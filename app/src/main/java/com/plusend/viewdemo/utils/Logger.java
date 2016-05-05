package com.plusend.viewdemo.utils;

import android.util.Log;

/**
 * Created by plusend on 16/5/4.
 */
public class Logger {
    private static final String TAG = "ViewDemo";

    public static void d(String tag, String s){
        Log.d(TAG, tag + " === " + s);
    }
}
