package com.example.aidldemo.utils;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
    private static Context sContext;

    @Override
    public void onCreate() {
        sContext = getApplicationContext();
        super.onCreate();
    }

    public static Context getContext(){
        return sContext;
    }
}
