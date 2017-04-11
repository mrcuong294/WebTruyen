package com.nguyencuong.webtruyen;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


import com.nguyencuong.webtruyen.widget.dialog.DialogLoading;

import butterknife.ButterKnife;

/**
 * Base Activity;
 *
 * Created by Mr Cuong on 2/18/2017.
 */

public class BaseActivity  extends AppCompatActivity{

    protected DialogLoading dialogLoading;

    protected boolean isScreenLand;

    protected boolean isLoadingBackPressExit = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getResources().getBoolean(R.bool.isTablet)) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            isScreenLand = true;
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            isScreenLand = false;
        }

        dialogLoading = new DialogLoading(this);
        dialogLoading.setOnPopupLoadingListener(new DialogLoading.OnPopupLoadingListener() {
            @Override
            public void onBackPressed() {
                if (isLoadingBackPressExit) {
                    finish();
                }
            }
        });
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        dialogLoading.dismiss();
        dialogLoading = null;
        super.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        isScreenLand = newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    protected void showToast(String mes) {
        Toast.makeText(getApplicationContext(), mes + "", Toast.LENGTH_SHORT).show();
    }

    protected void showToast(@StringRes int messageId) {
        showToast(getString(messageId));
    }
}
