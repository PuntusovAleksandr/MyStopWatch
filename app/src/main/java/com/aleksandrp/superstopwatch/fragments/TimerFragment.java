package com.aleksandrp.superstopwatch.fragments;


import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;

import com.aleksandrp.mystopwatch.R;
import com.aleksandrp.superstopwatch.values.Values;
import com.github.lzyzsd.circleprogress.DonutProgress;

import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimerFragment extends Fragment implements Values {

    private DonutProgress donutProgress;
    private Button btStartTimer;
    private Button btPauseTimer;
    private Button btStopTimer;
    private View view;

    private TimerTask task;

    private int countValue;

    private NumberPicker numberPickerHours;
    private NumberPicker numberPickerMinuts;
    private NumberPicker numberPickerSeconds;

    private int intProgress;
    private int intMaxValue;
    private int resultTasck = 0;

    private boolean flagTask = false;


    public TimerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_timer, container, false);
        initialValues();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        stopAllProcess();
    }

    @Override
    public void onStop() {
        super.onStop();
        taskTimerZero();
    }

    private void initialValues() {
        initXMLValues();
        initNumberPicker();
        btStopTimer.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                stopAllProcess();
                return true;
            }
        });
    }

    private void stopAllProcess() {
        countValue = 0;
        resultTasck = 0;
        donutProgress.setMax(0);
        donutProgress.setProgress(0);
        taskTimerZero();

        numberPickerHours.setValue(0);
        numberPickerMinuts.setValue(0);
        numberPickerSeconds.setValue(0);

        btStartTimer.setEnabled(false);
    }

    private void initXMLValues() {
        donutProgress = (DonutProgress) view.findViewById(R.id.donut_progress);
        btStartTimer = (Button) view.findViewById(R.id.bt_timer_start);
        btPauseTimer = (Button) view.findViewById(R.id.bt_timer_pause);
        btStopTimer = (Button) view.findViewById(R.id.bt_timer_stop);

        donutProgress.setOnClickListener(listener);
        btStartTimer.setOnClickListener(listener);
        btPauseTimer.setOnClickListener(listener);
        btStopTimer.setOnClickListener(listener);
    }

    private void initNumberPicker() {
        numberPickerHours = (NumberPicker) view.findViewById(R.id.nP_hours);
        numberPickerMinuts = (NumberPicker) view.findViewById(R.id.np_minutes);
        numberPickerSeconds = (NumberPicker) view.findViewById(R.id.np_seconds);

        numberPickerHours.setMaxValue(23);
        numberPickerMinuts.setMaxValue(59);
        numberPickerSeconds.setMaxValue(59);
        numberPickerHours.setMinValue(0);
        numberPickerMinuts.setMinValue(0);
        numberPickerSeconds.setMinValue(0);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.bt_timer_start:
                    if (flagTask) {
                        stopAllProcess();
                        donutProgress.setMax(intMaxValue);
                        donutProgress.setProgress(intProgress);
                        countValue = intProgress;
                        flagTask = false;
                        btPauseTimer.setEnabled(true);
                    }else {
                        setValue();
                    }
                    task = new TimerTask();
                    task.execute();
                    setInVisibleNumberPicker();
                    btStartTimer.setEnabled(false);
                    break;
                case R.id.bt_timer_stop:
                    taskTimerZero();
                    setInVisibleNumberPicker();
                    btStartTimer.setEnabled(true);
                    break;
                case R.id.bt_timer_pause:
                    if (task != null && !task.isCancelled()) {
                        flagTask = true;
                        intMaxValue = donutProgress.getMax();
                        intProgress = donutProgress.getProgress();
                        taskTimerZero();
                        setInVisibleNumberPicker();
                        btStartTimer.setEnabled(true);
                        btPauseTimer.setEnabled(false);
                    }
                    break;
                case R.id.donut_progress:
                    setVisibleNumberPicker();
                    btStartTimer.setEnabled(true);
                    break;
            }
        }
    };

    private void setInVisibleNumberPicker() {
        numberPickerHours.setVisibility(View.INVISIBLE);
        numberPickerMinuts.setVisibility(View.INVISIBLE);
        numberPickerSeconds.setVisibility(View.INVISIBLE);
    }

    private void setVisibleNumberPicker() {
        numberPickerHours.setVisibility(View.VISIBLE);
        numberPickerMinuts.setVisibility(View.VISIBLE);
        numberPickerSeconds.setVisibility(View.VISIBLE);
    }

    private void setValue() {
        countValue = (numberPickerHours.getValue() * (int) MILLIS_IN_HOUR +
                numberPickerMinuts.getValue() * (int) MILLIS_IN_MINUTE +
                numberPickerSeconds.getValue() * (int) MILLIS_IN_SECOND) / 10;
        donutProgress.setMax(countValue);
        resultTasck = 0;
    }

    private void taskTimerZero() {
        if (task != null) {
            if (!task.isCancelled())
                task.cancel(true);
            task = null;
        }
    }

    class TimerTask extends AsyncTask {
        @Override
        protected Object doInBackground(Object[] params) {
//             startTime = System.currentTimeMillis();
            while (resultTasck < countValue) {
                if (isCancelled()) return null;
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress();
                resultTasck++;
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Object[] values) {
            super.onProgressUpdate(values);
            int result = countValue - resultTasck;
            if (result == (-1)) result = 0;
            donutProgress.setProgress(result);
        }
    }
}
