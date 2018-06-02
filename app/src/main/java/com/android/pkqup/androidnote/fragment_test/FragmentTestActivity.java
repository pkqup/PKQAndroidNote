package com.android.pkqup.androidnote.fragment_test;

import android.os.Bundle;
import android.util.Log;

import com.android.pkqup.androidnote.R;
import com.android.pkqup.androidnote.abase.BaseActivity;

import butterknife.OnClick;

/**
 * Created by LiuCun on 2017/11/7.<br>
 * Describe
 */

public class FragmentTestActivity extends BaseActivity {

    private static final String TAG = "FragmentTestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate");
        setContentView(R.layout.activity_fragment_text);
    }

    @OnClick(R.id.bt_first)
    void first() {
        getSupportFragmentManager().beginTransaction().replace(R.id.content, new FirstFragment())
                .commit();
    }

    @OnClick(R.id.bt_second)
    void second() {
        getSupportFragmentManager().beginTransaction().replace(R.id.content, new SecondFragment())
                .commit();
    }

    @OnClick(R.id.bt_third)
    void third() {
        getSupportFragmentManager().beginTransaction().replace(R.id.content, new ThirdFragment())
                .commit();
    }
}
