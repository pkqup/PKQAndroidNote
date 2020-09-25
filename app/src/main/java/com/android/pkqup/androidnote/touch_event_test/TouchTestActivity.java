package com.android.pkqup.androidnote.touch_event_test;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.TextView;

import com.android.pkqup.androidnote.R;
import com.android.pkqup.androidnote.abase.BaseActivity;
import com.android.pkqup.androidnote.databinding.ActivityTouchDispatchBinding;

import java.util.ArrayList;

/**
 * Created by LiuCun on 2017/11/8.<br>
 * Describe
 */

public class TouchTestActivity extends BaseActivity {
    private ArrayList<Fragment> mFragments;
    private final String[] mTitles = {"资讯", "圈子", "赛事"};
    private MyPagerAdapter mAdapter;

    private Button button;
    private Button bt_my_test;
    private TextView textView;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_dispatch);

//        ActivityMainBinding  mBinding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(mBinding.getRoot());


        ActivityTouchDispatchBinding binding = ActivityTouchDispatchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    /*    bt_my_test = findViewById(R.id.bt_my_test);
        button = findViewById(R.id.bt_test);
        textView = findViewById(R.id.textView);


        textView.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e("getLeft", textView.getLeft() + "");
                Log.e("getX", textView.getX() + "");
                Log.e("getY", textView.getY() + "");
                Log.e("getTranslationX", textView.getTranslationX() + "");
                Log.e("getTranslationY", textView.getTranslationY() + "");

                int[] location = new int[2];
                textView.getLocationOnScreen(location);
            }
        }, 10);
*/


      /* button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(TAG, "onTouch execute, action " + event.getAction());
                return false;
            }
        });
*/

      /*  bt_my_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick execute");
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick execute");
            }
        });*/

     /*   textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewHelper.setTranslationX(textView, 10f);

                textView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("--------", "--------");
                        Log.e("getLeft", textView.getLeft() + "");
                        Log.e("getX", textView.getX() + "");
                        Log.e("getY", textView.getY() + "");
                        Log.e("getTranslationX", textView.getTranslationX() + "");
                        Log.e("getTranslationY", textView.getTranslationY() + "");
                    }
                }, 10);
            }
        });*/

     /*   mFragments = new ArrayList<>();
        mFragments.add(new MyFragment());
        mFragments.add(new MyFragment());
        mFragments.add(new MyFragment());
        SlidingTabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(4);
        mAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mAdapter);
        tabLayout.setViewPager(viewPager);
        tabLayout.getTitleView(0).getPaint().setFakeBoldText(true);
        tabLayout.getTitleView(0).invalidate();*/

/*        LinearLayout llContent = findViewById(R.id.llContent);
        ViewHelper.setTranslationY(llContent, -100f);*/


        /*
        final MoveTextView tvMove = findViewById(R.id.tvMove);

        findViewById(R.id.tvClick).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvMove.request();
            }
        });

        tvMove.animate().translationX(100).setDuration(500);
        */


    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("Activity", "dispatchTouchEvent" + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("Activity", "onTouchEvent" + event.getAction());
        return super.onTouchEvent(event);
    }


    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
}
