package com.android.pkqup.androidnote.fragment_test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.pkqup.androidnote.R;

/**
 * Created by LiuCun on 2017/11/7.<br>
 * Describe
 */

public class BaseLazyLoadFragment extends Fragment {

    private boolean mIsInit = false;//数据是否加载完成
    private boolean mIsPrepared = false;//UI是否准备完成

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mRootView = inflater.inflate(R.layout.fragment_first, container, false);
        mIsPrepared = true;
        lazyLoad();
        return mRootView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            lazyLoad();
        }
    }

    public void lazyLoad() {
        if (getUserVisibleHint() && mIsPrepared && !mIsInit) {
            // 异步初始化，在初始化后显示正常UI
            loadData();
        }
    }

    private void loadData() {
        new Thread() {
            public void run() {
                // 1. 加载数据
                // 2. 更新UI
                // 3. mIsInit = true
                mIsInit = true;
            }
        }.start();
    }

}
