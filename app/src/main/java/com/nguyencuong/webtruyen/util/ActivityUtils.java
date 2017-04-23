package com.nguyencuong.webtruyen.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;

/**
 * Content class.
 * <p>
 * Created by Mr Cuong on 4/21/2017.
 * Email: vancuong2941989@gmail.com
 */

public class ActivityUtils {

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static ActivityOptionsCompat getActivityOptionBookDetail(Activity context, View v) {
        return ActivityOptionsCompat.
                makeSceneTransitionAnimation(context, v, "profile");
    }
}
