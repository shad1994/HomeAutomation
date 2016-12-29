package com.example.shadab.homeautomation;

import android.app.Application;
import android.content.Context;

/**
 * Created by shadab on 12/27/2016.
 */
public class MyApplication extends Application {
    private static MyApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance=this;
    }
    public static MyApplication getsInstance(){
        return sInstance;
    }


}
