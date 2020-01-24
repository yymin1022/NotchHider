package com.yong.notchhider;

import android.content.*;
import android.util.Log;

public class BootReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction() != null && intent.getAction().equals("android.intent.action.BOOT_COMPLETED")){
            Log.d("HELLO", "BOOT RECEIVED");

            SharedPreferences prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
            if(prefs.getBoolean("isStarted", false)){
                context.startService(new Intent(context, HideService.class));
            }
        }
    }
}