package com.android.pkqup.androidnote.animation_test.fragment;

import android.os.Bundle;
import android.support.animation.DynamicAnimation;
import android.support.animation.FlingAnimation;
import android.support.animation.FloatPropertyCompat;
import android.support.animation.SpringAnimation;
import android.support.animation.SpringForce;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.android.pkqup.androidnote.R;
import com.android.pkqup.androidnote.abase.BaseFragment;

/**
 * Created by LiuCun on 2017/11/6.<br>
 * Describe
 */

public class BasePhysicsFragment extends BaseFragment {

    private View root;
    private Button btnFling;
    private Button btnSpring;
    private Button btnBounce;
    private Button btnStretch;
    private Button btn_reset;
    private ImageView img;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_simple_demo, container, false);
        btnFling = root.findViewById(R.id.btn_simple_fling);
        btnSpring = root.findViewById(R.id.btn_simple_spring);
        btnBounce = root.findViewById(R.id.btn_simple_bounce);
        btnStretch = root.findViewById(R.id.btn_simple_stretch);
        btn_reset = root.findViewById(R.id.btn_reset);
        img = root.findViewById(R.id.emoji);

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnFling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //抛掷动画
                final FlingAnimation flingAnimation = new FlingAnimation(img, DynamicAnimation.X);
                flingAnimation.setStartVelocity(500f);// 设置初始速度
                flingAnimation.setFriction(0.5f);// 设置摩擦力 0-1
                flingAnimation.start();
            }
        });

        btnSpring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final SpringAnimation springAnimation =
                        new SpringAnimation(img, DynamicAnimation.X);
                final SpringForce springForce = new SpringForce();
                springForce.setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY);
                springForce.setStiffness(SpringForce.STIFFNESS_VERY_LOW);
                springForce.setFinalPosition(img.getX());
                springAnimation.setSpring(springForce);
                springAnimation.setStartVelocity(2000);
                springAnimation.start();
            }
        });

        btnBounce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SpringAnimation springAnimation = new SpringAnimation(img, DynamicAnimation.X);

                SpringForce springForce = new SpringForce();
//                springForce.setFinalPosition(img.getX());
                springForce.setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY);
                springForce.setStiffness(SpringForce.STIFFNESS_LOW);

                springAnimation.setSpring(springForce);
                springAnimation.setStartVelocity(2000);
                springAnimation.start();

                img.setImageResource(R.drawable.ic_sentiment_very_satisfied_black_56dp);
                springAnimation.addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                    @Override
                    public void onAnimationEnd(DynamicAnimation animation, boolean canceled,
                            float value, float velocity) {
                        img.setImageResource(R.drawable.ic_sentiment_neutral_black_56dp);
                    }
                });
            }
        });

        btnStretch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FloatPropertyCompat<View> scale = new FloatPropertyCompat<View>("scale") {

                    @Override
                    public float getValue(View view) {
                        return view.getScaleX();
                    }

                    @Override
                    public void setValue(View view, float value) {
                        view.setScaleX(value);
                        view.setScaleY(value);
                    }
                };

                SpringAnimation stretchAnimation = new SpringAnimation(img, scale);
                stretchAnimation.setMinimumVisibleChange(DynamicAnimation.MIN_VISIBLE_CHANGE_SCALE);

                SpringForce force = new SpringForce(1);
                force.setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY)
                        .setStiffness(SpringForce.STIFFNESS_VERY_LOW);

                stretchAnimation.setSpring(force).setStartVelocity(100);

                stretchAnimation.start();
            }
        });
        return root;
    }

}
