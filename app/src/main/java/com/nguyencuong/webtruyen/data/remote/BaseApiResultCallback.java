package com.nguyencuong.webtruyen.data.remote;

/**
 *
 * Created by pc on 4/11/2017.
 */

public interface BaseApiResultCallback {

    void onApiResultFailure(ApiError apiError);

    void onApiConnectInternetFailure(ApiError apiError);
}
