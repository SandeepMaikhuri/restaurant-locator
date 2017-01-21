package com.sandeepmaikhuri.apps.restaurantlocator.utils;

import android.content.Context;
import android.graphics.Typeface;

import com.sandeepmaikhuri.apps.restarauntlocator.R;


/**
 * Created by Sandeep on 9/6/16.
 */

public class CustomTypeface
{
    public Typeface coolDots;

    public CustomTypeface(Context context)
    {
        coolDots = Typeface.createFromAsset(context.getAssets(), context.getResources().getString(R.string.cool_dots));
    }
}