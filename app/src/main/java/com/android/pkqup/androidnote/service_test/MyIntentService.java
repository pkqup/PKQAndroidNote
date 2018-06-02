package com.android.pkqup.androidnote.service_test;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by LiuCun on 2017/11/13.<br>
 * Describe
 */

public class MyIntentService extends IntentService {

    public static final String TAG = "MyIntentService";

    public static final String ACTION_ONE = "action_one";
    public static final String ACTION_TWO = "action_two";
    public static final String ACTION_BROADCAST = "action_broad";
    private int progressOne, progressTwo;

    //无参的构造方法，指定线程名称
    public MyIntentService() {
        // 线程的名称
        super("MyIntentService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "MyService thread id is " + Thread.currentThread().getId());
        Log.e(TAG, "onCreate() executed");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand() executed");
        return super.onStartCommand(intent, flags, startId);
    }

    //实现此方法，处理异步任务，此方法运行在子线程（即HandlerThread）
    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null && intent.getAction() != null) {
            switch (intent.getAction()) {
                case ACTION_ONE:
                    while (progressOne < 100) {
                        progressOne++;
                        //发送广播通知UI线程更新UI
                        sendBroadcast(getUpdateIntent(0, progressOne));
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case ACTION_TWO:
                    while (progressTwo < 100) {
                        progressTwo++;
                        //发送广播通知UI线程更新UI
                        sendBroadcast(getUpdateIntent(1, progressTwo));
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy() executed");
    }

    private Intent getUpdateIntent(int i, int progress) {
        Intent intent = new Intent(ACTION_BROADCAST);
        intent.putExtra("type", i);
        intent.putExtra("progress", progress);
        return intent;
    }

}
