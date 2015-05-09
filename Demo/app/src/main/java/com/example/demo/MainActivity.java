package com.example.demo;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonPlay = (Button) findViewById(R.id.btn_play);
        Button buttonStop = (Button) findViewById(R.id.btn_stop);

        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send broadcast
                Intent actionPlay = new Intent(MusicPlayerService.ACTION_MEDIA);
                actionPlay.putExtra(MusicPlayerService.ACTION_MEDIA, MusicPlayerService.ACTION_PLAY);
                sendBroadcast(actionPlay);
            }
        });

        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actionPlay = new Intent(MusicPlayerService.ACTION_MEDIA);
                actionPlay.putExtra(MusicPlayerService.ACTION_MEDIA, MusicPlayerService.ACTION_STOP);
                sendBroadcast(actionPlay);
            }
        });
//        new ParseListTask(new ParseListTaskCompletedListener() {
//            @Override
//            public void onTaskCompleted() {
//
//            }
//        }).execute("Demo string", "Demo string");
//
//        Log.i(TAG, "Line 20");

        startService(new Intent(this, MusicPlayerService.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class ParseListTask extends AsyncTask<String, Integer, String> {
        private final String TAG = ParseListTask.class.getSimpleName();

        private ParseListTaskCompletedListener listTaskCompletedListener;

        public ParseListTask(ParseListTaskCompletedListener listTaskCompletedListener){
            this.listTaskCompletedListener = listTaskCompletedListener;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.i(TAG, "onPreExecute");
        }

        @Override
        protected String doInBackground(String... params) {
            Log.i(TAG, "doInBackground");
            // Volley or picasso here
            return null;
        }

        @Override
        protected void onPostExecute(String resultOfDoInBackground) {
            super.onPostExecute(resultOfDoInBackground);
            Log.i(TAG, "onPostExecute");
        }

    }

    private interface ParseListTaskCompletedListener{
        public void onTaskCompleted();
    }
}
