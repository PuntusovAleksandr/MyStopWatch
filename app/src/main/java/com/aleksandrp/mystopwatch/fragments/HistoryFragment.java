package com.aleksandrp.mystopwatch.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aleksandrp.mystopwatch.R;
import com.aleksandrp.mystopwatch.adapters.RecyclerViewAdapter;
import com.aleksandrp.mystopwatch.entity.Time;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    private List<Time> times;

    private RecyclerViewAdapter recyclerViewAdapter;

    public HistoryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.recycler_view, container, false);
        initializedRecyclerView(view);
        initialized();
        initializeAdapter();
        return view;
    }

    private void initializedRecyclerView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
    }

    private void initializeAdapter() {
        recyclerViewAdapter = new RecyclerViewAdapter(times);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    private void initialized() {
        times = new ArrayList<>();
        times.add(new Time("first", "123", R.drawable.dec_0));
        times.add(new Time("second", "1243", R.drawable.dec_1));
        times.add(new Time("three", "1233", R.drawable.dec_2));
        times.add(new Time("four", "1253", R.drawable.dec_3));
        times.add(new Time("fife", "1823", R.drawable.dec_4));
        times.add(new Time("six", "12513", R.drawable.dec_5));
        times.add(new Time("seven", "12563", R.drawable.dec_6));
    }


}
