package com.yong.notchhider;

import android.content.*;
import android.os.Build;

public class BootReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction() != null && intent.getAction().equals("android.intent.action.BOOT_COMPLETED")){
            SharedPreferences prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);

            if(prefs.getBoolean("isStarted", false)){
                Intent serviceIntent = new Intent(context, HideService.class);
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    context.startForegroundService(serviceIntent);
                }else{
                    context.startService(serviceIntent);
                }
            }
        }
    }
}