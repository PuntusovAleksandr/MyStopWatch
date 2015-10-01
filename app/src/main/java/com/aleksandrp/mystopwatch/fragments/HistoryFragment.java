package com.aleksandrp.mystopwatch.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aleksandrp.mystopwatch.R;
import com.github.lzyzsd.circleprogress.ArcProgress;
import com.github.lzyzsd.circleprogress.DonutProgress;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {

    ArcProgress arcProgress1;
    DonutProgress arcProgress2;

    public HistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_history, container, false);

        arcProgress1 = (ArcProgress) view.findViewById(R.id.arc_progress);
        arcProgress2 = (DonutProgress) view.findViewById(R.id.arc_progress1);

        arcProgress1.setMax(5555);
        arcProgress1.setProgress(111);

        arcProgress2.setMax(250);
        arcProgress2.setProgress(25);

        return view;
    }


}
