package com.example.onchat_android;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
    public static Context context;
    private static String baseUrl = "http://10.0.2.2:7242/api/" ;

    @Override
    public void onCreate(){
        super.onCreate();
        context = getApplicationContext();
    }

    public static String getBaseUrl() {
        return baseUrl;
    }

    public static void setBaseUrl(String baseUrl) {
        MyApplication.baseUrl = baseUrl;
    }
}
