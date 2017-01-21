package com.sandeepmaikhuri.apps.restaurantlocator.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkStatus
{
    private Context context;

    /**
     * @param context
     * @return void
     * @constructor
     */
    public NetworkStatus(Context context)
    {
        this.context = context;
    }

    /**
     * This method is used to check whether internet is on or off
     *
     * @param none
     * @return boolean
     */
    public boolean isInternetOn()
    {
        boolean val = false;
        ConnectivityManager connec = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        // ARE WE CONNECTED TO THE NET
        if (connec.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTED || connec.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTING || connec.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTING || connec.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED)
        {
            val = true;
        }
        else if (connec.getNetworkInfo(0).getState() == NetworkInfo.State.DISCONNECTED || connec.getNetworkInfo(1).getState() == NetworkInfo.State.DISCONNECTED)
        {
            val = false;
        }
        return val;
    }

    /**
     * This method is used to show internet alert dialog
     *
     * @param none
     * @return void
     */
    public void showInternetAlertDialog()
    {
        new AlertDialog.Builder(context).setMessage("No Internet Connection").setNeutralButton("OK",
                new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.dismiss();
                    }
                }).show();
    }
}