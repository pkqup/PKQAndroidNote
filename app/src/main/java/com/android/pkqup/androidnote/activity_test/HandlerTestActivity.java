package com.android.pkqup.androidnote.activity_test;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * Created by liucun on 2017/11/12.
 */

public class HandlerTestActivity extends Activity {
    private static class MyHandler extends Handler {
        private final WeakReference<HandlerTestActivity> mActivity;

        public MyHandler(HandlerTestActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            HandlerTestActivity activity = mActivity.get();
            if (activity != null) {
                // ...
            }
        }
    }

    private final MyHandler mHandler = new MyHandler(this);

    private static final Runnable sRunnable = new Runnable() {
        @Override
        public void run() {
            /* ... */
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mHandler.postDelayed(sRunnable, 1000 * 60 * 10);

        finish();
    }
}
