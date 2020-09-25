package com.android.pkqup.androidnote.touch_event_test;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.pkqup.androidnote.R;
import com.android.pkqup.androidnote.abase.BaseFragment;

/**
 * @author liucun
 * @date 2020/9/21 18:54
 * @description
 */
public class ChildFragment extends BaseFragment {

    private View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.child_fragment, container, false);
        }
        return rootView;
    }
}
