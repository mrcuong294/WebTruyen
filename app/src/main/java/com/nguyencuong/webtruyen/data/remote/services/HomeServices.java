package com.nguyencuong.webtruyen.data.remote.services;

import android.content.Context;

import com.google.gson.Gson;
import com.nguyencuong.webtruyen.data.remote.ApiError;
import com.nguyencuong.webtruyen.data.remote.BaseApiResultCallback;
import com.nguyencuong.webtruyen.data.remote.BaseApiServices;
import com.nguyencuong.webtruyen.data.remote.model.HomeModel;
import com.nguyencuong.webtruyen.util.FileUtils;

/**
 * The Class
 * Created by pc on 4/14/2017.
 */

public class HomeServices extends BaseApiServices {

    public interface ResultCallback extends BaseApiResultCallback {

        void onHomeResultSuccess(HomeModel.Data data);
    }

    ResultCallback resultCallback;

    public HomeServices(Context context) {
        super(context);
    }

    public void setResultCallback(ResultCallback resultCallback) {
        this.resultCallback = resultCallback;
    }

    public void loadHomeData() {
        if (resultCallback == null) {
            return;
        }

        String dataJson = FileUtils.getJsonFromAssets(context, PATH_TEST, "home_data.json");
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
}
