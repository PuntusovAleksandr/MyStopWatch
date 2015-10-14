package com.aleksandrp.superstopwatch.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aleksandrp.mystopwatch.R;
import com.aleksandrp.superstopwatch.adapters.RecyclerViewAdapter;
import com.aleksandrp.superstopwatch.db.entity.TimeFix;
import com.aleksandrp.superstopwatch.db.functions_db.DBImpl;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {

    private RecyclerView recyclerView;

    private List<TimeFix> times;

    private DBImpl db;

    public HistoryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        db = new DBImpl(getActivity().getApplicationContext());

        View view = inflater.inflate(R.layout.recycler_view, container, false);
        initializedRecyclerView(view);
        initialized();
        initializeAdapter();
        return view;
    }

    private void initializedRecyclerView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
    }

    private void initializeAdapter() {
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(times);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    private void initialized() {
        times = db.getAllTimeByDate();
    }


}
