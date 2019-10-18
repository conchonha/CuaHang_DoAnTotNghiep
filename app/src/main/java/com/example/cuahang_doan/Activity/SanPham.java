package com.example.cuahang_doan.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.cuahang_doan.R;

public class SanPham extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_san_pham);
        Intent intent=getIntent();
        if(intent.hasExtra("id")){
           String giatri= intent.getStringExtra("id");
            Toast.makeText(this, giatri, Toast.LENGTH_SHORT).show();
        }
    }
}
