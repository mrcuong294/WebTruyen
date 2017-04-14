package com.nguyencuong.webtruyen.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nguyencuong.webtruyen.model.Slider;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class
 * Created by pc on 4/14/2017.
 */

public class HomeModel extends BaseGetModel {
    @SerializedName("data")
    @Expose
    private Data data;

    public Data getData() {
        return data;
    }

    public class Data {
        @SerializedName("sliders")
        @Expose
        private List<Slider> sliders;

        public List<Slider> getSliders() {
            if (sliders == null) {
                return new ArrayList<>();
            }
            return sliders;
        }
    }
}
