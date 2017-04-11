package com.nguyencuong.webtruyen.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 * Created by pc on 4/11/2017.
 */

public class BaseGetModel {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("message")
    @Expose
    private String message;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public int getCode() {
        if (code == null) return 0;
        return Integer.parseInt(code);
    }

    public String getMessage() {
        return message;
    }
}
