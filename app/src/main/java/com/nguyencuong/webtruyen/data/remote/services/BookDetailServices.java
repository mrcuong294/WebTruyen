package com.nguyencuong.webtruyen.data.remote.services;

import android.content.Context;

import com.google.gson.Gson;
import com.nguyencuong.webtruyen.data.remote.ApiError;
import com.nguyencuong.webtruyen.data.remote.BaseApiResultCallback;
import com.nguyencuong.webtruyen.data.remote.BaseApiServices;
import com.nguyencuong.webtruyen.data.remote.model.BookDetailModel;
import com.nguyencuong.webtruyen.data.remote.model.BookListChapterlModel;
import com.nguyencuong.webtruyen.model.Book;
import com.nguyencuong.webtruyen.model.Chapter;
import com.nguyencuong.webtruyen.util.FileUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Content class.
 * <p>
 * Created by Mr Cuong on 4/22/2017.
 * Email: vancuong2941989@gmail.com
 */

public class BookDetailServices extends BaseApiServices {

    public interface OnResultCallback extends BaseApiResultCallback {

        void onBookDetailResultSuccess(Book book);

        void onChapterResultSuccess(List<Chapter> chapters, int offset, int limit);

        void onChapterResultFailed(ApiError error);
    }

    public static class ResultCallback implements OnResultCallback {

        @Override
        public void onApiResultFailure(ApiError apiError) {

        }

        @Override
        public void onApiConnectInternetFailure(ApiError apiError) {

        }

        @Override
        public void onBookDetailResultSuccess(Book book) {

        }

        @Override
        public void onChapterResultSuccess(List<Chapter> chapters, int offset, int limit) {

        }

        @Override
        public void onChapterResultFailed(ApiError error) {

        }
    }

    private OnResultCallback resultCallback;

    public BookDetailServices(Context context) {
        super(context);
    }

    public void setResultCallback(OnResultCallback resultCallback) {
        this.resultCallback = resultCallback;
    }

    /**
     * Load book details;
     * @param bookId id of book;
     */
    public void loadBookDetail(int bookId) {
        if (resultCallback == null) {
            return;
        }
        String dataJson = FileUtils.getJsonFromAssets(context, PATH_TEST, "book_detail.json");

        if (dataJson != null) {
            BookDetailModel model = new Gson().fromJson(dataJson, BookDetailModel.class);
            resultCallback.onBookDetailResultSuccess(model.getBook());
        } else {
            resultCallback.onApiResultFailure(new ApiError.Builder().setCode(-3).build());
        }
    }

    /**
     * Load list chapters of book.;
     * @param bookId id of book;
     */
    public void loadListChapter(int bookId, int offset) {
        if (resultCallback == null) {
            return;
        }
        String dataJson = FileUtils.getJsonFromAssets(context, PATH_TEST, "book_detail_list_chapter.json");

        if (dataJson != null) {
            BookListChapterlModel model = new Gson().fromJson(dataJson, BookListChapterlModel.class);

            List<Chapter> chapters = new ArrayList<>();
            for (int i = 0; i < 50; i++) {
                Chapter chapter = new Chapter();
                chapter.setId(model.getData().getLimit() * model.getData().getOffset() + i);
                chapter.setNumber(model.getData().getLimit() * model.getData().getOffset() + i);
                chapter.setName(String.format("%1$s - Chương %2$s", chapter.getNumber(), chapter.getNumber()));
                chapters.add(chapter);
            }

            resultCallback.onChapterResultSuccess(chapters, model.getData().getOffset(), model.getData().getLimit());
        } else {
            resultCallback.onChapterResultFailed(new ApiError.Builder().setCode(-3).build());
        }
    }

    @Override
    public void cancel() {

    }
}
