package com.example.myhandlerdemo;
import android.util.Log;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.*;

public class JavaThreadWorker extends Thread {
    private  static final String TAG = "JavaThreadWorker";
    private AtomicBoolean mAlive = new AtomicBoolean(true);
    private  ConcurrentLinkedQueue<Runnable> taskQueue = new ConcurrentLinkedQueue<>();
    // ConcurrentLinkedQueue is used so that multiple threads can write tasks in to this Queue.

    public JavaThreadWorker() {
        super(TAG);
        start(); // calling start method of thread , start it self
    }

    @Override
    public void run() {
        while(mAlive.get()) {
            Runnable task = taskQueue.poll();
            if(task != null) task.run();
        }
        Log.i(TAG, "Thread is terminaed");
    }

    public JavaThreadWorker execute(Runnable task) {
        taskQueue.add(task);
        return this;
    }

    public void quit() {
        mAlive.set(false);
    }
}
