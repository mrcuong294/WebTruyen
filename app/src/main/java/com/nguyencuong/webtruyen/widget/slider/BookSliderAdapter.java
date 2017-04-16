package com.nguyencuong.webtruyen.widget.slider;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.nguyencuong.webtruyen.model.Slider;

import java.util.ArrayList;
import java.util.List;

public class BookSliderAdapter extends FragmentStatePagerAdapter {
    private static final String TAG = BookSliderAdapter.class.getSimpleName();
    private final Context context;
    private ArrayList<Slider> list = new ArrayList<>();

    public BookSliderAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return BookSliderFragment.newInstant(list.get(position));
    }

    @Override
    public float getPageWidth(int position) {
        /*
        if (context.getResources().getBoolean(R.bool.isTablet)) {
            return 0.8f;
        } else {
            return 1f;
        }*/
        return 1f;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    public void setListSlider(List<Slider> listSlider) {
        list.clear();
        list.addAll(listSlider);
        notifyDataSetChanged();
    }
}
