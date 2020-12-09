package com.android.pkqup.androidnote.animation_test;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;

import com.android.pkqup.androidnote.R;
import com.android.pkqup.androidnote.abase.BaseActivity;
import com.android.pkqup.androidnote.animation_test.fragment.BasePhysicsFragment;
import com.android.pkqup.androidnote.animation_test.fragment.ChainFragment;
import com.android.pkqup.androidnote.animation_test.fragment.TranslationFragment;

/**
 * Created by LiuCun on 2017/11/6.<br>
 * Describe
 */

public class PhysicsBasedAnimationActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physics);
        findViewById(R.id.bt_base).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content, new BasePhysicsFragment())
                        .commit();
            }
        });
        findViewById(R.id.bt_translation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content, new TranslationFragment())
                        .commit();
            }
        });
        findViewById(R.id.bt_chained).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content, new ChainFragment())
                        .commit();
            }
        });
    }
}
