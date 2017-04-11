package com.nguyencuong.webtruyen.util;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

/**
 * Class file utils
 * Created by pc on 4/11/2017.
 */

public class FileUtils {

    /**
     * The method get json file from assets folder;
     *
     * @param context The {@link Context}
     * @param filePath The file path in assets folder, #example: json/files
     * @param filename The file name #example config.json;
     * @return The json string;
     */
    public static String getJsonFromAssets(Context context, String filePath, String filename) {

        try {
            InputStream is = context.getAssets().open(filePath + "/" + filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            return new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
