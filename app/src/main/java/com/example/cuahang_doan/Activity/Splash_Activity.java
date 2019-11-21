package com.example.cuahang_doan.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.example.cuahang_doan.Activity.admin.Admin;
import com.example.cuahang_doan.R;


public class Splash_Activity extends AppCompatActivity {
    private RelativeLayout splashRelivelayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_);
        splashRelivelayout=findViewById(R.id.relativeSplash);
        Animation animation=AnimationUtils.loadAnimation(Splash_Activity.this,R.anim.anim_splash);
        splashRelivelayout.setAnimation(animation);
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        },4000);


    }
}
