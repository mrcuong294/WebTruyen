package com.nguyencuong.webtruyen.data.remote;

import android.content.Context;

/**
 *
 * Created by pc on 4/11/2017.
 */

public abstract class BaseApiServices {

    protected static final String assetPath = "json";

    protected static final String PATH_TEST = "testdata";

    protected static final String SRC = "android";

    protected final Context context;

    public BaseApiServices(Context context) {
        this.context = context;
    }

    public abstract void cancel();
}
