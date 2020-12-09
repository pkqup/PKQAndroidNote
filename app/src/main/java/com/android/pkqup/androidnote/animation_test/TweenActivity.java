package com.android.pkqup.androidnote.animation_test;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.util.Log;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
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

public class TweenActivity extends BaseActivity {

    @BindView(R.id.bt_alpha)
    Button bt_alpha;
    @BindView(R.id.bt_scale)
    Button bt_scale;
    @BindView(R.id.bt_rotate)
    Button bt_rotate;
    @BindView(R.id.bt_translate)
    Button bt_translate;
    @BindView(R.id.bt_set)
    Button bt_set;
    @BindView(R.id.tv_anim)
    TextView anim;
    private long startTime;
    private long endTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tween);
    }

//    以下为Andorid所提供的所有插值器：
//
//            1、AccelerateDecelerateInterpolator：开始和结束速度慢，中间部分加速。
//            2、AccelerateInterpolator：开始缓慢，然后加速。
//            3、AnticipateInterpolator: 开始后退，然后前进。
//            4、AnticipateOvershootInterpolator: 开始后退，然后前进，直到超出目标值，再后退至目标值。
//            5、BounceInterpolator：在结束时弹跳。
//            6、CycleInterpolator：在指定数量的周期内重复动画，速度变化遵循正弦规律。
//            7、DecelerateInterpolator：开始加速，结束缓慢。
//            8、LinearInterpolator：匀速。
//            9、OvershootInterpolator：前进，直到超出目标值，再后退至目标值。
//            10、PathInterpolator：根据路径变化改变速率。


    @OnClick(R.id.bt_alpha)
    void alphaAnim() {
        // android:fromAlpha： 动画开始前控件的透明度，取值0.0-1.0，从透明到不透明。
        // android:toAlpha： 动画结束时控件的透明度，取值0.0-1.0，从透明到不透明。
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.5f);
        alphaAnimation.setDuration(1000);// 动画时长，单位毫秒
        alphaAnimation.setFillAfter(false);// 动画结束后，是否保存在最后一帧
        alphaAnimation.setRepeatCount(1);// 重复次数（若设置为1，则总共运行2次，耗时2000毫秒）
        alphaAnimation.setRepeatMode(Animation.REVERSE);// 重复模式，reverse(倒序)
        // alphaAnimation.setRepeatMode(Animation.RESTART);//重复模式，restart(重复)
        alphaAnimation.setStartOffset(100);// 延迟多少毫秒执行
        alphaAnimation.setInterpolator(new AccelerateDecelerateInterpolator());//设置差值器，动画的速率变化
        anim.startAnimation(alphaAnimation);
        startTime = 0;
        endTime = 0;
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                startTime = System.currentTimeMillis();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                endTime = System.currentTimeMillis();
                Log.e("-----------", (endTime - startTime) + "");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
    }


    @OnClick(R.id.bt_scale)
    void scaleAnim() {
     /*
            参数解释：
                第一个参数：X轴水平缩放起始位置的大小（fromX）。1代表正常大小
                第二个参数：X轴水平缩放完了之后（toX）的大小，0代表完全消失了
                第三个参数：Y轴垂直缩放起始时的大小（fromY）
                第四个参数：Y轴垂直缩放结束后的大小（toY）
                第五个参数：pivotXType为动画在X轴相对于物件位置类型
                第六个参数：pivotXValue为动画相对于物件的X坐标的开始位置
                第七个参数：pivotXType为动画在Y轴相对于物件位置类型
                第八个参数：pivotYValue为动画相对于物件的Y坐标的开始位置

               （第五个参数，第六个参数），（第七个参数,第八个参数）是用来指定缩放的中心点
                0.5f代表从中心缩放
         */
        //ScaleAnimation scaleAnimation = new ScaleAnimation(1,2,1,2);//参数为相对比列,放大的中心点为控件的左上点
        ScaleAnimation scaleAnimation = new ScaleAnimation(1,2,1,2,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);//参数为相对比列,放大的中心点为控件的左上点
        scaleAnimation.setDuration(1500);
        scaleAnimation.setRepeatCount(1);
        scaleAnimation.setRepeatMode(Animation.REVERSE);
        anim.startAnimation(scaleAnimation);
    }


    @OnClick(R.id.bt_rotate)
    void rotateAnim() {

//        从fromDegress角度转到toDegress角度(正数为顺时针，负数为逆时针)。
//        android:fromDegrees： 动画开始前的角度。
//        android:toDegrees： 动画结束时的角度。
//        android:pivotX： 旋转动画围绕X方向的中心点，当为数值时，表明是屏幕上的绝对坐标值，
//                         当为百分数（如50%）时，为相对自己本身控件的比例值，当为百分数加p（如50%p）时，为相对父布局的比例值。
//        android:pivotY： 旋转动画围绕Y方向的中心点，当为数值时，表明是屏幕上的绝对坐标值，
//                         当为百分数（如50%）时，为相对自己本身控件的比例值，当为百分数加p（如50%p）时，为相对父布局的比例值。
        RotateAnimation rotateAnimation = new RotateAnimation(0,360,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        rotateAnimation.setDuration(1500);
        rotateAnimation.setRepeatCount(1);
        rotateAnimation.setRepeatMode(Animation.REVERSE);
        anim.startAnimation(rotateAnimation);
    }


    @OnClick(R.id.bt_translate)
    void translateAnim() {
//        android:fromXDelta：动画开始点的X轴坐标。有三种表示方式，
//                             一、是纯数字，使用绝对位置（比如"50"，表示以当前View左上角坐标加50px作为X坐标）；
//                             二、是百分数，相对于控件本身定位（比如"50%"，表示以当前View的左上角加上当前View宽度的50%作为X坐标）；
//                             三、是百分数p，相对于父控件定位（比如"50%p"，表示以当前View的左上角加上父控件宽度的50%做为X坐标）。
//        android:fromYDelta：动画开始点的Y轴坐标。
//        android:toXDelta：动画结束点的X轴坐标。
//        android:toYDelta：动画结束点的Y轴坐标。
        TranslateAnimation translateAnimation = new TranslateAnimation(0,0,0,300);//从当前位置，向下和向右各平移300px
//        TranslateAnimation translateAnimation = new TranslateAnimation(// 从屏幕底部进入的动画
//                Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f,
//                Animation.RELATIVE_TO_PARENT, 1.0f, Animation.RELATIVE_TO_PARENT, 0.0f);
        translateAnimation.setDuration(1500);
        translateAnimation.setRepeatCount(1);
        translateAnimation.setRepeatMode(Animation.REVERSE);
        anim.startAnimation(translateAnimation);

    }


    @OnClick(R.id.bt_set)
    void setAnim() {
        //AnimationSet(boolean shareInterpolator)：子动画是否共享一个插值器。
        AnimationSet animationSet = new AnimationSet(false);

        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.5f);
        alphaAnimation.setDuration(1000);// 动画时长，单位毫秒
//        alphaAnimation.setRepeatCount(1);// 重复次数（若设置为1，则总共运行2次，耗时2000毫秒）
//        alphaAnimation.setRepeatMode(Animation.REVERSE);// 重复模式，reverse(倒序)

        ScaleAnimation scaleAnimation = new ScaleAnimation(1,2,1,2,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);//参数为相对比列,放大的中心点为控件的左上点
        scaleAnimation.setDuration(1000);
//        scaleAnimation.setRepeatCount(1);
//        scaleAnimation.setRepeatMode(Animation.REVERSE);
        scaleAnimation.setStartOffset(1000);

        RotateAnimation rotateAnimation = new RotateAnimation(0,360,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        rotateAnimation.setDuration(1000);
//        rotateAnimation.setRepeatCount(1);
//        rotateAnimation.setRepeatMode(Animation.REVERSE);
        rotateAnimation.setStartOffset(2000);

        TranslateAnimation translateAnimation = new TranslateAnimation(0,0,0,300);//从当前位置，向下和向右各平移300px
        translateAnimation.setDuration(1000);
//        translateAnimation.setRepeatCount(1);
//        translateAnimation.setRepeatMode(Animation.REVERSE);
        translateAnimation.setStartOffset(3000);

        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(rotateAnimation);
        animationSet.addAnimation(translateAnimation);
        anim.startAnimation(animationSet);


    }

}
