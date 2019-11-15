package com.example.juandie_hua.ui.me;

import android.Manifest;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

import com.example.juandie_hua.R;
import com.example.juandie_hua.app.BaseActivity;
import com.example.juandie_hua.app.Constant;
import com.example.juandie_hua.utils.StatusBarUtils;
import com.example.juandie_hua.view.CusPopWindow;

import org.xutils.common.util.DensityUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 关注服务号界面
 *
 * @author ctt
 */
public class OfficialAccountActivity extends BaseActivity {

    private CusPopWindow popWindow;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitleContentView(R.layout.activity_follow_official_account);
        StatusBarUtils.with(this).setBarColor(R.color.white_fff);

        this.getTitleView().setTitleText(getResources().getString(R.string.follow_official_account));

        findViewById(R.id.ui_copy_service_account).setOnClickListener(listener);
        findViewById(R.id.save_service_account_qrcode).setOnClickListener(listener);
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.save_service_account_qrcode:
                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.attention_img);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (ContextCompat.checkSelfPermission(OfficialAccountActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(OfficialAccountActivity.this, PERMISSIONS_STORAGE,
                                    1);
                        } else {
                            saveImageToGallery(OfficialAccountActivity.this, bitmap);
                        }
                    }
                    break;
                case R.id.ui_copy_service_account:
                    ClipboardManager cmb = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                    cmb.setText(getResources().getString(R.string.service_account));
                    toast("服务号已复制到剪切板");
                    break;
            }
        }
    };

    private void showDialog() {
        View view = LayoutInflater.from(OfficialAccountActivity.this).inflate(R.layout.item_pop_save_qrcode, null);

        popWindow = new CusPopWindow.PopupWindowBuilder(OfficialAccountActivity.this)
                .setView(view)
                .enableBackgroundDark(true) //弹出popWindow时，背景是否变暗
                .setBgDarkAlpha(0.7f) // 控制亮度
                .size(DensityUtil.getScreenWidth() / 3 * 2, DensityUtil.getScreenHeight() / 5 * 3)
                .create();
        popWindow.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);

        view.findViewById(R.id.pop_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popWindow.dismiss();
            }
        });

        view.findViewById(R.id.pop_to_wechart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toWeChatScanDirect();
            }
        });
    }

    /**
     * 唤起微信扫一扫
     */
    public void toWeChatScanDirect() {
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI"));
            intent.putExtra("LauncherUI.From.Scaner.Shortcut", true);
//            intent.setFlags(335544320);
            intent.setAction("android.intent.action.VIEW");
            startActivity(intent);
        } catch (Exception e) {
        }
    }


    private String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    private void saveImageToGallery(Context context, Bitmap bitmap) {

        // 首先保存图片
        File appDir = new File(Constant.PATH, Constant.ALBUM);
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        showDialog();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            // 其次把文件插入到系统图库
            try {
                MediaStore.Images.Media.insertImage(getApplicationContext().getContentResolver(), file.getAbsolutePath(), fileName, null);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            // 最后通知图库更新
            if (Build.VERSION.SDK_INT > 23) { // 判断SDK版本是不是4.4或者高于4.4
                String[] paths = new String[]{file.getAbsolutePath()};
                MediaScannerConnection.scanFile(OfficialAccountActivity.this, paths, null, null);
            } else {
                final Intent intent;
                if (file.isDirectory()) {
                    intent = new Intent(Intent.ACTION_MEDIA_MOUNTED);
                    intent.setClassName("com.android.providers.media", "com.android.providers.media.MediaScannerReceiver");
                    intent.setData(Uri.fromFile(Environment.getExternalStorageDirectory()));
                } else {
                    intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    intent.setData(Uri.fromFile(file));
                }
                context.sendBroadcast(intent);
            }
        }

    }
}
