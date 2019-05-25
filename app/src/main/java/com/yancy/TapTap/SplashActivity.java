package com.yancy.TapTap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.yancy.TapTap.MainActivity;
import com.yancy.TapTap.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        隐藏状态栏
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        隐藏标题栏
//        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash);
//        多线程
        new Thread() {
            @Override
            public void run() {
                try {
//                    休眠3秒
                    sleep(3000);
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
//                    关闭
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
//            启动
        }.start();
    }
}
