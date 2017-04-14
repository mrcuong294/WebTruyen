package com.nguyencuong.webtruyen.screen.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;

import com.nguyencuong.webtruyen.BaseFragment;
import com.nguyencuong.webtruyen.R;
import com.nguyencuong.webtruyen.data.remote.services.HomeServices;
import com.nguyencuong.webtruyen.model.Slider;
import com.nguyencuong.webtruyen.util.LogUtils;
import com.nguyencuong.webtruyen.widget.slider.BookSliderView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class HomeFragment extends BaseFragment implements HomeContract.View {
    private static final String TAG = HomeFragment.class.getSimpleName();

    private HomeContract.Presenter presenter;

    LinearLayout contentLayout;

    BookSliderView mSliderView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.d("HomeFragment", "onCreate");
        if (contentLayout == null) {
            LogUtils.d("HomeFragment", "onCreate contentLayout NULL");
        }
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        contentLayout = (LinearLayout) view.findViewById(R.id.home_content_layout);
        new HomePresenter(this, new HomeServices(getActivity()));
        LogUtils.d("HomeFragment", "onViewCreated");
        if (contentLayout == null) {
            LogUtils.d("HomeFragment", "onViewCreated contentLayout NULL");
        }
    }

    /**
     * Called when the Fragment is visible to the user.  This is generally
     * tied to {@link android.app.Activity#onStart() Activity.onStart} of the containing
     * Activity's lifecycle.
     */
    @Override
    public void onStart() {
        super.onStart();
        LogUtils.d("HomeFragment", "onStart");
        if (contentLayout == null) {
            LogUtils.d("HomeFragment", "onStart contentLayout NULL");
        }
        if (contentLayout.getChildCount() == 0) {
            presenter.onStart();
        }
    }

    /**
     * Called when the fragment is visible to the user and actively running.
     * This is generally
     * tied to {@link android.app.Activity#onResume() Activity.onResume} of the containing
     * Activity's lifecycle.
     */
    @Override
    public void onResume() {
        super.onResume();
        if (mSliderView != null) {
            mSliderView.startSlider();
        }
    }

    /**
     * Called when the Fragment is no longer resumed.  This is generally
     * tied to {@link android.app.Activity#onPause() Activity.onPause} of the containing
     * Activity's lifecycle.
     */
    @Override
    public void onPause() {
        if (mSliderView != null) {
            mSliderView.stopSlider();
        }
        super.onPause();
    }

    /**
     * Called when the Fragment is no longer started.  This is generally
     * tied to {@link android.app.Activity#onStop() Activity.onStop} of the containing
     * Activity's lifecycle.
     */
    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        presenter.onDestroy();
        super.onDestroyView();
    }

    @Override
    public void setPresenter(HomeContract.Presenter Presenter) {
        presenter = Presenter;
    }

    @Override
    public void showLoading(boolean show) {
        dialogLoading.show(show);
    }

    @Override
    public void showMsgError(boolean show, String msg) {
        showToastError(msg);
    }

    @Override
    public void showMsgToast(String msg) {

    }

    @Override
    public void addSliderView(ArrayList<Slider> sliders) {
        if (mSliderView == null) {
            mSliderView = new BookSliderView(getActivity());
            mSliderView.setupAdapter(getFragmentManager());
        }
        mSliderView.setListSliders(sliders);

        contentLayout.removeView(mSliderView);
        contentLayout.addView(mSliderView, 0);

        mSliderView.startSlider();
    }
}
