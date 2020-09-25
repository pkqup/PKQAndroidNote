package com.android.pkqup.androidnote.touch_event_test

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.LinearLayout
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.ScreenUtils
import com.blankj.utilcode.util.SizeUtils
import com.nineoldandroids.view.ViewHelper

/**
 * @author liucun
 * @date 2020/9/23 14:35
 * @description 在屏幕内跟随手指滑动的View
 */
class MoveTextView @JvmOverloads constructor(
        context: Context?,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    private var screenHeight = 0
    private var screenWidth = 0
    private var statusBarHeight = 0

    init {
        screenHeight = ScreenUtils.getScreenHeight()
        screenWidth = ScreenUtils.getScreenWidth()
        statusBarHeight = BarUtils.getStatusBarHeight()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        Log.e("MoveTextView", "onMeasure")
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        Log.e("MoveTextView", "onLayout")
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        Log.e("MoveTextView", "onDraw")
    }

    fun updateText(){
//        layoutParams.width = SizeUtils.dp2px(200f)
        setBackgroundColor(Color.parseColor("#880044"))
        text = "更新"
    }

    fun request(){
        layoutParams.width = SizeUtils.dp2px(200f)
        requestLayout()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        post {
            if (parent is LinearLayout) {
                val height = (parent as LinearLayout).height
                Log.e("parent height", height.toString())
            }
        }
    }


    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
    }

    var mLastX = 0
    var mLastY = 0

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.rawX
        val y = event.rawY
        Log.e("x", x.toString())


        if (event.action == MotionEvent.ACTION_MOVE) {
            val deltaX = x - mLastX;
            val deltaY = y - mLastY;

            var translationX = ViewHelper.getTranslationX(this) + deltaX
            var translationY = ViewHelper.getTranslationY(this) + deltaY

            if (translationX + left < 0) {
                translationX = -left.toFloat()
            }

            if (translationX + right > screenWidth) {
                translationX = screenWidth.toFloat() - right
            }

            if (translationY + top < 0) {
                translationY = -top.toFloat()
            }

            if (translationY + bottom > screenHeight - statusBarHeight) {
                translationY = screenHeight.toFloat() - bottom - statusBarHeight
            }

            ViewHelper.setTranslationX(this, translationX)
            ViewHelper.setTranslationY(this, translationY)
        }

        mLastX = x.toInt()
        mLastY = y.toInt()
        Log.e("mLastY", mLastY.toString())
        return true
    }


}