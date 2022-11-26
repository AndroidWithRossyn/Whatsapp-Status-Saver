package com.banrossyn.socialsaver.util_items;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePrefs {
    public static String PREFERENCE = "InStory";




    private Context ctx;
    private SharedPreferences sharedPreferences;
    private static SharePrefs instance;



    public SharePrefs(Context context) {
        ctx = context;
        sharedPreferences = context.getSharedPreferences(PREFERENCE, 0);
        //sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static SharePrefs getInstance(Context ctx) {
        if (instance == null) {
            instance = new SharePrefs(ctx);
        }
        return instance;
    }

    public void putString(String key, String val) {
        sharedPreferences.edit().putString(key, val).apply();
    }

    public String getString(String key) {
        return sharedPreferences.getString(key, "");
    }

    public void putInt(String key, Integer val) {
        sharedPreferences.edit().putInt(key, val).apply();
    }

    public void putBoolean(String key, Boolean val) {
        sharedPreferences.edit().putBoolean(key, val).apply();
    }

    public Boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public int getInt(String key) {
        return sharedPreferences.getInt(key, 0);
    }

    public void clearSharePrefs() {
        sharedPreferences.edit().clear().apply();
    }
}
