package com.example.demo;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;

import java.io.IOException;

/**
 * Created by Nish on 5/9/15.
 */
public class MusicPlayerService extends Service implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener{
    private static final String TAG = MusicPlayerService.class.getSimpleName();

    public static final String ACTION_MEDIA = "com.example.demo.ACTION_MEDIA";

    public static final String ACTION_PLAY = "com.example.demo.ACTION_PLAY";
    public static final String ACTION_STOP = "com.example.demo.ACTION_STOP";

    private MediaPlayer mediaPlayer;
    private boolean isReady = false;

    public BroadcastReceiver buttonClickReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String command = intent.getStringExtra(ACTION_MEDIA);

            switch (command){
                case ACTION_PLAY:
                    Log.i(TAG, ACTION_PLAY);
                    play();
                    break;

                case ACTION_STOP:
                    Log.i(TAG, ACTION_STOP);
                    stop();
                    break;

                default:

            }
        }


    };

    private void play() {
        if(isReady && !mediaPlayer.isPlaying()){
            mediaPlayer.start();
        }
    }

    private void stop() {
        if(mediaPlayer.isPlaying()){
            mediaPlayer.stop();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setOnCompletionListener(this);

        try {
            mediaPlayer.setDataSource(this, Uri.parse("http://twit.cachefly.net/audio/kh/kh0142/kh0142.mp3"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.prepareAsync();
        registerReceiver(buttonClickReceiver, new IntentFilter(ACTION_MEDIA));
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy");
        unregisterReceiver(buttonClickReceiver);
        super.onDestroy();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {

    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        Log.i(TAG, "Media player is ready for some action");
        isReady = true;
    }
}
