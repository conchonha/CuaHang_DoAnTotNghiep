package com.example.cuahang_doan.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cuahang_doan.Fragment.Fragment_Danhgiasanpham;
import com.example.cuahang_doan.R;
import com.example.cuahang_doan.Services.APIServices;
import com.example.cuahang_doan.Services.DataService;
import com.example.cuahang_doan.model.SanPham;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Chitietsanpham extends AppCompatActivity {
    private ViewPager ChitietsanphamViewpaget;
    private TextView txttensanphamchitiet,txtthongsokythuat,txtmotasanphamchitiet;
    public  ArrayList<SanPham>arrayList=new ArrayList<>();
    public  int id;
    private CollapsingToolbarLayout collapsingtoolbar;
    private Toolbar toolbardanhsachbaihat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietsanpham);
        actionbar();
        anhxa();
        init();
    }

    private void addFragment(String masp) {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        Fragment_Danhgiasanpham fragment=new Fragment_Danhgiasanpham();
        Bundle bundle=new Bundle();
        bundle.putString("masp",masp);
        fragment.setArguments(bundle);
        fragmentTransaction.add(R.id.linnerlaout,fragment);
        fragmentTransaction.commit();
    }


    private void actionbar() {
        toolbardanhsachbaihat=findViewById(R.id.toolbardanhsachbaihat);
        setSupportActionBar(toolbardanhsachbaihat);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbardanhsachbaihat.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void init() {
        Intent intent=getIntent();
        if(intent!=null){
            if(intent.hasExtra("id")){
                id=intent.getIntExtra("id",0);
                Log.d("AAA",id+"Ma san pham chi tiet san pham");
                getdatasanpham();
            }
        }
    }

    private void anhxa() {
        ChitietsanphamViewpaget=findViewById(R.id.ChitietsanphamViewpaget);
        txttensanphamchitiet=findViewById(R.id.txttensanphamchitiet);
        txtthongsokythuat=findViewById(R.id.txtthongsokythuat);
        txtmotasanphamchitiet=findViewById(R.id.txtmotasanphamchitiet);
        collapsingtoolbar=findViewById(R.id.collapsingtoolbar);
        //Expanded set màu mở rộng
        collapsingtoolbar.setExpandedTitleColor(getResources().getColor(R.color.colorwhile));
        //set màu thu hẹp
        collapsingtoolbar.setCollapsedTitleTextColor(Color.WHITE);
    }


    private void getdatasanpham(){
        DataService dataService= APIServices.getService();
        Call<List<SanPham>>callback=dataService.postSanphamchitiet(id+"");
        callback.enqueue(new Callback<List<SanPham>>() {
            @Override
            public void onResponse(Call<List<SanPham>> call, Response<List<SanPham>> response) {
                Log.d("AAA","chitietsanpham"+response.toString());
                if(response!=null){
                    arrayList= (ArrayList<SanPham>) response.body();
                    if(arrayList!=null){
                        txtmotasanphamchitiet.setText(arrayList.get(0).getMota());
                        txttensanphamchitiet.setText(arrayList.get(0).getTenSanPham());
                        txtthongsokythuat.setText(arrayList.get(0).getThongSoKyThuat());
                        String masp=arrayList.get(0).getId()+"";
                        addFragment(masp);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<SanPham>> call, Throwable t) {
                Toast.makeText(Chitietsanpham.this, "Đã hết hạn dữ liệu vui lòng thử lại sau", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
