package com.nguyencuong.webtruyen.ui.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nguyencuong.webtruyen.BaseFragment;
import com.nguyencuong.webtruyen.R;
import com.nguyencuong.webtruyen.data.remote.services.HomeServices;
import com.nguyencuong.webtruyen.model.Book;
import com.nguyencuong.webtruyen.model.Slider;
import com.nguyencuong.webtruyen.ui.activity.bookdetail.BookDetailActivity;
import com.nguyencuong.webtruyen.util.DensityUtils;
import com.nguyencuong.webtruyen.util.LogUtils;
import com.nguyencuong.webtruyen.widget.homeblock.HomeBlockHorizontalView;
import com.nguyencuong.webtruyen.widget.homeblock.HomeBlockRecyclerAdapter;
import com.nguyencuong.webtruyen.widget.homeblock.HomeBlockView;
import com.nguyencuong.webtruyen.widget.slider.BookSliderView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class HomeFragment extends BaseFragment implements HomeContract.View {

    private static final String TAG = HomeFragment.class.getSimpleName();

    private HomeContract.Presenter presenter;

    @BindView(R.id.home_scroll_layout)
    NestedScrollView mNestedScrollView;

    @BindView(R.id.home_content_layout)
    LinearLayout contentLayout;

    private BookSliderView mSliderView;

    private Animation animAddView;

    private Handler mHandler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = new Handler();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mNestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                int contentH = contentLayout.getHeight();
                int scrollH = mNestedScrollView.getHeight();
                int b = contentH - scrollH - scrollY;

                if (contentH >  scrollH && b < 100) {
                    presenter.loadMore();
                }
            }
        });

        animAddView = AnimationUtils.loadAnimation(
                getActivity().getApplicationContext(), R.anim.fade_in_anim);

        new HomePresenter(this, new HomeServices(getActivity()));
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
        mHandler.removeCallbacksAndMessages(null);
        mHandler = null;
        super.onDestroyView();
    }

    @OnClick(R.id.home_img_search)
    public void onToolbarBtnSearchClick() {

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
        if (show) showToastError(msg);
    }

    @Override
    public void showMsgError(boolean show, @StringRes int resId) {
        if (show) showToastError(resId);
    }

    @Override
    public void showMsgToast(String msg) {

    }

    @Override
    public void addSliderView(List<Slider> sliders) {
        if (mSliderView == null) {
            mSliderView = new BookSliderView(getActivity());
            mSliderView.setupAdapter(getFragmentManager());
        }
        mSliderView.setListSliders(sliders);

        contentLayout.removeView(mSliderView);
        contentLayout.addView(mSliderView, 0);

        mSliderView.startSlider();
    }

    @Override
    public void addBlockBookListVertical(final int style, final String title, final String urlMore, final List<Book> books) {
        final View placeHolderView = new View(getContext());
        contentLayout.setId(contentLayout.getChildCount());
        contentLayout.addView(placeHolderView);
        LogUtils.d(TAG, "addBlockBookListVertical : add placeHolderView at " + title);
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                HomeBlockView homeBlockView = new HomeBlockView(getActivity(), style);
                homeBlockView.setTextTitle(title);
                homeBlockView.setUrlViewMore(urlMore);
                homeBlockView.setListBooks(books);
                homeBlockView.setOnViewMoreListener(new HomeBlockView.OnViewMoreListener() {
                    @Override
                    public void onHomeBlockViewMoreClick(String urlMore) {
                        showToastSuccess(urlMore);
                    }
                });
                homeBlockView.setOnItemClickListener(new HomeBlockRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onBookItemClick(View imgPoster, int bookId) {
                        Intent intent = BookDetailActivity.buildIntent(getActivity(), bookId);
                        getActivity().startActivity(intent);
                    }
                });
                contentLayout.removeView(placeHolderView);
                contentLayout.addView(homeBlockView, contentLayout.getId());
                LogUtils.d(TAG, "addBlockBookListVertical : add homeBlockView at " + title);
                contentLayout.startAnimation(animAddView);
            }
        });
    }

    @Override
    public void addBlockBookListHorizontal(final String urlBg, final String title, final String subTitle, final String urlMore, final List<Book> books) {
        final View placeHolderView = new View(getContext());
        contentLayout.setId(contentLayout.getChildCount());
        contentLayout.addView(placeHolderView);
        LogUtils.d(TAG, "addBlockBookListHorizontal : add placeHolderView at " + title);

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                HomeBlockHorizontalView homeBlockView = new HomeBlockHorizontalView(getActivity());
                homeBlockView.setTextTitle(title);
                homeBlockView.setTextSubTitle(subTitle);
                homeBlockView.setUrlViewMore(urlMore);
                homeBlockView.setListBooks(books);
                homeBlockView.setOnViewMoreListener(new HomeBlockHorizontalView.OnViewMoreListener() {
                    @Override
                    public void onHomeBlockViewMoreClick(String urlMore) {
                        showToastSuccess(urlMore);
                    }
                });
                homeBlockView.setOnItemClickListener(new HomeBlockRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onBookItemClick(View imgPoster, int bookId) {
                        Intent intent = BookDetailActivity.buildIntent(getActivity(), bookId);
                        getActivity().startActivity(intent);
                    }
                });
                contentLayout.removeView(placeHolderView);
                contentLayout.addView(homeBlockView, contentLayout.getId());
                LogUtils.d(TAG, "addBlockBookListHorizontal : add homeBlockView at " + title);
                contentLayout.startAnimation(animAddView);
                homeBlockView.setBackGroundView(urlBg);
            }
        });
    }

    @Override
    public void addAdsView() {
        final View placeHolderView = new View(getContext());
        contentLayout.setId(contentLayout.getChildCount());
        contentLayout.addView(placeHolderView);

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                int padding = DensityUtils.dip2px(getActivity(),16);
                TextView view = new TextView(getActivity());
                view.setBackgroundResource(R.color.background_dark);
                view.setPadding(padding, padding, padding, padding);
                view.setText("Quang cao");
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.topMargin = padding;
                contentLayout.removeView(placeHolderView);
                contentLayout.addView(view, contentLayout.getId());
                contentLayout.startAnimation(animAddView);
            }
        });
    }

    @Override
    public void clearViews() {
        contentLayout.removeAllViews();
    }
}
