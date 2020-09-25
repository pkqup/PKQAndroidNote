package com.android.pkqup.androidnote.glide_test;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.android.pkqup.androidnote.R;
import com.android.pkqup.androidnote.abase.BaseActivity;
import com.android.pkqup.androidnote.widget.RoundImageView;
import com.bumptech.glide.Glide;

/**
 * Created by LiuCun on 2017/11/30.<br>
 * Describe
 */

public class GlideActivity extends BaseActivity {

    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_glide);

        img = findViewById(R.id.img);


        String[] images =
                new String[] {"http://img.my.csdn.net/uploads/201407/26/1406383299_1976.jpg",
                        "http://img.my.csdn.net/uploads/201407/26/1406383291_6518.jpg",
                        "http://img.my.csdn.net/uploads/201407/26/1406383291_8239.jpg",
                        "http://img.my.csdn.net/uploads/201407/26/1406383290_9329.jpg",
                        "http://img.my.csdn.net/uploads/201407/26/1406383290_1042.jpg",
                        "http://img.my.csdn.net/uploads/201407/26/1406383275_3977.jpg",
                        "http://img.my.csdn.net/uploads/201407/26/1406383265_8550.jpg",
                        "http://img.my.csdn.net/uploads/201407/26/1406383264_3954.jpg",
                        "http://img.my.csdn.net/uploads/201407/26/1406383264_4787.jpg",
                        "http://img.my.csdn.net/uploads/201407/26/1406383264_8243.jpg",
                        "http://img.my.csdn.net/uploads/201407/26/1406383248_3693.jpg",};

        // /storage/emulated/0/DCIM/Camera/IMG_20170607_055114.jpg

        // GlideApp.with(this).load("http://img.my.csdn.net/uploads/201407/26/1406383291_8239.jpg")
        // .into(img);

        // 直接加载本地图片
        // Glide.with(this).load("/storage/emulated/0/DCIM/Camera/IMG_20170607_055114.jpg").into(img);
        Glide.with(this).asBitmap().load(images[0]).into(img);
        String cacheSize = GlideCacheUtil.getInstance().getCacheSize(this);

        Log.e("--cacheSize---", cacheSize + "");

        // All properties can be set in xml.
        RoundImageView iv0 = (RoundImageView) findViewById(R.id.image0);

        // You can set image with resource id.
        RoundImageView iv1 = (RoundImageView) findViewById(R.id.image1);
        iv1.setScaleType(ImageView.ScaleType.CENTER_CROP);
        iv1.setOval(true);
        iv1.setImageResource(R.mipmap.photo_cheetah);

        // Also, You can set image with Picasso.
        // This is a normal rectangle imageview.
        RoundImageView iv2 = (RoundImageView) findViewById(R.id.image2);
        iv1.setScaleType(ImageView.ScaleType.CENTER);
        Glide.with(this).load(images[2]).into(iv2);

        // Of course, you can set round radius in code.
        RoundImageView iv3 = (RoundImageView) findViewById(R.id.image3);
        iv3.setImageDrawable(getResources().getDrawable(R.mipmap.photo3));
        iv3.setCornerRadiiDP(4, 4, 0, 0);
    }
}
