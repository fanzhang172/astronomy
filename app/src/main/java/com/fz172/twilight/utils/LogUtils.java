package com.fz172.twilight.utils;

import android.util.Log;

import com.fz172.twilight.BuildConfig;

public final class LogUtils {
    private LogUtils() {
    }

    public static void v(String tag, String message, Object... args) {
        if (BuildConfig.DEBUG || Log.isLoggable(tag, Log.VERBOSE)) {
            Log.v(tag, args == null || args.length == 0 ? message : String.format(message, args));
        }
    }

    public static void d(String tag, String message, Object... args) {
        if (BuildConfig.DEBUG || Log.isLoggable(tag, Log.DEBUG)) {
            Log.d(tag, args == null || args.length == 0 ? message : String.format(message, args));
        }
    }

    public static void i(String tag, String message, Object... args) {
        if (BuildConfig.DEBUG || Log.isLoggable(tag, Log.INFO)) {
            Log.i(tag, args == null || args.length == 0 ? message : String.format(message, args));
        }
    }

    public static void w(String tag, String message, Object... args) {
        if (BuildConfig.DEBUG || Log.isLoggable(tag, Log.WARN)) {
            Log.w(tag, args == null || args.length == 0 ? message : String.format(message, args));
        }
    }

    public static void e(String tag, String message, Object... args) {
        if (BuildConfig.DEBUG || Log.isLoggable(tag, Log.ERROR)) {
            Log.e(tag, args == null || args.length == 0 ? message : String.format(message, args));
        }
    }

    public static void e(String tag, Exception e, String message, Object... args) {
        if (BuildConfig.DEBUG || Log.isLoggable(tag, Log.ERROR)) {
            Log.e(tag, args == null || args.length == 0 ? message : String.format(message, args),
                    e);
        }
    }

    public static void wtf(String tag, String message, Object... args) {
        if (BuildConfig.DEBUG || Log.isLoggable(tag, Log.ASSERT)) {
            Log.wtf(tag, args == null || args.length == 0 ? message : String.format(message, args));
        }
    }
}
