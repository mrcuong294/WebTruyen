package com.nguyencuong.webtruyen.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nguyencuong.webtruyen.model.Chapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Content class.
 * <p>
 * Created by Mr Cuong on 4/22/2017.
 * Email: vancuong2941989@gmail.com
 */

public class BookListChapterlModel extends BaseGetModel {
    @SerializedName("data")
    @Expose
    private Data data;

    public Data getData() {
        return data;
    }

    public class Data {
        @SerializedName("limit")
        @Expose
        private int limit;
        @SerializedName("offset")
        @Expose
        private int offset;
        @SerializedName("chapters")
        @Expose
        private List<Chapter> chapters = null;

        public int getLimit() {
            return limit;
        }

        public int getOffset() {
            return offset;
        }

        public List<Chapter> getChapters() {
            if (chapters == null) return new ArrayList<>();
            return chapters;
        }
    }
}
