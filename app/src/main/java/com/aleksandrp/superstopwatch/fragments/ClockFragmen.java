package com.aleksandrp.superstopwatch.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aleksandrp.mystopwatch.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import at.grabner.circleprogress.CircleProgressView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClockFragmen extends Fragment {

    private CircleProgressView circleHours;
    private CircleProgressView circleMin;
    private CircleProgressView circleSec;

    private TextView textView;

    public static final long MILLIS_IN_HOUR = 3600000;
    public static final long MILLIS_IN_MINUTE = 60000;
    public static final long MILLIS_IN_SECOND = 1000;

    private long timeLong, time, hours, minutes, seconds;


    public ClockFragmen() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_clock, container, false);

        initCircle(view);
        setData();

        return view;
    }

    private void initCircle(View view) {
        circleHours = (CircleProgressView) view.findViewById(R.id.circleView_hours);
        circleMin = (CircleProgressView) view.findViewById(R.id.circleView_min);
        circleSec = (CircleProgressView) view.findViewById(R.id.circleView_sec);

        textView = (TextView) view.findViewById(R.id.tv_time);


        circleSec.setBarColor(getResources().getColor(R.color.Chocolate),
                getResources().getColor(R.color.Red));

        circleMin.setBarColor(getResources().getColor(R.color.LimeGreen),
                getResources().getColor(R.color.LawnGreen),
                getResources().getColor(R.color.GreenYellow));

        circleHours.setBarColor(getResources().getColor(R.color.MediumBlue),
                getResources().getColor(R.color.Blue));
    }

    private void setData() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    getParamsDate();
                    if (getActivity() != null)
                    getActivity().runOnUiThread(setText);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

    private void getParamsDate() {
        timeLong = new Date().getTime();
        time = timeLong;

        hours =  time / MILLIS_IN_HOUR;
        time = time % MILLIS_IN_HOUR;

        minutes = time / MILLIS_IN_MINUTE;
        time = time % MILLIS_IN_MINUTE;

        seconds = time / MILLIS_IN_SECOND;
    }

    Runnable setText = new Runnable() {

        @Override
        public void run() {
            textView.setText(new SimpleDateFormat("h:mm:ss").format(timeLong) + "");

            int hoursText  = Integer.parseInt(new SimpleDateFormat("hh").format(timeLong));
            circleHours.setValue(hoursText);
            circleMin.setValue(minutes);
            circleSec.setValue(seconds);
        }
    };

}
