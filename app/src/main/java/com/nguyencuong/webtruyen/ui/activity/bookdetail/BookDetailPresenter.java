package com.nguyencuong.webtruyen.ui.activity.bookdetail;

import android.content.Intent;

import com.nguyencuong.webtruyen.BaseContractView;
import com.nguyencuong.webtruyen.BasePresenter;
import com.nguyencuong.webtruyen.R;
import com.nguyencuong.webtruyen.data.remote.ApiError;
import com.nguyencuong.webtruyen.data.remote.services.BookDetailServices;
import com.nguyencuong.webtruyen.model.Book;
import com.nguyencuong.webtruyen.util.LogUtils;

import static com.nguyencuong.webtruyen.ui.activity.bookdetail.BookDetailActivity.INTENT_BOOK_ID;

/**
 * Content class.
 * <p>
 * Created by Mr Cuong on 4/22/2017.
 * Email: vancuong2941989@gmail.com
 */

public class BookDetailPresenter extends BasePresenter implements BookDeailContract.Presenter, BookDetailServices.ResultCallback {

    private static final String TAG = BookDetailPresenter.class.getSimpleName();
    private final BookDeailContract.View mView;

    private final BookDetailServices mBookDetailServices;

    private Book mBook;

    private int bookId = -1;

    protected BookDetailPresenter(BaseContractView view, BookDetailServices mBookDetailServices) {
        super(view);
        this.mView = (BookDeailContract.View) view;
        this.mBookDetailServices = mBookDetailServices;

        this.mView.setPresenter(this);
        this.mBookDetailServices.setResultCallback(this);
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {
        mBookDetailServices.cancel();
    }

    @Override
    public void setIntent(Intent intent) {
        if (intent != null) {
            bookId = intent.getIntExtra(INTENT_BOOK_ID, -1);
        } else {
            mView.showMsgError(true, R.string.error_msg_no_data);
        }
    }

    @Override
    public void loadBookDetail() {
        mView.showMsgError(false, null);
        mView.showLoading(true);
        mBookDetailServices.loadBookDetail(bookId);
    }

    @Override
    public void reloadBookDetail() {
        mView.showMsgError(false, null);
        mView.showLoading(true);
        mBookDetailServices.loadBookDetail(bookId);
    }

    @Override
    public void loadBookListChapter() {

    }

    @Override
    public void reloadBookListChapter() {

    }

    @Override
    public void readBook() {

    }

    @Override
    public void readBookFromLast() {

    }

    @Override
    public void likeBook() {

    }

    @Override
    public void shareBook() {

    }

    @Override
    public void ratingBook() {

    }

    @Override
    public void submitRating(int point) {

    }

    @Override
    public void addBookmark() {

    }

    @Override
    public void onApiResultFailure(ApiError apiError) {
        mView.showLoading(false);
        mView.showMsgError(true, apiError.getMessage());
        LogUtils.d(TAG, "onApiResultFailure ");
    }

    @Override
    public void onApiConnectInternetFailure(ApiError apiError) {
        mView.showLoading(false);
        mView.showMsgError(true, apiError.getMessage());
        LogUtils.d(TAG, "onApiConnectInternetFailure ");
    }

    @Override
    public void onBookDetailResultSuccess(Book book) {
        this.mBook = book;
        if (mBook != null) {
            mView.showMsgError(false, null);
            mView.setBookDetails(book);
        } else {
            mView.showMsgError(true, R.string.error_msg_no_data);
        }
        LogUtils.d(TAG, "onBookDetailResultSuccess ");
        mView.showLoading(false);
    }
}
