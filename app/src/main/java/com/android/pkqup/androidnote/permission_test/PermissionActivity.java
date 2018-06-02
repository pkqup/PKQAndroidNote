package com.android.pkqup.androidnote.permission_test;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;

import com.android.pkqup.androidnote.R;
import com.android.pkqup.androidnote.abase.BaseActivity;

/**
 * Created by LiuCun on 2017/11/16.<br>
 * Describe
 */

public class PermissionActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

        findViewById(R.id.bt_phone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel://123456"));
                startActivity(intent);

            }
        });

        findViewById(R.id.bt_camera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断权限
                if (PackageManager.PERMISSION_GRANTED != checkSelfPermission(
                        Manifest.permission.CAMERA)) {
                    // 无权限
                    if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                        //是否需要给出需要此权限的提示
                        new AlertDialog.Builder(PermissionActivity.this).setTitle("标题")
                                .setMessage("需要权限")
                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //申请权限
                                        requestCameraPermission();
                                    }
                                }).show();

                    } else {
                        //申请权限
                        requestCameraPermission();
                    }
                } else {
                    //有权限
                    openCamera();
                }
            }
        });
    }

    //申请权限
    private void requestCameraPermission() {
        requestPermissions(new String[] {Manifest.permission.CAMERA}, 100);
    }

    //打开相机
    private void openCamera() {
        Intent intent = new Intent();
        intent.setAction("android.media.action.STILL_IMAGE_CAMERA");
        startActivity(intent);
    }

    //打开设置界面
    private void openSetting(int requestCode) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, requestCode);
    }


    @Override
    public void onRequestPermissionsResult(final int requestCode, String[] permissions,
            int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (PackageManager.PERMISSION_GRANTED == grantResults[0]) {
                openCamera();
            } else {
                if (!shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {

                    new AlertDialog.Builder(PermissionActivity.this).setTitle("权限申请失败")
                            .setMessage("我们需要的一些权限被您拒绝或者系统发生错误申请失败，请您到设置界面手动授权，否则功能无法正常使用！")
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    openSetting(requestCode);
                                }
                            }).show();
                }
            }
        }
    }

}
