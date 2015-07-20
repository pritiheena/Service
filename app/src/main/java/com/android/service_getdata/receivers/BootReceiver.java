package com.android.service_getdata.receivers;

import android.app.ActivityManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.android.service_getdata.Service.MyService;

/**
 * Created by inakhatri on 7/14/2015.
 */
public class BootReceiver extends BroadcastReceiver
{
   //private String SERVICE="com.android.service_getdata.Service";
    @Override
    public void onReceive(Context context, Intent intent)
    {
        //boolean isServiceRunning=false;
        Toast.makeText(context,"Boot Completed!",Toast.LENGTH_LONG).show();
        if(context==null && intent==null)
        {
            return;
        }
        //Checking for Service is started or not
//        ActivityManager manager = (ActivityManager) context.getSystemService(context.ACTIVITY_SERVICE);
//        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
//            if (SERVICE.equals(service.getClass().getSimpleName()))
//            {
//                isServiceRunning=true;
//            }
            //else
            //{
                //isServiceRunning=false;
                Intent in=new Intent(context,MyService.class);
                context.startService(in);

            //}

        //}

    }

}
