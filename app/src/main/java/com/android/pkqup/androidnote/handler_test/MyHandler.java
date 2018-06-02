package com.android.pkqup.androidnote.handler_test;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;

import java.lang.ref.WeakReference;

/**
 * Created by liucun on 2018/5/1.
 */

public abstract class MyHandler extends Handler {

    /**
     * 标记异步操作返回时目标界面已经消失时的处理状态
     */
    public static final int ACTIVITY_GONE = -1;

    public static final int ZERO = 0;
    public static final int ONE = 1;

    protected WeakReference<Activity> activityWeakReference;
    protected WeakReference<Fragment> fragmentWeakReference;

    //构造私有化,让调用者必须传递一个Activity 或者 Fragment的实例
    private MyHandler() {

    }

    public MyHandler(Activity activity) {
        this.activityWeakReference = new WeakReference<>(activity);
    }

    public MyHandler(Fragment fragment) {
        this.fragmentWeakReference = new WeakReference<>(fragment);
    }

    @Override
    public void handleMessage(Message msg) {
        if (activityWeakReference == null || activityWeakReference.get() == null || activityWeakReference.get().isFinishing()) {
            // 确认Activity是否不可用
            handleMessage(msg, ACTIVITY_GONE);
        } else if (fragmentWeakReference == null || fragmentWeakReference.get() == null || fragmentWeakReference.get().isRemoving()) {
            //确认判断Fragment不可用
            handleMessage(msg, ACTIVITY_GONE);
        } else {
            handleMessage(msg, msg.what);
        }
    }


     abstract void handleMessage(Message msg, int what);

}

