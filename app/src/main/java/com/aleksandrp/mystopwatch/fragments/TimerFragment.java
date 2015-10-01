package com.aleksandrp.mystopwatch.fragments;


import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aleksandrp.mystopwatch.R;
import com.github.lzyzsd.circleprogress.DonutProgress;

import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimerFragment extends Fragment {

    private DonutProgress donutProgress;

    public TimerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timer, container, false);
        donutProgress = (DonutProgress) view.findViewById(R.id.donut_progress);

        donutProgress.setMax(1000);

        count();

        return view;
    }

    private void count() {
        new TimerTask().execute();
    }
    class TimerTask extends AsyncTask {
        long timeFromStart = 0;
        int res = 0;

        @Override
        protected Object doInBackground(Object[] params) {
//             startTime = System.currentTimeMillis();
            while (res<1000) {
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
            donutProgress.setProgress(res);
        }
    }


}
