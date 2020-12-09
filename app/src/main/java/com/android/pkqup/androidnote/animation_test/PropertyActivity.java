package com.android.pkqup.androidnote.animation_test;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.widget.Button;
import android.widget.TextView;

import com.android.pkqup.androidnote.R;
import com.android.pkqup.androidnote.abase.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by LiuCun on 2017/11/1.<br>
 * Describe
 */

public class PropertyActivity extends BaseActivity {

    @BindView(R.id.bt_alpha)
    Button bt_alpha;
    @BindView(R.id.bt_scale)
    Button bt_scale;
    @BindView(R.id.bt_rotate)
    Button bt_rotate;
    @BindView(R.id.bt_translate)
    Button bt_translate;
    @BindView(R.id.bt_reset)
    Button bt_reset;
    @BindView(R.id.tv_anim)
    TextView anim;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);
    }

    @OnClick(R.id.bt_alpha)
    void alphaAnim() {
        // 第一种方式，使用ValueAnimator和ObjectAnimator
/*        ValueAnimator valueAnimator = ValueAnimator.ofFloat(1, 0.5f);
        valueAnimator.setDuration(1000);
        valueAnimator.start();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                anim.setAlpha((Float) animation.getAnimatedValue());
                Log.e("----", "onAnimationUpdate: " + animation.getAnimatedValue());
            }
        });
      */
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(anim, "alpha", 0.5f,0,0.5f,1);
        objectAnimator.setDuration(3000);
        objectAnimator.start();
    }

    @OnClick(R.id.bt_reset)
    void reset() {
        anim.animate().translationX(0);
        anim.animate().scaleX(1);
        anim.animate().scaleY(1);
        anim.animate().rotation(0);
        anim.animate().rotationX(0);
        anim.animate().rotationY(0);
    }

    @OnClick(R.id.bt_scale)
    void scaleAnim() {
        // 第二种方式，使用view.animate()得到 ViewPropertyAnimator 对象，再来做动画操作
        anim.animate().scaleX(2);//以控件本身中心放大
        anim.animate().scaleY(2);
    }

    @OnClick(R.id.bt_rotate)
    void rotateAnim() {
        // 第二种方式，使用view.animate()得到 ViewPropertyAnimator 对象，再来做动画操作
//        anim.animate().rotation(180);//以控件本身为中心 平面 旋转
//        anim.animate().rotationX(45);//以控件X轴为中心 轴心 旋转
        anim.animate().rotationY(45);//以控件Y轴为中心 轴心 旋转
    }


    @OnClick(R.id.bt_translate)
    void translateAnim() {
        // 第二种方式，使用view.animate()得到 ViewPropertyAnimator 对象，再来做动画操作
//        anim.animate().translationX(20);// 相对于控件原始位置向右偏移20个像素（多次运行只会偏移一次）
        anim.animate().translationXBy(20);//相对于控件当前位置向右偏移20个像素（多次运行会多次偏移）
    }

}
