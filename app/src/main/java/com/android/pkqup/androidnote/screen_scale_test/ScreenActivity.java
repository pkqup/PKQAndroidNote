package com.android.pkqup.androidnote.screen_scale_test;

import android.os.Bundle;
import android.util.Log;

import com.android.pkqup.androidnote.R;
import com.android.pkqup.androidnote.abase.BaseActivity;

/**
 * Created by LiuCun on 2017/11/23.<br>
 * Describe
 */

public class ScreenActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);

        int dimension = (int) getResources().getDimension(R.dimen.dp_180);
        int widthPixels = getResources().getDisplayMetrics().widthPixels;

        Log.e("-----widthPixels--", widthPixels + "");
        Log.e("-----ScreenActivity----", dimension + "");
    }
}
