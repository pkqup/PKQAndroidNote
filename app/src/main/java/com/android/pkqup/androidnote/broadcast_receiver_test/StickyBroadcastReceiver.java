package com.android.pkqup.androidnote.broadcast_receiver_test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.widget.Toast;

/**
 * Created by liucun on 2017/11/12.
 */

public class StickyBroadcastReceiver extends BroadcastReceiver {

    public static final String Action = "com.sample.test.sticky.broadcast.receiver";
    public static final String PERMISSION = "com.sample.test.permission.sticky.receiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        int checkCallingOrSelfPermission = context.checkCallingOrSelfPermission(PERMISSION);
        if (PackageManager.PERMISSION_GRANTED == checkCallingOrSelfPermission) {
            //权限判断
            Toast.makeText(context, "授权成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "授权失败", Toast.LENGTH_SHORT).show();
            throw new RuntimeException("permission denied");
        }
        if (intent != null && Action.equals(intent.getAction())) {
            Toast.makeText(context, intent.getStringExtra("info"), Toast.LENGTH_SHORT).show();
        }
    }

}
