package com.nguyencuong.webtruyen.util;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

import com.nguyencuong.webtruyen.BuildConfig;


/**
 * Content class.
 * <p>
 * Created by Mr Cuong on 3/9/2017.
 * Email: vancuong2941989@gmail.com
 */

public class IntentUtils {

    /**
     * Intent open page on facebook app;
     *
     * @param pm The {@link PackageManager}
     * @param fbId The {@link String} facebook ID;
     * @param url The {@link String} facebook url page;
     * @return The {@link Intent}
     */
    public static Intent openFacebook(PackageManager pm, String fbId, String url) {
        Uri uri = null;
        try {
            // Facebook App
            ApplicationInfo applicationInfo = pm.getApplicationInfo("com.facebook.katana", 0);
            //ApplicationInfo applicationInfo2 = pm.getApplicationInfo("com.facebook.lite", 0);
            if (applicationInfo.enabled ) {
                uri = Uri.parse("fb://page/" + fbId);
                return new Intent(Intent.ACTION_VIEW, uri);
            }
        } catch (PackageManager.NameNotFoundException ignored) {
        }

        try {
            //Facebook lite;
            ApplicationInfo applicationInfo = pm.getApplicationInfo("com.facebook.lite", 0);
            if (applicationInfo.enabled ) {
                uri = Uri.parse("fb://page/" + fbId);
                return new Intent(Intent.ACTION_VIEW, uri);
            }
        } catch (PackageManager.NameNotFoundException ignored) {
        }
        // Facebook web
        return new Intent(Intent.ACTION_VIEW, Uri.parse(url));
    }

    public static Intent sendEmail(String email) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
        intent.putExtra(Intent.EXTRA_SUBJECT, "");
        return intent;
    }

    /**
     * The intent is to send message on facebook msg;
     *
     * @param pm The {@link PackageManager}
     * @param fbId The {@link String} facebook ID;
     * @param url The {@link String} facebook url page;
     * @return The {@link Intent}
     */
    public static Intent sendFbMessenger(PackageManager pm, String fbId, String url) {
        try {
            ApplicationInfo applicationInfo = pm.getApplicationInfo("com.facebook.orca", 0);
            if (applicationInfo.enabled) {
                Uri uri = Uri.parse("fb-messenger://user/" + fbId);
                return new Intent(Intent.ACTION_VIEW, uri);
            }
        } catch (PackageManager.NameNotFoundException ignored) {
        }
        return openFacebook(pm, fbId, url);
    }

    public static Intent updateAppFromStore(PackageManager pm, String apkUrl) {
        try {
            ApplicationInfo applicationInfo = pm.getApplicationInfo("com.android.vending", 0);
            if (applicationInfo.enabled) {
                Uri uri = Uri.parse("market://details?id=" + BuildConfig.APPLICATION_ID);
                return new Intent(Intent.ACTION_VIEW, uri);
            }
        } catch (PackageManager.NameNotFoundException ignored) {
        }
        return openUrlOnWebBrowser(apkUrl);
    }

    public static Intent share(String shareLink) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, shareLink);
        return intent;
    }

    public static Intent openUrlOnWebBrowser(String url) {
        return new Intent(Intent.ACTION_VIEW, Uri.parse(url));
    }

}
