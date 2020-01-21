package com.yong.notchhider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class PermissionActivity extends AppCompatActivity {
    Button btnDone;
    Button btnGrant;
    Button btnNext;
    Button btnPrev;
    LinearLayout layoutFirst;
    LinearLayout layoutSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

        btnDone = findViewById(R.id.permisson_btn_done);
        btnGrant = findViewById(R.id.permisson_btn_grant);
        btnNext = findViewById(R.id.permission_btn_next);
        btnPrev = findViewById(R.id.permission_btn_prev);
        layoutFirst = findViewById(R.id.permission_layout_first);
        layoutSecond = findViewById(R.id.permission_layout_second);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(view.getId()){
                    case R.id.permisson_btn_done:
                        finish();
                        break;
                    case R.id.permisson_btn_grant:
                        startActivity(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName())));
                        break;
                    case R.id.permission_btn_next:
                        layoutFirst.setVisibility(View.INVISIBLE);
                        layoutSecond.setVisibility(View.VISIBLE);
                        break;
                    case R.id.permission_btn_prev:
                        layoutFirst.setVisibility(View.VISIBLE);
                        layoutSecond.setVisibility(View.INVISIBLE);
                        break;
                }
            }
        };

        btnDone.setOnClickListener(onClickListener);
        btnGrant.setOnClickListener(onClickListener);
        btnNext.setOnClickListener(onClickListener);
        btnPrev.setOnClickListener(onClickListener);

        layoutSecond.setVisibility(View.INVISIBLE);
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
