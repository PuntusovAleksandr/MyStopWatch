package com.aleksandrp.mystopwatch;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity {

    TextView textViewHead;
    Button btStart;
    Button btStop;
    TimerTask task;
    boolean run = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);

        textViewHead = (TextView) findViewById(R.id.textView_head);
        btStart = (Button) findViewById(R.id.bt_start);
        btStop = (Button) findViewById(R.id.bt_stop);

        btStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task = new TimerTask();
                task.execute();
            }
        });

        btStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                run = false;
                task = null;
            }
        });
    }

    class TimerTask extends AsyncTask {

        ;
        long timeFromStart = 0;

        @Override
        protected Object doInBackground(Object[] params) {
            long startTime = System.currentTimeMillis();
            while (run) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                    timeFromStart = System.currentTimeMillis() - startTime;
                    publishProgress();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Object[] values) {
            super.onProgressUpdate(values);

            textViewHead.setText(getFormattedTime());
        }

        static final long MILLIS_IN_HOUR = 3600000;
        static final long MILLIS_IN_MINUTE = 60000;
        static final long MILLIS_IN_SECOND = 1000;

        String getFormattedTime() {

            long time = timeFromStart;

            long hours = time / MILLIS_IN_HOUR;
            time = time % MILLIS_IN_HOUR;

            long minutes = time / MILLIS_IN_MINUTE;
            time = time % MILLIS_IN_MINUTE;

            long seconds = time / MILLIS_IN_SECOND;

            String sHours = (hours == 0) ? "00" : (hours < 10) ? "0" + String.valueOf(hours) : String.valueOf(hours);
            String sMinutes = (minutes == 0) ? "00" : (minutes < 10) ? "0" + String.valueOf(minutes) : String.valueOf(minutes);
            String sSeconds = (seconds == 0) ? "00" : (seconds < 10) ? "0" + String.valueOf(seconds) : String.valueOf(seconds);

            return sHours + ":" + sMinutes + ":" + sSeconds;
        }
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
