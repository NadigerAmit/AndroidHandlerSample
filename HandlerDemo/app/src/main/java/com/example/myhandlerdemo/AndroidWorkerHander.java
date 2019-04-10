package com.example.myhandlerdemo;

import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

public class AndroidWorkerHander extends HandlerThread {
    private static final String TAG = "AndroidWorkerHander";
    private Handler mHandler;

    public AndroidWorkerHander() {
        super(TAG);
        start();
        mHandler = new Handler(getLooper());
    }

    public  AndroidWorkerHander execute(Runnable task) {
        mHandler.post(task);
        Log.i(TAG, "Task is added to the Queue");
        return this;
    }
}
