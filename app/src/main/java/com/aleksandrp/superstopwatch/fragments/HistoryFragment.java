package com.aleksandrp.superstopwatch.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
public class HistoryFragment extends Fragment implements DBImpl.RefreshList {

    private RecyclerView recyclerView;

    private List<TimeFix> times;

    private DBImpl db;

    public static RecyclerViewAdapter recyclerViewAdapter;

    private final String ALL = "All";
    private final String NAME = "Name";
    private final String DATE = "Date";
    private final String TIME = "Time";

    private TabLayout.Tab selectTab;

    private View view;

    public HistoryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        db = new DBImpl(getActivity().getApplicationContext());

        view = inflater.inflate(R.layout.recycler_view, container, false);

        initTab();
        initializedRecyclerView();
        initialized();
        initializeAdapter();

        return view;
    }

    public void initTab() {
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab_layout_history);
        tabLayout.addTab(tabLayout.newTab()
                .setText(ALL));
        tabLayout.addTab(tabLayout.newTab()
                .setText(NAME));
        tabLayout.addTab(tabLayout.newTab()
                .setText(DATE));
        tabLayout.addTab(tabLayout.newTab()
                .setText(TIME));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.i("myLog", tab.getText() + "");
                if (db == null) {
                    db = new DBImpl(getActivity().getApplicationContext());
                }
                if (tab.getText().equals(ALL)) times = db.getAllTimeById();
                if (tab.getText().equals(NAME)) times = db.getAllTimeByTitle();
                if (tab.getText().equals(DATE)) times = db.getAllTimeByDate();
                if (tab.getText().equals(TIME)) times = db.getAllTimeByTime();
                initializeAdapter();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }


    private void initializedRecyclerView() {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
    }

    private void initializeAdapter() {
        recyclerViewAdapter = null;
        recyclerViewAdapter = new RecyclerViewAdapter(times, getActivity());
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    private void initialized() {
        times = db.getAllTimeById();
    }

    @Override
    public void refreshListHistory() {
        initialized();
        initializeAdapter();
    }
}
