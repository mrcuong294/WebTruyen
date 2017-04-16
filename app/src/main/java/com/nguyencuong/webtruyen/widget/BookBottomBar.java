package com.nguyencuong.webtruyen.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nguyencuong.webtruyen.R;
import com.nguyencuong.webtruyen.util.LogUtils;

/**
 * Content class.
 * <p>
 * Created by Mr Cuong on 4/12/2017.
 * Email: vancuong2941989@gmail.com
 */

public class BookBottomBar extends LinearLayout {

    public interface Listener {

        void onBottomBarHomeClick();

        void onBottomBarCategoryClick();

        void onBottomBarLibraryClick();

        void onBottomBarPersonalClick();
    }

    private static final String TAG = BookBottomBar.class.getSimpleName();

    private final int COLOR_NORMAL;
    private final int COLOR_ACTIVE;
    private static final float TEXT_SIZE_NORMAL = 12; // sp
    private static final float TEXT_SIZE_ACTIVE = 14; //sp
    private final int MARGIN_NORMAL;
    private final int MARGIN_ACTIVE;

    private static final int ANIMATION_DURATION = 200;

    private ViewGroup rootView;

    private ImageView imgIcon, imgIconEnd;

    private TextView tvLabel, tvLabelEnd;

    private Listener listener;

    private ValueAnimator animatorTextSize;
    private ValueAnimator animatorTextSizeEnd;

    private ValueAnimator animatorMargin;
    private ValueAnimator animatorMarginEnd;

    private int positionItemActived = 0;

    public BookBottomBar(Context context) {
        this(context, null, 0);
    }

    public BookBottomBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BookBottomBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(getContext()).inflate(R.layout.widget_bottom_bar, this, true);

        COLOR_NORMAL = ResourcesCompat.getColor(getContext().getResources(), R.color.text_light_1, null);
        COLOR_ACTIVE = ResourcesCompat.getColor(getContext().getResources(), R.color.blue_light, null);
        MARGIN_NORMAL = getContext().getResources().getDimensionPixelSize(R.dimen.space_8);
        MARGIN_ACTIVE = getContext().getResources().getDimensionPixelSize(R.dimen.space_6);

        setupAnimation();

        rootView = (ViewGroup) findViewById(R.id.bottom_bar_root);

        for(int i = 0; i < rootView.getChildCount(); i++) {
            rootView.getChildAt(i).setId(i);
            rootView.getChildAt(i).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        switch (view.getId()) {
                            case 0:
                                listener.onBottomBarHomeClick();
                                break;
                            case 1:
                                listener.onBottomBarCategoryClick();
                                break;
                            case 2:
                                listener.onBottomBarLibraryClick();
                                break;
                            case 3:
                                listener.onBottomBarPersonalClick();
                                break;
                        }
                    }

                    int posItemOld = positionItemActived;
                    positionItemActived = view.getId();

                    if (posItemOld == positionItemActived) {
                        return;
                    }

                    ViewGroup itemView = (ViewGroup) view;
                    imgIcon = (ImageView) itemView.getChildAt(0);
                    tvLabel = (TextView) itemView.getChildAt(1);

                    ViewGroup itemViewEnd = (ViewGroup) rootView.getChildAt(posItemOld);
                    imgIconEnd = (ImageView) itemViewEnd.getChildAt(0);
                    tvLabelEnd = (TextView) itemViewEnd.getChildAt(1);

                    animatorTextSize.start();
                    animatorTextSizeEnd.start();
                    animatorMargin.start();
                    animatorMarginEnd.start();

                    changeIconColor(imgIcon, COLOR_ACTIVE);
                    changeIconColor(imgIconEnd, COLOR_NORMAL);
                    changeTextColor(tvLabel, COLOR_ACTIVE);
                    changeTextColor(tvLabelEnd, COLOR_NORMAL);

                    itemView.setAlpha(1.0f);
                    itemViewEnd.setAlpha(0.7f);
                }
            });
        }
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    /**
     * Setup animation icon margin and text size label;
     */
    private void setupAnimation() {
        animatorTextSize = ValueAnimator.ofFloat(TEXT_SIZE_NORMAL, TEXT_SIZE_ACTIVE);
        animatorTextSize.setDuration(ANIMATION_DURATION);
        animatorTextSize.setRepeatMode(ValueAnimator.RESTART);
        animatorTextSize.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {

                if (tvLabel != null) {
                    Float size = (float) valueAnimator.getAnimatedValue();
                    LogUtils.i(TAG, "animatorTextSize size : " + size);
                    setTextSize(tvLabel, size);
                }
            }
        });

        animatorTextSizeEnd = ValueAnimator.ofFloat(TEXT_SIZE_ACTIVE, TEXT_SIZE_NORMAL);
        animatorTextSizeEnd.setDuration(ANIMATION_DURATION);
        animatorTextSize.setRepeatMode(ValueAnimator.RESTART);
        animatorTextSizeEnd.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {

                if (tvLabelEnd != null) {
                    setTextSize(tvLabelEnd, (float) valueAnimator.getAnimatedValue());
                }
            }
        });

        animatorMargin = ValueAnimator.ofInt(MARGIN_NORMAL, MARGIN_ACTIVE);
        animatorMargin.setDuration(ANIMATION_DURATION);
        animatorTextSize.setRepeatMode(ValueAnimator.RESTART);
        animatorMargin.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {

                if (imgIcon != null) {
                    FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) imgIcon.getLayoutParams();
                    layoutParams.topMargin = (int) valueAnimator.getAnimatedValue();
                    imgIcon.setLayoutParams(layoutParams);
                }
            }
        });

        animatorMarginEnd = ValueAnimator.ofInt(MARGIN_ACTIVE, MARGIN_NORMAL);
        animatorMarginEnd.setDuration(ANIMATION_DURATION);
        animatorTextSize.setRepeatMode(ValueAnimator.RESTART);
        animatorMarginEnd.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {

                if (imgIconEnd != null) {
                    FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) imgIconEnd.getLayoutParams();
                    layoutParams.topMargin = (int) valueAnimator.getAnimatedValue();
                    imgIconEnd.setLayoutParams(layoutParams);
                }
            }
        });
    }

    private void changeIconColor(ImageView icon, int color) {
        if (icon != null) {
            icon.setColorFilter(color);
        }
    }

    private void changeTextColor(TextView tv, int color) {
        if (tv != null) {
            tv.setTextColor(color);
        }
    }

    private void setTextSize(TextView tv, float size) {
        if (tv != null) {
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
        }
    }

}
