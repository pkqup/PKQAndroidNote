package com.android.pkqup.androidnote.service_test;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;

/**
 * Created by LiuCun on 2017/11/15.<br>
 * Describe
 */

public class RemoteService extends Service {

    public static final String TAG = "RemoteService";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate() executed");
        Log.e(TAG, "ServiceTestOneActivity process id is " + Process.myPid());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand() executed");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy() executed");
    }

    // 返回具体的IBind对象
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    IMyAIDLService.Stub binder = new IMyAIDLService.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
                double aDouble, String aString) throws RemoteException {
        }

        // 相加
        @Override
        public int plus(int a, int b) throws RemoteException {
            return a + b;
        }

        // 转大写
        @Override
        public String toUpperCase(String str) throws RemoteException {
            if (str != null) {
                return str.toUpperCase();
            }
            return null;
        }
    };
}
