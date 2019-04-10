package com.example.myhandlerdemo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView mTextViewMessage = null;
    JavaThreadWorker worker;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mTextViewMessage.setText((String)msg.obj);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextViewMessage = (TextView) findViewById(R.id.textViewMessage);
        worker =  new JavaThreadWorker();

        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    Message message = Message.obtain();
                    message.obj = "Task 1";
                    mHandler.sendMessage(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    Message message = Message.obtain();
                    message.obj = "Task 2";
                    mHandler.sendMessage(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable r3 = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    Message message = Message.obtain();
                    message.obj = "Task 3";
                    mHandler.sendMessage(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        worker.execute(r1);
        worker.execute(r2);
        worker.execute(r3);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        worker.quit();
    }
}
