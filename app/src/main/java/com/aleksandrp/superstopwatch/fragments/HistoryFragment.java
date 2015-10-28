package com.aleksandrp.superstopwatch.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

        initTab(view);
        initializedRecyclerView(view);
        initialized();
        initializeAdapter();

        return view;
    }

    private void initTab(View view) {
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab_layout_history);
        tabLayout.addTab(tabLayout.newTab()
                .setText("All"));
//                .setIcon(R.drawable.icon_bg));
        tabLayout.addTab(tabLayout.newTab()
                .setText("Name"));
//                .setIcon(R.drawable.icon_bg));
        tabLayout.addTab(tabLayout.newTab()
                .setText("Date"));
//                .setIcon(R.drawable.ic_timelapse_white_24dp));
        tabLayout.addTab(tabLayout.newTab()
                .setText("Time"));
//                .setIcon(R.drawable.ic_av_timer_white_24dp));

//        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
//        AdapterStopwatch adapterStopwatch = new AdapterStopwatch(fragmentManager, 4);
//
//        viewPager.setAdapter(adapterStopwatch);
//        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//                viewPager.setCurrentItem(tab.getPosition());
                Toast.makeText(getActivity(), tab.getText()+"", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initializedRecyclerView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
    }

    private void initializeAdapter() {
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(times, getActivity().getApplicationContext());
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    private void initialized() {
        times = db.getAllTimeByDate();
    }


}
