package com.nguyencuong.webtruyen.screen.splashscreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.nguyencuong.webtruyen.BaseActivity;
import com.nguyencuong.webtruyen.R;
import com.nguyencuong.webtruyen.screen.mainscreen.MainActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }, 2000);
    }
}
