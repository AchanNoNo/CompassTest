package com.example.achan.compasstest;

import android.app.Application;
import android.content.Context;

/**
 * Created by Achan on 2016/10/17.
 */

public class MyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        context=getApplicationContext();
    }

    public static Context getContext(){
        return context;
    }
}
