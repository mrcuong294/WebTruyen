package com.nguyencuong.webtruyen.widget.homeblock;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.nguyencuong.webtruyen.R;


/**
 *
 * Created by pc on 3/3/2017.
 */

public class HomeBlockHorizontalRecyclerDecoration extends RecyclerView.ItemDecoration {

    private final int MARGIN;

    private final int MARGIN_ITEM;

    private final int ITEM_WIDTH;

    public HomeBlockHorizontalRecyclerDecoration(Context context, int ITEM_WIDTH) {
        MARGIN = context.getResources().getDimensionPixelSize(R.dimen.space_16);
        MARGIN_ITEM = MARGIN / 2;
        this.ITEM_WIDTH = ITEM_WIDTH;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int pos = parent.getChildAdapterPosition(view);
        if(pos == 0){
            outRect.left = ITEM_WIDTH + MARGIN + MARGIN_ITEM;
            outRect.right = MARGIN_ITEM;
        } else if (pos == state.getItemCount()-1){
            outRect.left = 0;
            outRect.right = MARGIN;
        } else {
            outRect.left = 0;
            outRect.right = MARGIN_ITEM;
        }
    }
}
