package com.nguyencuong.webtruyen.ui.activity.main;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.nguyencuong.webtruyen.BaseActivity;
import com.nguyencuong.webtruyen.R;
import com.nguyencuong.webtruyen.ui.fragment.home.HomeFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enableButterKnight = false;
        setContentView(R.layout.activity_main);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.addToBackStack(null);
        transaction.replace(R.id.main_frameLayout, new HomeFragment());
        transaction.commit();
    }
}
