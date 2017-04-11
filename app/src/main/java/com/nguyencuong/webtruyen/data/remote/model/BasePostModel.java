package com.nguyencuong.webtruyen.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Base model client post to server;
 * Created by pc on 4/11/2017.
 */

public class BasePostModel {

    @SerializedName("api_key")
    @Expose
    protected String apiKey;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
