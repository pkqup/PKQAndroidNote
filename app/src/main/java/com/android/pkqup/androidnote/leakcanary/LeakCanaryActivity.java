package com.android.pkqup.androidnote.leakcanary;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;

import com.android.pkqup.androidnote.R;
import com.android.pkqup.androidnote.abase.BaseActivity;
import com.android.pkqup.androidnote.abase.MainActivity;

/**
 * @author liucun
 * @date 2020/9/16 17:02
 * @description
 */
public class LeakCanaryActivity extends BaseActivity {


    public static Context testContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leakcanary_test);

        testContext = this;

//        anonymousInnerClass();

        findViewById(R.id.tv_finish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    //匿名内部类持有MemoryTestActivity实例引用
    public void anonymousInnerClass() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("=================");
                }
            }
        }).start();
    }
}
