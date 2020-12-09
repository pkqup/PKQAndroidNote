package com.android.pkqup.androidnote.broadcast_receiver_test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;

import com.android.pkqup.androidnote.R;
import com.android.pkqup.androidnote.abase.BaseActivity;

/**
 * Created by liucun on 2017/11/12.
 */

public class BroadcastReceiverActivity extends BaseActivity {

    private MyReceiver myReceiver;
    private static final String ACTION_BROADCAST_ONE = "action_broadcast_receiver_one";
    private static final String ACTION_BROADCAST_TWO = "action_broadcast_receiver_two";
    private static final String ACTION_BROADCAST_THREE = "action_broadcast_receiver_three";


    private LocalBroadcastManager localBroadcastManager;
    private LocalReceiver localReceiver;
    private final String LOCAL_ACTION = "com.example.local.receiver";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_receiver);

        initBroadcast();

        initLocalBroadcastReceiver();

        findViewById(R.id.bt_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ACTION_BROADCAST_ONE);
                intent.putExtra("msg","ACTION_BROADCAST_RECEIVER");
                // 包含未启动的过的应用（也可以收到广播），默认为不包含
                intent.setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                //指定广播接收者的包名，只有这个包名下的接收者才可以接收到此广播
                intent.setPackage("com.android.pkqup.androidnote");
                sendBroadcast(intent);
            }
        });

        findViewById(R.id.bt_send_local).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LOCAL_ACTION);
                localBroadcastManager.sendBroadcast(intent);
            }
        });

        findViewById(R.id.bt_send_sticky).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(StickyBroadcastReceiver.Action);
                intent.putExtra("info", "sticky broadcast has been receiver");
                sendStickyBroadcast(intent);
            }
        });
    }

    //注册本地广播
    private void initLocalBroadcastReceiver() {
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        localReceiver = new LocalReceiver();
        localBroadcastManager.registerReceiver(localReceiver, new IntentFilter(LOCAL_ACTION));
    }

    //注册普通广播
    private void initBroadcast() {
        myReceiver = new MyReceiver();
        registerReceiver(myReceiver, new IntentFilter(ACTION_BROADCAST_ONE));
        registerReceiver(myReceiver, new IntentFilter(ACTION_BROADCAST_TWO));
        registerReceiver(myReceiver, new IntentFilter(ACTION_BROADCAST_THREE));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver);
        localBroadcastManager.unregisterReceiver(localReceiver);
    }

    public class LocalReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("LocalReceiver", "接收到了本地广播");
        }
    }


    public class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action){
                case ACTION_BROADCAST_ONE:
                    String msg = intent.getStringExtra("msg");
                    Log.i("MyReceiver", msg);
                    break;
                case ACTION_BROADCAST_TWO:
                    break;
                case ACTION_BROADCAST_THREE:
                    break;
            }
        }
    }

}
