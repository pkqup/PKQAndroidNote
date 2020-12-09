package com.android.pkqup.androidnote.abase;

import android.os.Bundle;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;

import com.android.pkqup.androidnote.R;
import com.jaeger.library.StatusBarUtil;

import butterknife.ButterKnife;

/**
 * Created by LiuCun on 2017/11/7.<br>
 * Describe
 */

public class BaseActivity extends AppCompatActivity {

    public static String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getClass().getSimpleName();
    }

    public void setStatusBar() {
        StatusBarUtil.setColor(this, ContextCompat.getColor(this, R.color.colorAccent),
                StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA);
    }


    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        //注入要在setContentView()方法之后调用
        ButterKnife.bind(this);
        setStatusBar();
    }
}
