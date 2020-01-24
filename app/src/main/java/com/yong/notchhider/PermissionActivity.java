package com.yong.notchhider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;

public class PermissionActivity extends AppCompatActivity {
    Button btnDone;
    Button btnGrant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

        btnDone = findViewById(R.id.permisson_btn_done);
        btnGrant = findViewById(R.id.permisson_btn_grant);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(view.getId()){
                    case R.id.permisson_btn_done:
                        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
                        SharedPreferences.Editor ed = prefs.edit();
                        ed.putBoolean("isFirst", false);
                        ed.apply();

                        finish();
                        break;
                    case R.id.permisson_btn_grant:
                        startActivity(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName())));
                        break;
                }
            }
        };

        btnDone.setOnClickListener(onClickListener);
        btnGrant.setOnClickListener(onClickListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!Settings.canDrawOverlays(this)) {
            btnDone.setEnabled(false);
            btnGrant.setEnabled(true);
        }else{
            btnDone.setEnabled(true);
            btnGrant.setEnabled(false);
        }
    }
}
