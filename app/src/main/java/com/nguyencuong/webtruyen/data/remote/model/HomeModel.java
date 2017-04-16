package com.nguyencuong.webtruyen.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nguyencuong.webtruyen.model.Book;
import com.nguyencuong.webtruyen.model.Slider;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class
 * Created by pc on 4/14/2017.
 */

public class HomeModel extends BaseGetModel {
    @SerializedName("offset")
    @Expose
    private int offset;
    @SerializedName("data")
    @Expose
    private Data data;

    public Data getData() {
        return data;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public class Data {
        @SerializedName("sliders")
        @Expose
        private List<Slider> sliders = null;
        @SerializedName("items")
        @Expose
        private List<Items> items = null;

        public List<Slider> getSliders() {
            if (sliders == null) return new ArrayList<>();
            return sliders;
        }

        public List<Items> getItems() {
            if (items == null) return new ArrayList<>();
            return items;
        }
    }

    public class Items {
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("sub_title")
        @Expose
        private String subTitle;
        @SerializedName("img_bg")
        @Expose
        private String imgBg;
        @SerializedName("link")
        @Expose
        private String link;
        @SerializedName("style")
        @Expose
        private int style;
        @SerializedName("books")
        @Expose
        private List<Book> bookList;

        public List<Book> getBookList() {
            if (bookList == null) {
                return new ArrayList<>();
            }
            return bookList;
        }

        public String getTitle() {
            if (title == null) return "";
            return title;
        }

        public String getSubTitle() {
            if (subTitle == null) return "";
            return subTitle;
        }

        public String getImgBg() {
            if (link != null && link.length() < 2) return "book";
            return imgBg;
        }

        public String getLink() {
            if (link != null && link.length() < 2) return null;
            return link;
        }

        public int getStyle() {
            return style;
        }
    }
}
