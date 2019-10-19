package com.example.cuahang_doan.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.cuahang_doan.Adapter.Adapter_Sanphammoinhat;
import com.example.cuahang_doan.R;
import com.example.cuahang_doan.Services.APIServices;
import com.example.cuahang_doan.Services.DataService;
import com.example.cuahang_doan.model.DanhMucCon;
import com.example.cuahang_doan.model.GetdataSanphammoinhat;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SanPham extends AppCompatActivity {
    private RecyclerView recyclerviewsanphamdanhmuc;
    private String giatri="";
    private Toolbar tollbarsanpham;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_san_pham);
        anhxa();
        getdatasp();
        settitleToolbar();
    }

    private void settitleToolbar() {
        if(giatri.equals("laptop")){
            tollbarsanpham.setTitle("LapTop - Macbook");
        }
        if(giatri.equals("manhinh")){
            tollbarsanpham.setTitle("Màn Hình - LapTop");
        }
        if(giatri.equals("banphim")){
            tollbarsanpham.setTitle("Bàn Phím - LapTop");
        }
        if(giatri.equals("pin")){
            tollbarsanpham.setTitle("Pin - LapTop");
        }
        if(giatri.equals("sac")){
            tollbarsanpham.setTitle("Sạc - LapTop");
        }
        if(giatri.equals("tbluutru")){
            tollbarsanpham.setTitle("TB Lưu Trữ - Phụ Kiện");
        }
        if(giatri.equals("loa")){
            tollbarsanpham.setTitle("Loa - LapTop");
        }
        if(giatri.equals("microphone")){
            tollbarsanpham.setTitle("Microphone");
        }
        if(giatri.equals("tainghe")){
            tollbarsanpham.setTitle("Tai Nghe");
        }
        if(giatri.equals("webcam")){
            tollbarsanpham.setTitle("Webcam");
        }
        if(giatri.equals("cardamthanh")){
            tollbarsanpham.setTitle("Card Âm Thanh");
        }

    }

    private void getdatasp() {
        Intent intent=getIntent();
        if(intent.hasExtra("id")){
            giatri= intent.getStringExtra("id");
            Toast.makeText(this, giatri, Toast.LENGTH_SHORT).show();
            DataService dataService= APIServices.getService();
            Call<List<GetdataSanphammoinhat>>callback=dataService.getdatasanphamdanhmuc(giatri);
            callback.enqueue(new Callback<List<GetdataSanphammoinhat>>() {
                @Override
                public void onResponse(Call<List<GetdataSanphammoinhat>> call, Response<List<GetdataSanphammoinhat>> response) {
                    Log.d("AAA","San Pham tim kiem: "+response.toString());
                    if(response.isSuccessful()){
                        ArrayList<GetdataSanphammoinhat>arrayList= (ArrayList<GetdataSanphammoinhat>) response.body();
                        Adapter_Sanphammoinhat adapter=new Adapter_Sanphammoinhat(getApplicationContext(),R.layout.layoutsanpham,arrayList);
                        recyclerviewsanphamdanhmuc.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<List<GetdataSanphammoinhat>> call, Throwable t) {

                }
            });
        }
    }

    private void anhxa() {
        tollbarsanpham=findViewById(R.id.tollbarsanpham);
        setSupportActionBar(tollbarsanpham);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tollbarsanpham.setNavigationIcon(R.drawable.back);
        tollbarsanpham.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        recyclerviewsanphamdanhmuc=findViewById(R.id.recyclerviewsanphamdanhmuc);
        recyclerviewsanphamdanhmuc.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerviewsanphamdanhmuc.setLayoutManager(linearLayoutManager);
    }
}
