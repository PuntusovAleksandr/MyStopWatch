package com.aleksandrp.superstopwatch.fragments;


import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.aleksandrp.mystopwatch.R;
import com.github.lzyzsd.circleprogress.DonutProgress;

import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimerFragment extends Fragment {

    private DonutProgress donutProgress;
    private Button btStartTimer;
    private Button btPauseTimer;
    private Button btStopTimer;
    private View view;

    private boolean run;

    private TimerTask task;

    private int countValue;

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

        btStartTimer.setOnClickListener(listener);
        btPauseTimer.setOnClickListener(listener);
        btStopTimer.setOnClickListener(listener);
        task = new TimerTask();
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.bt_timer_start:
                    zering();
                    task = new TimerTask();
                    run = true;
                    task.execute();

                case R.id.bt_timer_stop:
                    run = false;
                    taskTimerZero();
            }
        }
    };

    private void taskTimerZero() {
        if (task != null)
            task = null;
    }

    private void zering() {

        run = false;
        taskTimerZero();
        countValue = 1000;
        donutProgress.setMax(countValue);
    }

    class TimerTask extends AsyncTask {
        int res = 0;

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
            return null;
        }

        @Override
        protected void onProgressUpdate(Object[] values) {
            super.onProgressUpdate(values);
            donutProgress.setProgress(countValue - res);
        }
    }


}
