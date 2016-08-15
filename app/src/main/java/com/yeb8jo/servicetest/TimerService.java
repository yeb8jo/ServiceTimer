package com.yeb8jo.servicetest;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerService extends Service {
    public static final String TAG = TimerService.class.getSimpleName();
    public static final String KEY_INTERVAL = "key.interval";
    private int INTERVAL_PERIOD;
    private Timer timer = null;

    public TimerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");

        if(timer == null) {
            timer = new Timer();
        }

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");

        Bundle bundle = null;
        if(intent != null) {
            bundle = intent.getExtras();
            INTERVAL_PERIOD = bundle.getInt(KEY_INTERVAL);
        }

        timer.scheduleAtFixedRate(new TimerTask() {

            private Handler updateUI = new Handler(){
                @Override
                public void dispatchMessage(Message msg) {
                    super.dispatchMessage(msg);
                    func();
                }

                private void func() {
                    Toast.makeText(TimerService.this, "hello world", Toast.LENGTH_SHORT).show();
                }
            };

            @Override
            public void run() {
                Log.d(TAG, "hello world");
                updateUI.sendEmptyMessage(0);
            }
        }, new Date(), INTERVAL_PERIOD);

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");

        if(timer != null) {
            timer.cancel();
            timer = null;
        }
    }


    /**
     * ps :
     * you never kill me pattern
     *
     * http://stackoverflow.com/a/21551045/6005850
     */
}
