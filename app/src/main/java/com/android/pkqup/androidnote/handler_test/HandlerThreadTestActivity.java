package com.android.pkqup.androidnote.handler_test;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.android.pkqup.androidnote.R;
import com.android.pkqup.androidnote.abase.BaseActivity;

import java.lang.ref.WeakReference;

/**
 * Created by LiuCun on 2017/11/9.<br>
 * Describe
 */

public class HandlerThreadTestActivity extends BaseActivity {


           private static class MyHandler extends Handler {

                WeakReference<HandlerThreadTestActivity> mActivity;

                public MyHandler(HandlerThreadTestActivity activity) {
                    mActivity = new WeakReference<>(activity);
                }

                @Override
                public void handleMessage(Message msg) {
                    HandlerThreadTestActivity activity = mActivity.get();
                    if (null!=activity) {
                    }
                }
            }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    private HandlerThread handlerThread;// 子线程
    private Handler childHandler;// 与子线程关联的Handler
    private Handler.Callback callback = new Handler.Callback() {// 子线程处理耗时操作的回调
        @Override
        public boolean handleMessage(Message msg) {
            Log.i(TAG, Thread.currentThread().getName() + ":" + Thread.currentThread().getId());
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_thread_test);
        findViewById(R.id.bt_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, Thread.currentThread().getName() + ":" + Thread.currentThread().getId());
                childHandler.sendEmptyMessage(0);
            }
        });
        initHandlerThread();


    }

    private void initHandlerThread() {
        handlerThread = new HandlerThread("child_thread"); // 构造参数为子线程的线程名
        handlerThread.start();// 开启子线程
        childHandler = new Handler(handlerThread.getLooper(), callback);// 初始化子线程的Handler
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 释放资源
        handlerThread.quit();
    }

    class LooperThread extends Thread {
        public Handler mHandler;

        public void run() {
//            Looper.prepare();

            mHandler = new Handler() {
                public void handleMessage(Message msg) {
                    // process incoming messages here
                }
            };

//            Looper.loop();
        }
    }
}
