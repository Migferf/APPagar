package com.example.nunse.appagar.conf;

import android.app.Application;
import android.content.Context;

/**
 * Created by nunse on 06/04/2016.
 */
public class APPagar extends Application{

    private static Context mContext;

    @Override
    public void onCreate()
    {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getContext()
    {
        return mContext;
    }
}
