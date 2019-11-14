package com.example.cuahang_doan.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cuahang_doan.R;
import com.example.cuahang_doan.model.TinTuc;
import com.squareup.picasso.Picasso;

public class ThongTinChiTietTinTuc extends AppCompatActivity {
    private ImageView imghinhtintuc;
    private TextView tentintuc,ngaytintuc,noidungtintuc;
    private Toolbar toolbarThongTinTinTuc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_chi_tiet_tin_tuc);
        anhxa();
        actionbar();
    }

    private void actionbar() {
        setSupportActionBar(toolbarThongTinTinTuc);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbarThongTinTinTuc.setNavigationIcon(R.drawable.back);
        toolbarThongTinTinTuc.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void anhxa() {
        toolbarThongTinTinTuc=findViewById(R.id.toolbarThongTinTinTuc);
        imghinhtintuc=findViewById(R.id.imghinhtintuc);
        tentintuc=findViewById(R.id.tentintuc);
        ngaytintuc=findViewById(R.id.ngaytintuc);
        noidungtintuc=findViewById(R.id.noidungtintuc);
        Intent intent=getIntent();
        if(intent!=null){
            TinTuc tinTuc= (TinTuc) intent.getSerializableExtra("tintuc");
            Picasso.with(getApplicationContext()).load(tinTuc.getHinhbaiviet()).into(imghinhtintuc);
            tentintuc.setText(tinTuc.getTenbaiviet());
            ngaytintuc.setText(tinTuc.getThoigian());
            noidungtintuc.setText(tinTuc.getNoidung());
        }
    }
}
