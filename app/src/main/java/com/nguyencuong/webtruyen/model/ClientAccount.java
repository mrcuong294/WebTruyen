package com.nguyencuong.webtruyen.model;

/**
 *
 * Created by pc on 4/11/2017.
 */

public class ClientAccount {
    private String id;
    private String fcmKey;
    private String appVer;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFcmKey() {
        return fcmKey;
    }

    public void setFcmKey(String fcmKey) {
        this.fcmKey = fcmKey;
    }

    public String getAppVer() {
        return appVer;
    }

    public void setAppVer(String appVer) {
        this.appVer = appVer;
    }
}
