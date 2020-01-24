package com.yong.notchhider;

import android.content.*;

public class BootReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction() != null && intent.getAction().equals("android.intent.action.BOOT_COMPLETED")){
            SharedPreferences prefs = context.getSharedPreferences("androesPrefName", Context.MODE_PRIVATE);
            if(prefs.getBoolean("isStarted", false)){
                context.startService(new Intent(context, HideService.class));
            }
        }
    }
}