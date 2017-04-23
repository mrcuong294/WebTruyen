package com.nguyencuong.webtruyen.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nguyencuong.webtruyen.model.Book;

/**
 * Content class.
 * <p>
 * Created by Mr Cuong on 4/22/2017.
 * Email: vancuong2941989@gmail.com
 */

public class BookDetailModel extends BaseGetModel {
    @SerializedName("data")
    @Expose
    private Book book;

    public Book getBook() {
        return book;
    }
}
