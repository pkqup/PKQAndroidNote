package com.android.pkqup.androidnote.animation_test.fragment;

import android.os.Bundle;
import android.support.animation.SpringAnimation;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.android.pkqup.androidnote.R;
import com.android.pkqup.androidnote.abase.BaseFragment;

/**
 * Created by LiuCun on 2017/11/6.<br>
 * Describe
 */

public class TranslationFragment extends BaseFragment {
    private View root;
    // XY坐标
    private float downX, downY;
    // 调整参数的SeekBar
    private SeekBar dampingSeekBar, stiffnessSeekBar;
    // X/Y方向速度相关的帮助类
    private VelocityTracker velocityTracker;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_translation, container, false);
        stiffnessSeekBar = root.findViewById(R.id.stiffness);
        dampingSeekBar = root.findViewById(R.id.damping);
        velocityTracker = VelocityTracker.obtain();
        final View box = root.findViewById(R.id.box);
        root.findViewById(R.id.content).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        downX = event.getX();
                        downY = event.getY();
                        velocityTracker.addMovement(event);
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        box.setTranslationX(event.getX() - downX);
                        box.setTranslationY(event.getY() - downY);
                        velocityTracker.addMovement(event);
                        return true;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        velocityTracker.computeCurrentVelocity(1000);
                        if (box.getTranslationX() != 0) {
                            SpringAnimation animX =
                                    new SpringAnimation(box, SpringAnimation.TRANSLATION_X, 0);
                            animX.getSpring().setStiffness(getStiffnessSeekBar());
                            animX.getSpring().setDampingRatio(getDampingSeekBar());
                            animX.setStartVelocity(velocityTracker.getXVelocity());
                            animX.start();
                        }
                        if (box.getTranslationY() != 0) {
                            SpringAnimation animY =
                                    new SpringAnimation(box, SpringAnimation.TRANSLATION_Y, 0);
                            animY.getSpring().setStiffness(getStiffnessSeekBar());
                            animY.getSpring().setDampingRatio(getDampingSeekBar());
                            animY.setStartVelocity(velocityTracker.getYVelocity());
                            animY.start();
                        }
                        velocityTracker.clear();
                        return true;
                }
                return false;
            }
        });
        return root;
    }


    /**
     * 从SeekBar获取自定义的强度
     *
     * @return 强度float
     */
    private float getStiffnessSeekBar() {
        return Math.max(stiffnessSeekBar.getProgress(), 1f);
    }

    /**
     * 从SeekBar获取自定义的阻尼
     *
     * @return 阻尼float
     */
    private float getDampingSeekBar() {
        return dampingSeekBar.getProgress() / 100f;
    }
}
