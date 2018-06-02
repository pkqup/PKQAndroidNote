package com.android.pkqup.androidnote.abase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.pkqup.androidnote.R;
import com.android.pkqup.androidnote.activity_test.ActivityTestActivity;
import com.android.pkqup.androidnote.animation_test.FrameActivity;
import com.android.pkqup.androidnote.animation_test.PhysicsBasedAnimationActivity;
import com.android.pkqup.androidnote.animation_test.PropertyActivity;
import com.android.pkqup.androidnote.animation_test.TweenActivity;
import com.android.pkqup.androidnote.annotation_test.AnnotationActivity;
import com.android.pkqup.androidnote.broadcast_receiver_test.BroadcastReceiverActivity;
import com.android.pkqup.androidnote.content_provider_test.ContentProviderActivity;
import com.android.pkqup.androidnote.fragment_test.FragmentTestActivity;
import com.android.pkqup.androidnote.generics_test.GenericsActivity;
import com.android.pkqup.androidnote.glide_test.GlideActivity;
import com.android.pkqup.androidnote.handler_test.HandlerThreadTestActivity;
import com.android.pkqup.androidnote.okhttp_test.OkHttpActivity;
import com.android.pkqup.androidnote.permission_test.PermissionActivity;
import com.android.pkqup.androidnote.rxjava_retrofit_okhttp_test.RetrofitActivity;
import com.android.pkqup.androidnote.rxjava_test.RxJavaActivity;
import com.android.pkqup.androidnote.screen_scale_test.ScreenActivity;
import com.android.pkqup.androidnote.service_test.ServiceTestOneActivity;
import com.android.pkqup.androidnote.status_bar_test.StatusBarActivity;
import com.android.pkqup.androidnote.touch_event_test.TouchTestActivity;
import com.android.pkqup.androidnote.view_draw_test.ViewDrawTestActivity;
import java.util.List;

public class MainActivity extends BaseActivity {

    private List list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在冷启动后改为默认的样式
        setTheme(R.style.AppTheme);

        setContentView(R.layout.activity_main);


        findViewById(R.id.bt_frame).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.size();
                startActivity(new Intent(MainActivity.this, FrameActivity.class));
            }
        });
        findViewById(R.id.bt_tween).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TweenActivity.class));
            }
        });
        findViewById(R.id.bt_property).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, PropertyActivity.class));
            }
        });
        findViewById(R.id.bt_physics).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, PhysicsBasedAnimationActivity.class));
            }
        });
        findViewById(R.id.bt_fragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, FragmentTestActivity.class));
            }
        });
        findViewById(R.id.bt_handler).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, HandlerThreadTestActivity.class));
            }
        });
        findViewById(R.id.bt_touch_dispatch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TouchTestActivity.class));
            }
        });
        findViewById(R.id.bt_view_draw).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ViewDrawTestActivity.class));
            }
        });
        findViewById(R.id.bt_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ActivityTestActivity.class));
            }
        });
        findViewById(R.id.bt_broadcast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, BroadcastReceiverActivity.class));
            }
        });
        findViewById(R.id.bt_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ServiceTestOneActivity.class));
            }
        });
        findViewById(R.id.bt_content_provider).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ContentProviderActivity.class));
            }
        });
        findViewById(R.id.bt_permission).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, PermissionActivity.class));
            }
        });
        findViewById(R.id.bt_annotation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AnnotationActivity.class));
            }
        });
        findViewById(R.id.bt_generics).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, GenericsActivity.class));
            }
        });
        findViewById(R.id.bt_status_bar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, StatusBarActivity.class));
            }
        });
        findViewById(R.id.bt_screen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ScreenActivity.class));
            }
        });
        findViewById(R.id.bt_okhttp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, OkHttpActivity.class));
            }
        });
        findViewById(R.id.bt_retrofit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RetrofitActivity.class));
            }
        });
        findViewById(R.id.bt_glide).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, GlideActivity.class));
            }
        });
        findViewById(R.id.bt_rxjava).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RxJavaActivity.class));
            }
        });
    }


}
