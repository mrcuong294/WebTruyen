package com.nguyencuong.webtruyen.ui.activity.bookdetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nguyencuong.webtruyen.BaseActivity;
import com.nguyencuong.webtruyen.R;
import com.nguyencuong.webtruyen.data.remote.services.BookDetailServices;
import com.nguyencuong.webtruyen.model.Book;
import com.nguyencuong.webtruyen.util.TextUtils;
import com.nguyencuong.webtruyen.util.TimeUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class BookDetailActivity extends BaseActivity implements BookDeailContract.View {
    public static final String INTENT_BOOK_ID = "INTENT_BOOK_ID";

    public static Intent buildIntent(Context context, int bookId) {
        Intent intent = new Intent(context, BookDetailActivity.class);
        intent.putExtra(INTENT_BOOK_ID, bookId);
        return intent;
    }

    @BindView(R.id.book_img_poster)
    ImageView imgPoster;

    @BindView(R.id.book_tv_author)
    TextView tvAuthor;

    @BindView(R.id.book_tv_name)
    TextView tvName;

    @BindView(R.id.book_tv_chapter)
    TextView tvChapter;

    @BindView(R.id.book_tv_likes)
    TextView tvLikes;

    @BindView(R.id.book_tv_viewed)
    TextView tvViewed;

    @BindView(R.id.book_tv_rating)
    TextView tvRating;

    @BindView(R.id.book_tv_des)
    TextView tvDescription;

    @BindView(R.id.book_layout_error)
    ViewGroup errorLayout;

    @BindView(R.id.book_tv_error)
    TextView tvError;

    @BindView(R.id.book_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.book_layout_ads)
    ViewGroup adsLayout;

    private BookDeailContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        new BookDetailPresenter(this, new BookDetailServices(this));
        mPresenter.setIntent(getIntent());
        mPresenter.loadBookDetail();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_book_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.book_btn_reading)
    public void onButtonRedingClick() {

    }

    @OnClick(R.id.book_btn_list_chapter)
    public void onButtonListChapterClick() {

    }

    @OnClick(R.id.book_tv_error)
    public void onButtonErrorClick() {
        mPresenter.reloadBookDetail();
    }

    @Override
    public void setPresenter(BookDeailContract.Presenter Presenter) {
        mPresenter = Presenter;
    }

    @Override
    public void showLoading(boolean show) {
        dialogLoading.show(show);
    }

    @Override
    public void showMsgError(boolean show, String msg) {
        errorLayout.setVisibility(show ? View.VISIBLE : View.GONE);
        if (show) {
            tvError.setText(msg);
        }
    }

    @Override
    public void showMsgError(boolean show, @StringRes int resId) {
        errorLayout.setVisibility(show ? View.VISIBLE : View.GONE);
        if (show) {
            tvError.setText(resId);
        }
    }

    @Override
    public void showMsgToast(String msg) {
        showToast(msg);
    }

    @Override
    public void setBookDetails(Book book) {

        Glide.with(this)
                .load(book.getPoster())
                .into(imgPoster);

        tvName.setText(book.getName());

        String author = String.format(getString(R.string.book_text_author), book.getAuthor().toUpperCase());
        tvAuthor.setText(TextUtils.styleTextHtml(author));

        String textChapAndUpdate = String.format(
                getString(R.string.book_detail_text_chapter_update),
                String.valueOf(book.getCurentChapter()),
                String.valueOf(book.getTotalChapter()),
                TimeUtils.convertTimeStampToDateTime(book.getUpdateAt()));
        tvChapter.setText(textChapAndUpdate);

        tvLikes.setText(String.format(
                getString(R.string.book_detail_text_liked),
                String.valueOf(book.getLiked())));

        tvViewed.setText(String.format(
                getString(R.string.book_detail_text_view),
                String.valueOf(book.getViewed())));

        tvRating.setText(book.getRating());

        String des = String.format(
                getString(R.string.book_detail_text_description),
                book.getName(), book.getCategory(), book.getBookType(), book.getDescription());
        tvDescription.setText(TextUtils.styleTextHtml(des));
    }

    @Override
    public void showPopupListChapter() {

    }

    @Override
    public void showPopupRating() {

    }
}
