package com.nguyencuong.webtruyen.widget.homeblock;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.nguyencuong.webtruyen.R;
import com.nguyencuong.webtruyen.model.Book;
import com.nguyencuong.webtruyen.util.DensityUtils;
import com.nguyencuong.webtruyen.util.LogUtils;

import java.util.List;

import static com.nguyencuong.webtruyen.widget.homeblock.HomeBlockRecyclerAdapter.ITEM_TYPE_LIST_H;

/**
 * Apply for style HOME_ITEM_STYLE_LIST_V and HOME_ITEM_STYLE_GRID
 * <p>
 * Created by Mr Cuong on 4/15/2017.
 * Email: vancuong2941989@gmail.com
 */
public class HomeBlockHorizontalView extends FrameLayout implements View.OnClickListener {

    public interface OnViewMoreListener {
        void onHomeBlockViewMoreClick(String urlMore);
    }

    private final int ITEM_WIDTH;

    private FrameLayout rootLayout;
    private View layoutTitle;
    private TextView title;
    private TextView subTitle;
    private View btnViewMore;
    private RecyclerView recyclerView;

    private HomeBlockRecyclerAdapter adapter;

    private OnViewMoreListener onViewMoreListener;

    private String urlViewMore;

    private float scrollX = 0;

    public HomeBlockHorizontalView(@NonNull Context context) {
        super(context);
        ITEM_WIDTH = context.getResources().getDimensionPixelSize(R.dimen.poster_size_w);
        init();
    }

    public HomeBlockHorizontalView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        ITEM_WIDTH = context.getResources().getDimensionPixelSize(R.dimen.poster_size_w);
        init();
    }

    private void init() {
        boolean isTablet = getContext().getResources().getBoolean(R.bool.isTablet);

        LayoutInflater.from(getContext()).inflate(R.layout.widget_home_block_horizontal_view, this);

        rootLayout = (FrameLayout) findViewById(R.id.widget_home_block_root);
        layoutTitle = findViewById(R.id.widget_home_block_layout_title);
        title = (TextView) findViewById(R.id.widget_block_tv_title);
        subTitle = (TextView) findViewById(R.id.widget_block_tv_subtitle);
        btnViewMore = findViewById(R.id.widget_block_tv_more);
        btnViewMore.setOnClickListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.widget_block_recyclerview);

        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(
                getContext(), LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new HomeBlockHorizontalRecyclerDecoration(getContext(), ITEM_WIDTH));
        adapter = new HomeBlockRecyclerAdapter(ITEM_TYPE_LIST_H, 0);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LogUtils.i("HomeBlockHorizontalView", "dx = " + dx);
                scrollX = scrollX + dx;
                LogUtils.i("HomeBlockHorizontalView", "scrollX = " + scrollX);
                float alpha = 1 - (Math.abs(scrollX) / ITEM_WIDTH);
                if (alpha <= 0) {
                    alpha = 0;
                    layoutTitle.setVisibility(GONE);
                } else {
                    layoutTitle.setVisibility(VISIBLE);
                }
                layoutTitle.setAlpha(alpha);
            }
        });
    }

    public void setTextTitle(String text) {
        title.setText(text);
    }

    public void setTextSubTitle(String text) {
        subTitle.setText(text);
    }

    public void setUrlViewMore(String urlViewMore) {
        this.urlViewMore = urlViewMore;
    }

    public void setListBooks(List<Book> list) {
        adapter.setListBooks(list);
    }

    public void setBackGroundView(String url) {
        int w = (int) DensityUtils.getWidthInPx(getContext());
        int h = DensityUtils.dip2px(getContext(), 194);
        SimpleTarget target = new SimpleTarget<Bitmap>(w, h) {
            @Override
            public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {
                BitmapDrawable db = new BitmapDrawable(getContext().getResources(), bitmap);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    rootLayout.setBackground(db);
                } else {
                    rootLayout.setBackgroundDrawable(db);
                }
            }
        };

        Glide.with(getContext().getApplicationContext())
                .load(url)
                .asBitmap()
                .centerCrop()
                .placeholder(R.drawable.default_cover)
                .error(R.drawable.default_cover)
                .into(target);
    }

    public void setOnItemClickListener(HomeBlockRecyclerAdapter.OnItemClickListener onItemClickListener) {
        adapter.setOnItemClickListener(onItemClickListener);
    }

    public void setOnViewMoreListener(OnViewMoreListener onViewMoreListener) {
        this.onViewMoreListener = onViewMoreListener;
    }

    @Override
    public void onClick(View view) {
        if (onViewMoreListener != null) {
            if (view == btnViewMore) {
                onViewMoreListener.onHomeBlockViewMoreClick(urlViewMore);
            }
        }
        LogUtils.d("HomeBlock", "OnCLick view more");
    }
}
