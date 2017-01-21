package com.sandeepmaikhuri.apps.restaurantlocator.presentation.ui.activities;

import android.app.Activity;
import android.widget.Toast;

/**
 * @author  Sandeep Maikhuri
 */
public class Hello extends Activity
{
    @Override
    protected void onResume()
    {
        super.onResume();
        Toast.makeText(this, "Recursively Alive!!", Toast.LENGTH_LONG).show();
    }
}
