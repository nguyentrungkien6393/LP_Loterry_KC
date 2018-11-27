package com.kien.lp.myapplication.library;

import android.content.Context;
import android.content.res.Configuration;

/**
 * Created by THIENVIET-DEV on 13/12/2017.
 */

public class CheckTablet {

    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }



}
