package com.aleksandrp.superstopwatch.fragments;


import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.aleksandrp.mystopwatch.R;
import com.aleksandrp.superstopwatch.values.Values;

import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 */
public class StopwatchFragment extends Fragment implements Values {

    private TextView textViewHead;
    private TextView textViewBody;
    private Button btStart;
    private Button btStop;
    private Button btPause;
    private TimerTask task;
    private boolean run;
    private long startTime;
    private long timeLong;

    private View stopwatchView;


    public StopwatchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        stopwatchView = inflater.inflate(R.layout.fragment_stopwatch, container, false);
        init();

        return stopwatchView;
    }

    private void init() {
        textViewHead = (TextView) stopwatchView.findViewById(R.id.textView_head);
        textViewBody = (TextView) stopwatchView.findViewById(R.id.textView_body);
        btStart = (Button) stopwatchView.findViewById(R.id.bt_start);
        btStop = (Button) stopwatchView.findViewById(R.id.bt_stop);
        btPause = (Button) stopwatchView.findViewById(R.id.bt_pause);

        btStart.setOnClickListener(listener);
        btPause.setOnClickListener(listener);
        btStop.setOnClickListener(listener);
        btPause.setOnLongClickListener(longListener);
        btStop.setOnLongClickListener(longListener);
    }
    class TimerTask extends AsyncTask {
        long timeFromStart = 0;

        @Override
        protected Object doInBackground(Object[] params) {
//             startTime = System.currentTimeMillis();
            while (run) {
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                timeFromStart = System.currentTimeMillis() - startTime;
                timeLong = timeFromStart;
                publishProgress();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Object[] values) {
            super.onProgressUpdate(values);
            textViewHead.setText(getFormattedTime());
        }


        String getFormattedTime() {

            long time = timeFromStart;

            long hours = time / MILLIS_IN_HOUR;
            time = time % MILLIS_IN_HOUR;

            long minutes = time / MILLIS_IN_MINUTE;
            time = time % MILLIS_IN_MINUTE;

            long seconds = time / MILLIS_IN_SECOND;
            time = time % MILLIS_IN_SECOND;

            long milliseconds = time / 10;

            String sHours = (hours == 0) ? "00" : (hours < 10) ? "0" + String.valueOf(hours) : String.valueOf(hours);
            String sMinutes = (minutes == 0) ? "00" : (minutes < 10) ? "0" + String.valueOf(minutes) : String.valueOf(minutes);
            String sSeconds = (seconds == 0) ? "00" : (seconds < 10) ? "0" + String.valueOf(seconds) : String.valueOf(seconds);
            String mMilliseconds = (milliseconds == 0) ? "00" : (milliseconds < 10) ? "0" + String.valueOf(milliseconds) : String.valueOf(milliseconds);

            return sHours + ":" + sMinutes + ":" + sSeconds + ":" + mMilliseconds;
        }
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.bt_start:
                    zering();
                    checking();
                    task = new TimerTask();
                    run = true;
                    task.execute();
                    break;
                case R.id.bt_stop:
//                    db.putNewTime(new TimeFix(timeLong));
                    run = false;
                    if (task != null)
                        task = null;
                    break;
                case R.id.bt_pause:
//                    db.putNewTime(new TimeFix(timeLong));
                    textViewBody.setText(textViewHead.getText().toString());
                    break;
            }
        }
    };

    View.OnLongClickListener longListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            switch (v.getId()) {
                case R.id.bt_stop:
//                    db.putNewTime(new TimeFix(timeLong));
                    zering();
                    break;
                case R.id.bt_pause:
                    textViewBody.setText(R.string.four_zero);
                    break;
            }
            return true;
        }
    };

    private void checking() {
        if (task == null) {
            startTime = System.currentTimeMillis();
        }
    }

    private void zering() {
        run = false;
        if (task != null)
            task = null;
        textViewBody.setText(R.string.four_zero);
        textViewHead.setText(R.string.four_zero);
    }

}
