package com.aleksandrp.mystopwatch.main;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.aleksandrp.mystopwatch.R;
import com.aleksandrp.mystopwatch.adapters.AdapterStopwatch;
import com.aleksandrp.mystopwatch.db.functions_db.DBImpl;
import com.aleksandrp.mystopwatch.fragments.HistoryFragment;
import com.aleksandrp.mystopwatch.fragments.StopwatchFragment;
import com.aleksandrp.mystopwatch.fragments.TimerFragment;
import com.aleksandrp.mystopwatch.values.Values;

public class StartActivity extends AppCompatActivity implements Values {

    private DBImpl db;

    private FragmentManager fragmentManager;
    private StopwatchFragment stopwatchFragment;
    private TimerFragment timerFragment;
    private HistoryFragment historyFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);

        db = new DBImpl(getApplicationContext());
        db.openDb();

        fragmentManager = getFragmentManager();
        setUi();
    }

    private void setUi() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitleTextColor(getResources().getColor(R.color.Orange));
            setSupportActionBar(toolbar);
        }

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab()
                .setText(R.string.title_stopwatch)
                .setIcon(R.drawable.ic_timelapse_white_24dp));
        tabLayout.addTab(tabLayout.newTab()
                .setText(R.string.title_timer)
                .setIcon(R.drawable.ic_av_timer_white_24dp));
        tabLayout.addTab(tabLayout.newTab()
                .setText(R.string.title_history)
                .setIcon(R.drawable.ic_restore_white_24dp));

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        AdapterStopwatch adapterStopwatch = new AdapterStopwatch(fragmentManager, 3);

        viewPager.setAdapter(adapterStopwatch);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        db.close();
    }


    //    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_start, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
