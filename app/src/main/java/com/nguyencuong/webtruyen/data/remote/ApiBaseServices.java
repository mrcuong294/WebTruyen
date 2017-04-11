package com.nguyencuong.webtruyen.data.remote;

import android.content.Context;

/**
 *
 * Created by pc on 4/11/2017.
 */

public abstract class ApiBaseServices {

    protected static final String SRC = "android";

    protected final Context context;

    public ApiBaseServices(Context context) {
        this.context = context;
    }

    public abstract void cancel();
}
