package com.android.pkqup.androidnote.annotation_test;

import android.os.Bundle;
import android.util.Log;

import com.android.pkqup.androidnote.R;
import com.android.pkqup.androidnote.abase.BaseActivity;
import com.pkqup.aptlib.annotation.Code;
import com.pkqup.aptlib.annotation.Print;

/**
 * Created by LiuCun on 2017/11/17.<br>
 * Describe
 */

public class AnnotationActivity extends BaseActivity {

    @Print
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annotation);
        process();

        User user = new User();
        AnnotationProcessor.init(user);
        Log.e("AnnotationActivity",user.toString());
    }

    @Code(author = "closedevice", date = "20161225")
    private void process() {

    }
}
