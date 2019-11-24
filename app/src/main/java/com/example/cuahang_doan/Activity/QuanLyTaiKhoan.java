package com.example.cuahang_doan.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.cuahang_doan.Fragment.Tai_Khoan.TaiKhoan.DialogFragment_Chinhsuadiachi;
import com.example.cuahang_doan.Fragment.Tai_Khoan.TaiKhoan.DialogFragment_chinhsuathongtintaikhoan;
import com.example.cuahang_doan.Fragment.Tai_Khoan.DonHang.Fragment_SanPhamHoaDon;
import com.example.cuahang_doan.R;
import com.example.cuahang_doan.Services.APIServices;
import com.example.cuahang_doan.Services.DataService;
import com.example.cuahang_doan.model.HoaDon;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuanLyTaiKhoan extends AppCompatActivity {
    private RoundedImageView imgavartaruser;
    private TextView txtusername,txtemail,txtsdt,txtusername1,txtdiachinhanhang,txtchinhsuathongtin,txtchinhsuadiachi;
    private Toolbar toolbarquanlytaikhoan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_tai_khoan);
        anhxa();
        actionbar();
        init();
        getdatasanphamganday();
        sukienchinhsuathongtin();
    }

    private void sukienchinhsuathongtin() {
        txtchinhsuathongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager=getSupportFragmentManager();
                DialogFragment_chinhsuathongtintaikhoan dialog =new DialogFragment_chinhsuathongtintaikhoan();
                dialog.show(fragmentManager,"chinhsuathongtintaikhoan");

            }
        });
        txtchinhsuadiachi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager1=getSupportFragmentManager();
                DialogFragment_Chinhsuadiachi fragment_chinhsuadiachi=new DialogFragment_Chinhsuadiachi();
                fragment_chinhsuadiachi.show(fragmentManager1,"chinhsuadiachi");
            }
        });
    }

    private void getdatasanphamganday() {
        if(MainActivity.sharedPreferences.getInt("iduser",0)!=0){
            DataService dataService= APIServices.getService();
            Call<List<HoaDon>>callback=dataService.getdatahoadon(MainActivity.sharedPreferences
            .getInt("iduser",0)+"");
            callback.enqueue(new Callback<List<HoaDon>>() {
                @Override
                public void onResponse(Call<List<HoaDon>> call, Response<List<HoaDon>> response) {
                    Log.d("AAA","don hang gan day: "+response.toString());
                    List<HoaDon>arrayList=  response.body();
                    Fragment_SanPhamHoaDon fragment_sanPhamHoaDon =new Fragment_SanPhamHoaDon(arrayList);
                    FragmentManager fragmentManager=getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                    fragmentTransaction.add(R.id.linnerlaoutdonhangganday, fragment_sanPhamHoaDon,"donhangganday");
                    fragmentTransaction.commit();
                }

                @Override
                public void onFailure(Call<List<HoaDon>> call, Throwable t) {

                }
            });
        }
    }

    private void actionbar() {
        setSupportActionBar(toolbarquanlytaikhoan);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarquanlytaikhoan.setNavigationIcon(R.drawable.back);
        toolbarquanlytaikhoan.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void init() {
        if(MainActivity.sharedPreferences.getInt("iduser",0)==0){
            startActivity(new Intent(getApplicationContext(),Login.class));
        }else{
            Picasso.with(getApplicationContext()).load(MainActivity.sharedPreferences.getString("hinh",""))
                    .into(imgavartaruser);
            txtusername.setText(MainActivity.sharedPreferences.getString("username",""));
            txtemail.setText(MainActivity.sharedPreferences.getString("email",""));
            txtusername1.setText(MainActivity.sharedPreferences.getString("username",""));
            txtdiachinhanhang.setText(MainActivity.sharedPreferences.getString("diachi",""));
            txtsdt.setText(MainActivity.sharedPreferences.getString("sodienthoai",""));

        }
    }

    private void anhxa() {
        txtsdt=findViewById(R.id.txtsdt);
        toolbarquanlytaikhoan=findViewById(R.id.toolbarquanlytaikhoan);
        imgavartaruser=findViewById(R.id.imgavartaruser);
        txtusername=findViewById(R.id.txtusername);
        txtemail=findViewById(R.id.txtemail);
        txtusername1=findViewById(R.id.txtusername1);
        txtdiachinhanhang=findViewById(R.id.txtdiachinhanhang);
        txtchinhsuathongtin=findViewById(R.id.txtchinhsuathongtin);
        txtchinhsuadiachi=findViewById(R.id.txtchinhsuadiachi);
    }
}
