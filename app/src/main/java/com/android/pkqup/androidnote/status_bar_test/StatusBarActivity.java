package com.android.pkqup.androidnote.status_bar_test;

import android.os.Bundle;
import androidx.core.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.android.pkqup.androidnote.R;
import com.android.pkqup.androidnote.abase.BaseActivity;
import com.jaeger.library.StatusBarUtil;

import java.util.Random;

import butterknife.BindView;

/**
 * Created by LiuCun on 2017/11/22.<br>
 * Describe
 */

public class StatusBarActivity extends BaseActivity {

    @BindView(R.id.content)
    ImageView contentLayout;

    @BindView(R.id.need_view)
    LinearLayout need_view;
    @BindView(R.id.btn_change_color)
    Button btn_change_color;
    @BindView(R.id.chb_translucent)
    CheckBox mChbTranslucent;
    @BindView(R.id.sb_change_alpha)
    SeekBar mSbChangeAlpha;
    @BindView(R.id.tv_status_alpha)
    TextView mTvStatusAlpha;

    private int alpha;

    private int color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        color = ContextCompat.getColor(this, R.color.colorAccent);
        alpha = StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA;

        setContentView(R.layout.activity_status_bar);

        initRandomColor();

        initCheckBox();

        initSeekBar();
        contentLayout.setBackground(
                ContextCompat.getDrawable(StatusBarActivity.this, R.mipmap.bg_monkey));
        StatusBarUtil.setTranslucentForImageView(StatusBarActivity.this, alpha,
                need_view);
    }

    private void initRandomColor() {
        btn_change_color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                color = 0xff000000 | random.nextInt(0xffffff);
                StatusBarUtil.setColor(StatusBarActivity.this, color, alpha);
            }
        });
    }

    private void initCheckBox() {
        mChbTranslucent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mChbTranslucent.isChecked()) {
                    contentLayout.setBackground(
                            ContextCompat.getDrawable(StatusBarActivity.this, R.mipmap.bg_monkey));
                    StatusBarUtil.setTranslucentForImageView(StatusBarActivity.this, alpha,
                            need_view);
                } else {
                    contentLayout.setBackground(null);
                    StatusBarUtil.setColor(StatusBarActivity.this, color, alpha);
                }
            }
        });
    }

    private void initSeekBar() {
        mSbChangeAlpha.setMax(255);
        mSbChangeAlpha.setProgress(StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA);
        mSbChangeAlpha.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                alpha = progress;
                if (mChbTranslucent.isChecked()) {
                    StatusBarUtil.setTranslucentForImageView(StatusBarActivity.this, alpha,
                            need_view);
                } else {
                    StatusBarUtil.setColor(StatusBarActivity.this, color, alpha);
                }
                mTvStatusAlpha.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    @Override
    public void setStatusBar() {

    }
}
