package com.android.pkqup.androidnote.service_test;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.android.pkqup.androidnote.R;
import com.android.pkqup.androidnote.abase.MainActivity;

/**
 * Created by LiuCun on 2017/11/13.<br>
 * Describe
 */

public class MyService extends Service {

    public static final String TAG = "MyService";
    public boolean isServiceBind = false;

    public MyBinder myBind = new MyBinder();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "MyService thread id is " + Thread.currentThread().getId());
        Log.e(TAG, "onCreate() executed");
        createForegroundService();
    }

    //创建前台服务
    private void createForegroundService() {
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,notificationIntent,0);
        Notification.Builder builder = new Notification.Builder(getApplicationContext());
        builder.setContentTitle("***服务");
        builder.setContentText("请勿关闭，***");
        builder.setSmallIcon(R.mipmap.service_icon);
        builder.setContentIntent(pendingIntent);
        Notification notification = builder.build();

        //启动前台服务
        startForeground(1,notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand() executed");
        return super.onStartCommand(intent, flags, startId);
    }

    // 返回具体的IBind对象
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind() executed");
        isServiceBind = true;
        return myBind;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        isServiceBind = false;
        Log.e(TAG, "onUnbind() executed");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //停止前台服务
        stopForeground(true);
        Log.e(TAG, "onDestroy() executed");
    }

    public void doSomeThing() {
        Log.e(TAG, "doSomeThing() executed");
    }

    public class MyBinder extends Binder {

        public MyService getService() {
            return MyService.this;
        }

        public void doWork() {
            Log.e("MyBind", "doWork() executed");
        }
    }
}
