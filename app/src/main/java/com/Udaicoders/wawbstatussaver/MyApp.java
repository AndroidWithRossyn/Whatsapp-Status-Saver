package com.Udaicoders.wawbstatussaver;

import android.app.Application;
import android.content.Context;

import com.Udaicoders.wawbstatussaver.util.AdController;


public class MyApp extends Application {


    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        // Enable verbose OneSignal logging to debug issues if needed.



        if (!AdController.isLoadIronSourceAd){
            AdController.initAd(this);
        }
    }

    public static Context getAppContext() {
        return context;
    }
}
