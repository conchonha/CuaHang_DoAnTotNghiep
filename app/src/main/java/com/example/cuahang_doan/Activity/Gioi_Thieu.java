package com.example.cuahang_doan.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.cuahang_doan.R;
import com.example.cuahang_doan.Services.APIServices;
import com.example.cuahang_doan.Services.DataService;
import com.example.cuahang_doan.model.GioithieuShop;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Gioi_Thieu extends AppCompatActivity {
    private Toolbar toolbargioithieu;
    private TextView txttentrusogioithieu,txtdiachigioithieu,txtsodienthoaigioithieu,txtemailgioithieushop,
            txtwebsitegioithieushop,txtfanpagegioithieushop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gioi__thieu);
        anhxa();
        getdatagioithieushop();
    }

    private void getdatagioithieushop() {
        DataService dataService= APIServices.getService();
        Call<List<GioithieuShop>>callback=dataService.Getdatagioithieushop();
        callback.enqueue(new Callback<List<GioithieuShop>>() {
            @Override
            public void onResponse(Call<List<GioithieuShop>> call, Response<List<GioithieuShop>> response) {
                Log.d("AAA","Gioi thieu shop: "+response.toString());
                if(response.isSuccessful()){
                    ArrayList<GioithieuShop>arrayList= (ArrayList<GioithieuShop>) response.body();
                    GioithieuShop gioithieuShop=arrayList.get(0);
                    txttentrusogioithieu.setText(gioithieuShop.getTenCuaHang());
                    txtdiachigioithieu.setText(gioithieuShop.getTruSoChinh());
                    txtsodienthoaigioithieu.setText(gioithieuShop.getDienThoai());
                    txtemailgioithieushop.setText(gioithieuShop.getEmail());
                    txtwebsitegioithieushop.setText(gioithieuShop.getWebsite());
                    txtfanpagegioithieushop.setText(gioithieuShop.getFanpage());

                }
            }

            @Override
            public void onFailure(Call<List<GioithieuShop>> call, Throwable t) {

            }
        });
    }

    private void anhxa() {
        txttentrusogioithieu=findViewById(R.id.txttentrusogioithieu);
        txtdiachigioithieu=findViewById(R.id.txtdiachigioithieu);
        txtsodienthoaigioithieu=findViewById(R.id.txtsodienthoaigioithieu);
        txtemailgioithieushop=findViewById(R.id.txtemailgioithieushop);
        txtwebsitegioithieushop=findViewById(R.id.txtwebsitegioithieushop);
        txtfanpagegioithieushop=findViewById(R.id.txtfanpagegioithieushop);


        toolbargioithieu=findViewById(R.id.toolbargioithieu);
        setSupportActionBar(toolbargioithieu);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbargioithieu.setNavigationIcon(R.drawable.back);
        toolbargioithieu.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
