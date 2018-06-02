package com.android.pkqup.androidnote.content_provider_test;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.pkqup.androidnote.R;
import com.android.pkqup.androidnote.abase.BaseActivity;

/**
 * Created by LiuCun on 2017/11/14.<br>
 * Describe
 */

public class ContentProviderActivity extends BaseActivity {

    private UserUtils userUtils;

    private String name = "zhang san";
    private int index = 0;
    private int id = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_provider_activity);

        userUtils = new UserUtils(this);


        findViewById(R.id.bt_insert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userUtils.insertUser(name + index, id + index + "");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(500);
                            index++;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        findViewById(R.id.bt_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userUtils.deleteUser();
                index = 0;
            }
        });

        findViewById(R.id.bt_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userUtils.updateUser("li si", id + index + "");
            }
        });

        findViewById(R.id.bt_get_user).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = userUtils.queryUser();
                Toast.makeText(ContentProviderActivity.this,
                        "名字：" + user.getName() + "  ;  " + "id:" + user.getUserId(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.bt_get_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userNameFrom = userUtils.getUserNameFrom(id + index - 1 + "");
                Toast.makeText(ContentProviderActivity.this, "名字：" + userNameFrom,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
