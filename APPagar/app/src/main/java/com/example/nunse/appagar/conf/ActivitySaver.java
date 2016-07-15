package com.example.nunse.appagar.conf;

import android.app.Activity;

/**
 * Created by nunse on 24/05/2016.
 */
public class ActivitySaver {


    private static Activity saved;

    public static Activity getSaved()
    {
        return saved;
    }

    public static void setSaved(Activity newSaved)
    {
        saved = newSaved;
    }
}
