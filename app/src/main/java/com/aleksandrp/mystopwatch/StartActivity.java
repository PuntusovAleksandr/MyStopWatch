package com.aleksandrp.mystopwatch;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity {

    static final long MILLIS_IN_HOUR = 3600000;
    static final long MILLIS_IN_MINUTE = 60000;
    static final long MILLIS_IN_SECOND = 1000;

    private TextView textViewHead;
    private TextView textViewBody;
    private Button btStart;
    private Button btStop;
    private Button btPause;
    private TimerTask task;
    private boolean run;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);

        textViewHead = (TextView) findViewById(R.id.textView_head);
        textViewBody = (TextView) findViewById(R.id.textView_body);
        btStart = (Button) findViewById(R.id.bt_start);
        btStop = (Button) findViewById(R.id.bt_stop);
        btPause = (Button) findViewById(R.id.bt_pause);

        btStart.setOnClickListener(listener);
        btPause.setOnClickListener(listener);
        btStop.setOnClickListener( listener);
        btStop.setOnLongClickListener(longListener);

    }

    class TimerTask extends AsyncTask {
        long timeFromStart = 0;

        @Override
        protected Object doInBackground(Object[] params) {
            long startTime = System.currentTimeMillis();
            while (run) {
                try {
                    Thread.sleep(10);
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


        String getFormattedTime() {

            long time = timeFromStart;


            long hours = time / MILLIS_IN_HOUR;
            time = time % MILLIS_IN_HOUR;

            long minutes = time / MILLIS_IN_MINUTE;
            time = time % MILLIS_IN_MINUTE;

            long seconds = time / MILLIS_IN_SECOND;
            time = time % MILLIS_IN_SECOND;

            long milliseconds = time/10;

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
                    task = new TimerTask();
                    run = true;
                    task.execute();
                    break;
                case R.id.bt_stop:
                    run = false;
                    if (task != null)
                    task = null;
                    break;
                case R.id.bt_pause:
                    textViewBody.setText(textViewHead.getText().toString());
                    break;
            }
        }
    };
    View.OnLongClickListener longListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            zering();
            return true;
        }
    };

    private void zering() {
        run = false;
        if (task != null)
            task = null;
        textViewBody.setText(R.string.four_zero);
        textViewHead.setText(R.string.four_zero);
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
