package com.nguyencuong.webtruyen.data.remote.services;

import android.content.Context;

import com.google.gson.Gson;
import com.nguyencuong.webtruyen.data.remote.ApiError;
import com.nguyencuong.webtruyen.data.remote.BaseApiResultCallback;
import com.nguyencuong.webtruyen.data.remote.BaseApiServices;
import com.nguyencuong.webtruyen.data.remote.model.BookDetailModel;
import com.nguyencuong.webtruyen.model.Book;
import com.nguyencuong.webtruyen.util.FileUtils;

/**
 * Content class.
 * <p>
 * Created by Mr Cuong on 4/22/2017.
 * Email: vancuong2941989@gmail.com
 */

public class BookDetailServices extends BaseApiServices {

    public interface ResultCallback extends BaseApiResultCallback {

        void onBookDetailResultSuccess(Book book);
    }

    private ResultCallback resultCallback;

    public BookDetailServices(Context context) {
        super(context);
    }

    public void setResultCallback(ResultCallback resultCallback) {
        this.resultCallback = resultCallback;
    }

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

    @Override
    public void cancel() {

    }
}
