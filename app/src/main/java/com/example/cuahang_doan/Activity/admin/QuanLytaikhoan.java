package com.example.cuahang_doan.Activity.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.cuahang_doan.Activity.DangKyTaiKhoan;
import com.example.cuahang_doan.Adapter.TimkiemTaikhoan_Adapter;
import com.example.cuahang_doan.Adapter.admin.Adapter_Quan_ly_tai_khoan;
import com.example.cuahang_doan.R;
import com.example.cuahang_doan.Services.APIServices;
import com.example.cuahang_doan.Services.DataService;
import com.example.cuahang_doan.model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuanLytaikhoan extends AppCompatActivity {
    private RecyclerView Recyclerview_Quan_ly_tai_khoan;
    private EditText edtseachsanphamadmin;
    private ImageView imgsearchadmin,imgmenutaikhoanadmin;
    private Toolbar toolbaradminquanlysp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_lytaikhoan);
        anhxa();
        getDataQuanLyTaiKhoanAdmin();
        setActionBar();
    }
    public void reload(){
        finish();
        startActivity(getIntent());
    }
    private void setActionBar() {
        setSupportActionBar(toolbaradminquanlysp);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbaradminquanlysp.setNavigationIcon(R.drawable.back);
        toolbaradminquanlysp.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getDataQuanLyTaiKhoanAdmin() {
        DataService dataService= APIServices.getService();
        Call<List<User>>callback=dataService.getDataQuanLyTaiKhoanAdmin();
        callback.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                Log.d("AAA","GetdataquanlytaikhoanAdmin: "+response.toString());
                if(response.isSuccessful()){
                    ArrayList<User>arrayList= (ArrayList<User>) response.body();
                    setRecyclerviewAdmin(arrayList);
                }else{
                    Toast.makeText(QuanLytaikhoan.this, "Tạm thời không lấy được dữ liệu", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
    }
    private void timkim(){
        if(!edtseachsanphamadmin.getText().toString().equals("")){
            DataService dataService=APIServices.getService();
            Call<List<User>>callback=dataService.timkiem(edtseachsanphamadmin.getText().toString());
            callback.enqueue(new Callback<List<User>>() {
                @Override
                public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                    Log.d("AAA","event timkiem: "+response.toString());
                    if(response.isSuccessful()){
                        ArrayList arrayList= (ArrayList<User>) response.body();
                        setRecyclerviewAdmin(arrayList);
                    }else{
                        Toast.makeText(getApplicationContext(), "Không tìm thấy kết quả", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<List<User>> call, Throwable t) {

                }
            });
        }else{
            Toast.makeText(getApplicationContext(), "không để trống dữ liệu", Toast.LENGTH_SHORT).show();
        }
    }

    private void anhxa() {
        imgmenutaikhoanadmin=findViewById(R.id.imgmenutaikhoanadmin);
        imgmenutaikhoanadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu=new PopupMenu(QuanLytaikhoan.this,imgmenutaikhoanadmin);
                popupMenu.getMenuInflater().inflate(R.menu.menu_quanlysanpham,popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getItemId()==R.id.them){
                            startActivity(new Intent(getApplicationContext(), DangKyTaiKhoan.class));
                        }
                        return false;
                    }
                });
            }
        });
        toolbaradminquanlysp=findViewById(R.id.toolbaradminquanlysp);
        imgsearchadmin=findViewById(R.id.imgsearchadmin);
        imgsearchadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timkim();
            }
        });
        edtseachsanphamadmin=findViewById(R.id.edtseachsanphamadmin);
        Recyclerview_Quan_ly_tai_khoan=findViewById(R.id.Recyclerview_Quan_ly_tai_khoan);
    }
    private void setRecyclerviewAdmin(ArrayList<User>arrayList){
        Adapter_Quan_ly_tai_khoan adapter=new Adapter_Quan_ly_tai_khoan(QuanLytaikhoan.this,R.layout.layout_quan_ly_tai_khoan_admin,
                arrayList);
        Recyclerview_Quan_ly_tai_khoan.setHasFixedSize(true);
        Recyclerview_Quan_ly_tai_khoan.setLayoutManager(new GridLayoutManager(this,1));
        Recyclerview_Quan_ly_tai_khoan.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
