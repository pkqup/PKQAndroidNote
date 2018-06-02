package com.android.pkqup.androidnote.activity_test;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.pkqup.androidnote.R;
import com.android.pkqup.androidnote.abase.BaseActivity;

/**
 * Created by LiuCun on 2017/11/10.<br>
 * Describe
 */

public class ActivityTestActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_activity);


        Log.e("AndroidNote", "taskId" + getTaskId());
        findViewById(R.id.bt_call).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel://123456"));
                startActivity(intent);

            }
        });
        findViewById(R.id.bt_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 网页
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent);
            }
        });
        findViewById(R.id.bt_other).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(Intent.ACTION_MAIN);
                //intent.addCategory(Intent.CATEGORY_LAUNCHER);
                //有 <intent-filter>标签的Activity默认  android:exported="true"
                Intent intent = new Intent();
                //参数是包名，类全限定名，注意直接用类名不行
                ComponentName cn = new ComponentName("com.example.liucun.myapplication",
                        "com.example.liucun.myapplication.MainActivity");
                intent.setComponent(cn);
                startActivity(intent);
            }
        });
        findViewById(R.id.bt_other_not_main).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //必须要在manifest里面设置exported属性为true，表示此activity对外公开，才能直接跳转。
                Intent intent = new Intent();
                intent.setClassName("com.example.liucun.myapplication",
                        "com.example.liucun.myapplication.test.SecondActivity");
                startActivity(intent);
            }
        });


    }
}
