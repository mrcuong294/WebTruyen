package com.nguyencuong.webtruyen.widget.slider;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.nguyencuong.webtruyen.model.Slider;

import java.util.ArrayList;

public class BookSliderAdapter extends FragmentStatePagerAdapter {
    private static final String TAG = BookSliderAdapter.class.getSimpleName();
    private final Context context;
    private final ArrayList<Slider> list;

    public BookSliderAdapter(Context context, FragmentManager fm, ArrayList<Slider> list) {
        super(fm);
        this.context = context;
        this.list = list;
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

    public void addMultiItem(ArrayList<Slider> listSlider) {
        list.clear();
        list.addAll(listSlider);
        notifyDataSetChanged();
    }
}
