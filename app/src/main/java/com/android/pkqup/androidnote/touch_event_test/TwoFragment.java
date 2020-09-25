package com.android.pkqup.androidnote.touch_event_test;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.pkqup.androidnote.R;
import com.android.pkqup.androidnote.abase.BaseFragment;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;

/**
 * @author liucun
 * @date 2020/9/21 19:26
 * @description
 */
public class TwoFragment extends BaseFragment {
    private ArrayList<Fragment> mFragments;
    private final String[] mTitles = {"1", "2", "3"};
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.my_fragment, container, false);
            initView();
        }
        return rootView;
    }

    private void initView() {
        mFragments = new ArrayList<>();
        mFragments.add(new ChildFragment());
//        mFragments.add(new ChildFragment());
//        mFragments.add(new ChildFragment());
        SlidingTabLayout tabLayout = rootView.findViewById(R.id.tabLayout2);
        ViewPager viewPager = rootView.findViewById(R.id.viewPager2);
        MyPagerAdapter mAdapter = new MyPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(mAdapter);
        tabLayout.setViewPager(viewPager);
        tabLayout.getTitleView(0).getPaint().setFakeBoldText(true);
        tabLayout.getTitleView(0).invalidate();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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

