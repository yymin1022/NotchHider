package com.yong.notchhider;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    boolean isRunning = false;

    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        LinearLayout mainLayout = findViewById(R.id.main_layout);
        prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        editor = prefs.edit();

        if(prefs.getBoolean("isFirst", true)){
            startActivity(new Intent(this, PermissionActivity.class));
        }

        updateView();

        mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isRunning){
                    stopService(new Intent(getApplicationContext(), HideService.class));

                    editor.putBoolean("isStarted", false);
                    editor.apply();
                }else{
                    startService(new Intent(getApplicationContext(), HideService.class));

                    editor.putBoolean("isStarted", true);
                    editor.apply();
                }
                updateView();
            }
        });

    }

    void updateView(){
        ActivityManager manager = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
        ImageView ivNotch = findViewById(R.id.main_image_notch);
        TextView tvInfo = findViewById(R.id.main_text_info);

        ivNotch.setImageResource(R.drawable.ic_service_off);
        tvInfo.setText(getString(R.string.main_text_off));
        isRunning = false;

        if(manager != null){
            for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
                if (HideService.class.getName().equals(service.service.getClassName())) {
                    ivNotch.setImageResource(R.drawable.ic_service_on);
                    tvInfo.setText(getString(R.string.main_text_on));
                    isRunning = true;
                }
            }
        }
    }
}
