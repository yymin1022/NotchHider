package com.yong.notchhider;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.hardware.display.DisplayManager;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.OrientationEventListener;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.core.app.NotificationCompat;

public class HideService extends Service{
    int currentRotation = 0;

    DisplayManager displayManager;
    DisplayManager.DisplayListener displayListener;
    LayoutInflater inflater;
    NotificationCompat.Builder notificationBuilder;
    NotificationManager notificationManager;
    View windowView;
    WindowManager windowManager;
    WindowManager.LayoutParams windowParams;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        displayManager = (DisplayManager) this.getSystemService(Context.DISPLAY_SERVICE);
        windowManager = (WindowManager)getSystemService(WINDOW_SERVICE);

        displayListener = new DisplayManager.DisplayListener() {
            @Override
            public void onDisplayAdded(int displayId) {

            }

            @Override
            public void onDisplayRemoved(int displayId) {

            }

            @Override
            public void onDisplayChanged(int displayId) {
                currentRotation = windowManager.getDefaultDisplay().getRotation();
                switch(currentRotation){
                    case Surface.ROTATION_0:
                        windowManager.removeView(windowView);
                        windowParams = new WindowManager.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                180,
                                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN|
                                        WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR|
                                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE|
                                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                                PixelFormat.TRANSLUCENT
                        );
                        windowParams.gravity = Gravity.TOP;
                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){
                            windowParams.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
                        }
                        windowView = inflater.inflate(R.layout.window_view_portrait, null);
                        windowManager.addView(windowView, windowParams);
                        break;
                    case Surface.ROTATION_90:
                        windowManager.removeView(windowView);
                        windowParams = new WindowManager.LayoutParams(
                                180,
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN|
                                        WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR|
                                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE|
                                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                                PixelFormat.TRANSLUCENT
                        );
                        windowParams.gravity = Gravity.LEFT;
                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){
                            windowParams.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
                        }
                        windowView = inflater.inflate(R.layout.window_view_landscape, null);
                        windowManager.addView(windowView, windowParams);
                        break;
                    case Surface.ROTATION_270:
                        windowManager.removeView(windowView);
                        windowParams = new WindowManager.LayoutParams(
                                180,
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN|
                                        WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR|
                                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE|
                                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                                PixelFormat.TRANSLUCENT
                        );
                        windowParams.gravity = Gravity.RIGHT;
                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){
                            windowParams.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
                        }
                        windowView = inflater.inflate(R.layout.window_view_landscape_reverse, null);
                        windowManager.addView(windowView, windowParams);
                        break;
                }
            }
        };

        currentRotation = windowManager.getDefaultDisplay().getRotation();
        switch (currentRotation){
            case Surface.ROTATION_0:
                windowParams = new WindowManager.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        180,
                        WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                        WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN|
                                WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR|
                                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE|
                                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                        PixelFormat.TRANSLUCENT
                );
                windowParams.gravity = Gravity.TOP;
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){
                    windowParams.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
                }
                windowView = inflater.inflate(R.layout.window_view_portrait, null);
                break;
            case Surface.ROTATION_90:
                windowParams = new WindowManager.LayoutParams(
                        180,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                        WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN|
                                WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR|
                                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE|
                                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                        PixelFormat.TRANSLUCENT
                );
                windowParams.gravity = Gravity.LEFT;
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){
                    windowParams.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
                }
                windowView = inflater.inflate(R.layout.window_view_landscape, null);
                break;
            case Surface.ROTATION_270:
                windowParams = new WindowManager.LayoutParams(
                        180,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                        WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN|
                                WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR|
                                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE|
                                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                        PixelFormat.TRANSLUCENT
                );
                windowParams.gravity = Gravity.RIGHT;
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){
                    windowParams.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
                }
                windowView = inflater.inflate(R.layout.window_view_landscape_reverse, null);
                break;
        }
        windowManager.addView(windowView, windowParams);

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

        if(displayListener != null){
            displayManager.registerDisplayListener(displayListener, new Handler());
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        windowManager.removeView(windowView);

        if(displayListener != null){
            displayManager.unregisterDisplayListener(displayListener);
        }

        stopForeground(true);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
