package com.yong.notchhider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnStart = findViewById(R.id.main_btn_start);
        Button btnStop = findViewById(R.id.main_btn_stop);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(view.getId()){
                    case R.id.main_btn_start:
                        startService(new Intent(MainActivity.this, HideService.class));
                        break;
                    case R.id.main_btn_stop:
                        stopService(new Intent(MainActivity.this, HideService.class));
                        break;
                }
            }
        };
        btnStart.setOnClickListener(onClickListener);
        btnStop.setOnClickListener(onClickListener);

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        if(prefs.getBoolean("isFirst", true)){
            startActivity(new Intent(this, PermissionActivity.class));
        }
    }
}
