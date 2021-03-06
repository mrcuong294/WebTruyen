package com.nguyencuong.webtruyen.ui.activity.bookdetail;

import android.content.Intent;

import com.nguyencuong.webtruyen.BaseContractPresenter;
import com.nguyencuong.webtruyen.BaseContractView;
import com.nguyencuong.webtruyen.model.Book;

/**
 * Content class.
 * <p>
 * Created by Mr Cuong on 4/22/2017.
 * Email: vancuong2941989@gmail.com
 */

public interface BookDeailContract {

    interface View extends BaseContractView<Presenter> {

        void setBookDetails(Book book);

        void showPopupListChapter();

        void showPopupRating();
    }

    interface Presenter extends BaseContractPresenter {

        void setIntent(Intent intent);

        void loadBookDetail();

        void reloadBookDetail();

        void loadBookListChapter();

        void reloadBookListChapter();

        void readBook();

        void readBookFromLast();

        void likeBook();

        void shareBook();

        void ratingBook();

        void submitRating(int point);

        void addBookmark();
    }
}
