package com.yong.notchhider;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.core.app.NotificationCompat;

public class HideService extends Service {
    LayoutInflater inflater;
    NotificationCompat.Builder notificationBuilder;
    NotificationManager notificationManager;
    View windowView;
    WindowManager windowManager;
    WindowManager.LayoutParams windowParams;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel channel = new NotificationChannel("RunningBackground", "Channel Name", NotificationManager.IMPORTANCE_MIN);
            channel.setImportance(NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("Channel Description");
            if(notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
        notificationBuilder = new NotificationCompat.Builder(getApplicationContext(), "RunningBackground")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                .setContentTitle("Notification Title")
                .setContentText("Notification Content")
                .setOngoing(true)
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setAutoCancel(false);
        notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        if(notificationBuilder != null && notificationManager != null){
            startForeground(1379, notificationBuilder.build());
        }

        inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);

        windowManager = (WindowManager)getSystemService(WINDOW_SERVICE);
        windowParams = new WindowManager.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.RGB_888
        );
        windowParams.gravity = Gravity.TOP;
        windowView = inflater.inflate(R.layout.window_view, null);

        windowManager.addView(windowView, windowParams);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopForeground(true);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
