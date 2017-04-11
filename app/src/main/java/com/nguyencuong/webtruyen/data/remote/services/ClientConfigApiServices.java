package com.nguyencuong.webtruyen.data.remote.services;

import android.content.Context;

import com.nguyencuong.webtruyen.data.remote.BaseApiResultCallback;
import com.nguyencuong.webtruyen.data.remote.BaseApiServices;

/**
 *
 * Created by pc on 4/11/2017.
 */

public class ClientConfigApiServices extends BaseApiServices {

    public interface ResultCallback extends BaseApiResultCallback {

        void onClientConfigResultSuccess();
    }

    public ClientConfigApiServices(Context context) {
        super(context);
    }

    public void clientConfigExec() {

    }

    @Override
    public void cancel() {

    }
}
