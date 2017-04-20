package com.nguyencuong.webtruyen.ui.activity.bookdetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.nguyencuong.webtruyen.BaseActivity;
import com.nguyencuong.webtruyen.R;
import com.nguyencuong.webtruyen.util.TextUtils;

import butterknife.BindView;

public class BookDetailActivity extends BaseActivity {
    private static final String INTENT_BOOK_ID = "INTENT_BOOK_ID";

    public static Intent buildIntent(Context context, int bookId) {
        Intent intent = new Intent(context, BookDetailActivity.class);
        intent.putExtra(INTENT_BOOK_ID, bookId);
        return intent;
    }

    @BindView(R.id.book_tv_author)
    TextView tvAuthor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        String author = "<font color=\"#24e2fc\">Tác giả: </font> Nguyễn Cường";
        tvAuthor.setText(TextUtils.styleTextHtml(author));
    }
}
