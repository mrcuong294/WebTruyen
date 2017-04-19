package com.nguyencuong.webtruyen.widget.slider;

import android.content.Context;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.nguyencuong.webtruyen.R;
import com.nguyencuong.webtruyen.model.Slider;
import com.nguyencuong.webtruyen.util.DensityUtils;

import java.util.List;

/**
 * Slider image with indicator;
 */
public class BookSliderView extends FrameLayout implements ViewPager.OnPageChangeListener, View.OnClickListener {

    public interface OnPageChangeListener {
        void onPageChangePosition();
    }

    public interface Listener {
        void onBookSliderButtonTopViewClick();
        void onBookSliderButtonTopFavoriteClick();
        void onBookSliderButtonTopVoteClick();
        void onBookSliderButtonTopNewClick();
    }

    private static final String TAG = BookSliderView.class.getSimpleName();
    private static final float RATIO_COVER_HW = 0.5f;
    private static final float TABLET_LAND_PAGER_W = 0.7f;
    private static final int TIME_SLIDER = 3000; // mili second

    private BookSliderAdapter adapter;

    private final ViewPager viewPager;
    private final LinearLayout pagerlayout;
    private final View btnTopView;
    private final View btnTopNew;

    private LinearLayout.LayoutParams layoutParamsPager;

    private final int MARGIN;

    private int currentItem = 0;

    private boolean runLeftToRight = true;

    private Handler handlerRunSlider = new Handler();

    private OnPageChangeListener onPageChangeListener;

    private Listener listener;

    // Runnable auto play slider;
    private Runnable runnableRunSlider = new Runnable() {
        @Override
        public void run() {
            if (viewPager != null && adapter != null && adapter.getCount() > 1) {
                int itemCount = adapter.getCount();

                if (currentItem < itemCount - 1) {
                    if (runLeftToRight) {
                        currentItem++;
                    } else {
                        currentItem -= 1;
                    }
                    if (currentItem == 0) runLeftToRight = true;
                } else {
                    runLeftToRight = false;
                    currentItem -= 1;
                }

                viewPager.setCurrentItem(currentItem, true);

                handlerRunSlider.postDelayed(runnableRunSlider, TIME_SLIDER);
            }
        }
    };

    public BookSliderView(Context context) {
        this(context, null, 0);
    }

    public BookSliderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BookSliderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        float SCREEN_W = DensityUtils.getWidthInPx(getContext());

        MARGIN = getContext().getResources().getDimensionPixelSize(R.dimen.space_16);

        View.inflate(getContext(), R.layout.widget_block_slider_view, this);

        viewPager = (ViewPager) findViewById(R.id.widget_book_slider_viewPager);
        viewPager.setOffscreenPageLimit(2);
        viewPager.addOnPageChangeListener(this);
        viewPager.setPageMargin(MARGIN);
        if (getContext().getResources().getBoolean(R.bool.isTablet)) {
            // Config for tablet
            int padding = (int) (SCREEN_W * (1 - TABLET_LAND_PAGER_W)) / 2;
            viewPager.getLayoutParams().height = (int) ((SCREEN_W - padding * 2) * RATIO_COVER_HW);
            viewPager.setPadding(padding, 0, padding, 0);
        } else {
            // set height for mobile
            //viewPager.getLayoutParams().height = (int) ((SCREEN_W - MARGIN*2) * RATIO_COVER_HW);
            viewPager.getLayoutParams().height = (int) (SCREEN_W * RATIO_COVER_HW);
        }

        pagerlayout = (LinearLayout) findViewById(R.id.widget_book_slider_pager);

        btnTopView = findViewById(R.id.widget_book_slider_btn_viewed);
        btnTopView.setOnClickListener(this);
        btnTopNew = findViewById(R.id.widget_book_slider_btn_new);
        btnTopNew.setOnClickListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        currentItem = position;

        if (pagerlayout.getChildCount() > 0) {
            for (int i=0; i < pagerlayout.getChildCount(); i++) {
                View view = pagerlayout.getChildAt(i);
                if (position == i) {
                    view.setBackgroundResource(R.drawable.ds_widget_slider_pager_bg_actived);
                } else {
                    view.setBackgroundResource(R.drawable.ds_widget_slider_pager_bg);
                }
            }
            if (onPageChangeListener != null) {
                onPageChangeListener.onPageChangePosition();
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View view) {
        if (listener != null) {
            if (view == btnTopView) {
                listener.onBookSliderButtonTopViewClick();
            } else if (view == btnTopNew) {
                listener.onBookSliderButtonTopNewClick();
            }
        }
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public void setupAdapter(FragmentManager fm) {
        adapter = new BookSliderAdapter(getContext(), fm);
        viewPager.setAdapter(adapter);
    }

    /**
     * Set data sliderView;
     * @param listSlider list slider
     */
    public void setListSliders(List<Slider> listSlider) {
        if (adapter != null) {
            adapter.setListSlider(listSlider);
            addPagerViews();
        }
    }

    /**
     * Start auto play slider
     */
    public void startSlider() {
        if (adapter != null && adapter.getCount() > 1) {
            handlerRunSlider.removeCallbacks(runnableRunSlider);
            handlerRunSlider.postDelayed(runnableRunSlider, TIME_SLIDER);
        }
    }

    /**
     * Stop auto play slider;
     */
    public void stopSlider() {
        handlerRunSlider.removeCallbacks(runnableRunSlider);
    }

    /**
     * Add item pager to pager layout;
     */
    private void addPagerViews() {
        if (adapter == null || adapter.getCount() == 0) {
            return;
        }

        if (layoutParamsPager == null) {
            int margin = MARGIN / 2;
            layoutParamsPager = new LinearLayout.LayoutParams(margin, margin);
            margin = margin /2;
            layoutParamsPager.setMargins(margin,margin,margin,margin);
        }

        pagerlayout.removeAllViews();
        for (int i = 0; i < adapter.getCount(); i++) {
            View view = new View(getContext());
            if (viewPager.getCurrentItem() == i) {
                view.setBackgroundResource(R.drawable.ds_widget_slider_pager_bg_actived);
            } else {
                view.setBackgroundResource(R.drawable.ds_widget_slider_pager_bg);
            }
            pagerlayout.addView(view, layoutParamsPager);
        }
    }
}
