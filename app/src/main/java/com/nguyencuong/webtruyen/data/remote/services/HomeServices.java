package com.nguyencuong.webtruyen.data.remote.services;

import android.content.Context;

import com.google.gson.Gson;
import com.nguyencuong.webtruyen.data.remote.ApiError;
import com.nguyencuong.webtruyen.data.remote.BaseApiResultCallback;
import com.nguyencuong.webtruyen.data.remote.BaseApiServices;
import com.nguyencuong.webtruyen.data.remote.model.HomeModel;
import com.nguyencuong.webtruyen.util.FileUtils;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * The Class
 * Created by pc on 4/14/2017.
 */

public class HomeServices extends BaseApiServices {

    public interface ResultCallback extends BaseApiResultCallback {

        void onHomeResultSuccess(HomeModel.Data data);
    }

    private ResultCallback resultCallback;

    private int offset = 0;

    public HomeServices(Context context) {
        super(context);
    }

    public void setResultCallback(ResultCallback resultCallback) {
        this.resultCallback = resultCallback;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getOffset() {
        return offset;
    }

    public void loadHomeData() {
        if (resultCallback == null) {
            return;
        }
        String dataJson = FileUtils.getJsonFromAssets(context, PATH_TEST, "home_data_" + offset + ".json");

        if (dataJson != null) {
            HomeModel homeModel = new Gson().fromJson(dataJson, HomeModel.class);
            resultCallback.onHomeResultSuccess(homeModel.getData());
        } else {
            resultCallback.onApiResultFailure(new ApiError.Builder().setCode(-3).build());
        }
    }

    @Override
    public void cancel() {

    }

    interface ServicesInterface {

        @GET("home")
        Call<HomeModel> loadHomeData(@Query("offset") int offset);
    }
}
