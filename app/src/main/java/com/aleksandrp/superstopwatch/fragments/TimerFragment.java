package com.aleksandrp.superstopwatch.fragments;


import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;

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

    private boolean run;

    private TimerTask task;

    private int countValue;

    private NumberPicker numberPickerHours;
    private NumberPicker numberPickerMinuts;
    private NumberPicker numberPickerSeconds;

    private int intProgress;
    private int res = 0;


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

    private void initialValues() {
        donutProgress = (DonutProgress) view.findViewById(R.id.donut_progress);
        btStartTimer = (Button) view.findViewById(R.id.bt_timer_start);
        btPauseTimer = (Button) view.findViewById(R.id.bt_timer_pause);
        btStopTimer = (Button) view.findViewById(R.id.bt_timer_stop);

        numberPickerHours = (NumberPicker) view.findViewById(R.id.nP_hours);
        numberPickerMinuts = (NumberPicker) view.findViewById(R.id.np_minutes);
        numberPickerSeconds = (NumberPicker) view.findViewById(R.id.np_seconds);

        donutProgress.setOnClickListener(listener);
        btStartTimer.setOnClickListener(listener);
        btPauseTimer.setOnClickListener(listener);
        btStopTimer.setOnClickListener(listener);

        numberPickerHours.setMaxValue(23);
        numberPickerMinuts.setMaxValue(59);
        numberPickerSeconds.setMaxValue(59);
        numberPickerHours.setMinValue(0);
        numberPickerMinuts.setMinValue(0);
        numberPickerSeconds.setMinValue(0);


        btStopTimer.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                donutProgress.setMax(0);
                donutProgress.setProgress(0);
                zering();
                return true;
            }
        });
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.bt_timer_start:
                    setValue();
                    if (intProgress == 0) {
                        if (donutProgress.getMax() > 0) {
                            zering();
                            task = new TimerTask();
                            run = true;
                            task.execute();
                            setInVisibleNumberPicker();
                        } else
                            Toast.makeText(getActivity(), "Enter value timer", Toast.LENGTH_SHORT).show();
                    } else {
                        donutProgress.setProgress(intProgress);
                        zering();
                        task = new TimerTask();
                        run = true;
                        task.execute();
                    }
                    break;
                case R.id.bt_timer_stop:
                    run = false;
                    countValue = 0;
                    res = 0;
                    zering();
                    setInVisibleNumberPicker();
                    break;
                case R.id.bt_pause:
                    if (task != null && !task.isCancelled()) {
                        countValue = 0;
                        res = 0;
                        intProgress = donutProgress.getProgress();
                        zering();
                        donutProgress.setProgress(intProgress);
                    }
                    setInVisibleNumberPicker();
                    break;
                case R.id.donut_progress:
                    setVisibleNumberPicker();
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
        int milInHour = numberPickerHours.getValue() * (int) MILLIS_IN_HOUR;
        int milInMin = numberPickerMinuts.getValue() * (int) MILLIS_IN_MINUTE;
        int milInSec = numberPickerSeconds.getValue() * (int) MILLIS_IN_SECOND;
        countValue = (milInHour + milInMin + milInSec) / 10;
        donutProgress.setMax(countValue);
    }

    private void taskTimerZero() {
        if (task != null) {
            if (!task.isCancelled())
                task.cancel(true);
            task = null;
        }
    }

    private void zering() {
        run = false;
        taskTimerZero();
    }

    class TimerTask extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] params) {
//             startTime = System.currentTimeMillis();
            while (res < countValue) {
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress();
                res++;
            }
            taskTimerZero();
            return null;
        }

        @Override
        protected void onProgressUpdate(Object[] values) {
            super.onProgressUpdate(values);
            donutProgress.setProgress(countValue - res);
        }
    }


}
