package com.nguyencuong.webtruyen.widget.homeblock;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.nguyencuong.webtruyen.Constants;
import com.nguyencuong.webtruyen.R;
import com.nguyencuong.webtruyen.model.Book;
import com.nguyencuong.webtruyen.util.DensityUtils;

import java.util.List;

import static com.nguyencuong.webtruyen.widget.homeblock.HomeBlockRecyclerAdapter.ITEM_TYPE_GRID;
import static com.nguyencuong.webtruyen.widget.homeblock.HomeBlockRecyclerAdapter.ITEM_TYPE_LIST_H;
import static com.nguyencuong.webtruyen.widget.homeblock.HomeBlockRecyclerAdapter.ITEM_TYPE_LIST_V;

/**
 * Apply for style HOME_ITEM_STYLE_LIST_V and HOME_ITEM_STYLE_GRID
 * <p>
 * Created by Mr Cuong on 4/15/2017.
 * Email: vancuong2941989@gmail.com
 */
public class HomeBlockView extends FrameLayout implements View.OnClickListener {

    public interface OnViewMoreListener {
        void onHomeBlockViewMoreClick(String urlMore);
    }

    private TextView title;
    private View btnViewMoreTop;
    private View btnViewMoreBottom;
    private RecyclerView recyclerView;

    private HomeBlockRecyclerAdapter adapter;

    private OnViewMoreListener onViewMoreListener;

    private int style;

    private String urlViewMore;


    public HomeBlockView(@NonNull Context context, int style) {
        super(context);
        this.style = style;
        init();
    }

    public HomeBlockView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        style = Constants.HOME_ITEM_STYLE_LIST_V;
        init();
    }

    private void init() {
        boolean isTablet = getContext().getResources().getBoolean(R.bool.isTablet);

        LayoutInflater.from(getContext()).inflate(R.layout.widget_home_block_view, this);

        title = (TextView) findViewById(R.id.widget_block_tv_title);
        btnViewMoreTop = findViewById(R.id.widget_block_tv_more);
        btnViewMoreTop.setOnClickListener(this);
        btnViewMoreBottom = findViewById(R.id.widget_block_tv_more2);
        btnViewMoreBottom.setOnClickListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.widget_block_recyclerview);

        RecyclerView.LayoutManager layoutManager;
        int itemWidth;
        int itemType;

        switch (style) {
            case Constants.HOME_ITEM_STYLE_GRID:
                int spanCount = 3; // mobile.
                if (isTablet) {
                    spanCount = 6;
                }
                float screenWidth = DensityUtils.getWidthInPx(getContext());
                int margin = getContext().getResources().getDimensionPixelSize(R.dimen.space_8);
                int pading = getContext().getResources().getDimensionPixelSize(R.dimen.space_4);

                itemType = ITEM_TYPE_GRID;
                itemWidth = (int) ((screenWidth - margin*(spanCount - 1)) / spanCount);
                layoutManager = new GridLayoutManager(getContext(), spanCount);

                recyclerView.setPadding(pading, 0, pading, 0);
                break;

            case Constants.HOME_ITEM_STYLE_LIST_V:
                itemType = ITEM_TYPE_LIST_V;
                itemWidth = 0;
                layoutManager = new LinearLayoutManager(getContext());
                break;

            case Constants.HOME_ITEM_STYLE_LIST_H:
                itemType = ITEM_TYPE_LIST_H;
                itemWidth = 0;
                layoutManager = new LinearLayoutManager(
                        getContext(), LinearLayoutManager.HORIZONTAL, false);
                break;

            default:
                itemType = ITEM_TYPE_LIST_V;
                itemWidth = 0;
                if (isTablet) {
                    layoutManager = new GridLayoutManager(getContext(), 3);
                } else {
                    layoutManager = new LinearLayoutManager(getContext());
                }
                break;
        }

        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new HomeBlockRecyclerAdapter(itemType, itemWidth);
        recyclerView.setAdapter(adapter);
    }

    public void setTextTitle(String text) {
        title.setText(text);
    }

    public void setUrlViewMore(String urlViewMore) {
        this.urlViewMore = urlViewMore;
    }

    public void setListBooks(List<Book> list) {
        adapter.setListBooks(list);
    }

    public void setOnItemClickListener(HomeBlockRecyclerAdapter.OnItemClickListener onItemClickListener) {
        adapter.setOnItemClickListener(onItemClickListener);
    }

    public void setOnViewMoreListener(OnViewMoreListener onViewMoreListener) {
        this.onViewMoreListener = onViewMoreListener;
    }

    @Override
    public void onClick(View view) {
        if (onViewMoreListener != null && urlViewMore != null) {
            if (view == btnViewMoreTop || view == btnViewMoreBottom) {
                onViewMoreListener.onHomeBlockViewMoreClick(urlViewMore);
            }
        }
    }
}
