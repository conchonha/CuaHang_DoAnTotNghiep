package com.example.cuahang_doan.Activity.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cuahang_doan.Activity.SanPham;
import com.example.cuahang_doan.Adapter.Adapter_SanPham;
import com.example.cuahang_doan.R;
import com.example.cuahang_doan.Services.APIServices;
import com.example.cuahang_doan.Services.DataService;
import com.example.cuahang_doan.model.GetdataSanphammoinhat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuanLySanPham extends AppCompatActivity {
    private RecyclerView recyclerviewquanlysanphamadmin;
    private TextView txttendanhmucquanlyadmin,txtluachonadminsanpham;
    private Toolbar toolbaradminquanlysp;
    private EditText edtseachsanphamadmin;
    private ImageView imgsearchadmin,img_MenuProductAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_san_pham);
        anhxa();
        getdatasanpham("1");
        PopupMenuluachon();
       timkim();
       onclick_Views();
    }

    private void onclick_Views() {
        setSupportActionBar(toolbaradminquanlysp);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbaradminquanlysp.setNavigationIcon(R.drawable.back);
        toolbaradminquanlysp.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        img_MenuProductAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu=new PopupMenu(getApplicationContext(),img_MenuProductAdmin);
                popupMenu.getMenuInflater().inflate(R.menu.menu_quanlysanpham,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getItemId()==R.id.them){
                            startActivity(new Intent(getApplicationContext(),ThemSanPham.class));
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }

    private void timkim() {
        imgsearchadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search=edtseachsanphamadmin.getText().toString();
                Toast.makeText(getApplicationContext(), search, Toast.LENGTH_SHORT).show();
                DataService dataService=APIServices.getService();
                Call<List<GetdataSanphammoinhat>>callback=dataService.getdataTimkiemsanpham(search);
                callback.enqueue(new Callback<List<GetdataSanphammoinhat>>() {
                    @Override
                    public void onResponse(Call<List<GetdataSanphammoinhat>> call, Response<List<GetdataSanphammoinhat>> response) {
                        Log.d("AAA","Tim kim sp: "+response.toString());
                        if(response.isSuccessful()){
                            ArrayList<GetdataSanphammoinhat>arrayList= (ArrayList<GetdataSanphammoinhat>) response.body();
                            addrecyclerview(arrayList);
                        }else{
                            Toast.makeText(QuanLySanPham.this, "Không tìm thấy dữ liệu", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<GetdataSanphammoinhat>> call, Throwable t) {
                        Log.d("AAA","Loi timkim Sp: "+t.toString());

                    }
                });
            }
        });
    }

    private void PopupMenuluachon() {
        txtluachonadminsanpham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu=new PopupMenu(QuanLySanPham.this,txtluachonadminsanpham);
                popupMenu.getMenuInflater().inflate(R.menu.menuluachonsanphamadmin,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        if(item.getItemId()==R.id.laptopmacbook){
                            getdatasanpham("1");
                        }
                        if(item.getItemId()==R.id.linhkien){
                            getdatasanpham("2");
                        }
                        if(item.getItemId()==R.id.tbluutrupk){
                            getdatasanpham("3");
                        }
                        if(item.getItemId()==R.id.tbnghenhin){
                            getdatasanpham("4");
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }





    private void anhxa() {
        img_MenuProductAdmin=findViewById(R.id.img_MenuProductAdmin);
        edtseachsanphamadmin=findViewById(R.id.edtseachsanphamadmin);
        imgsearchadmin=findViewById(R.id.imgsearchadmin);
        toolbaradminquanlysp=findViewById(R.id.toolbaradminquanlysp);
        txtluachonadminsanpham=findViewById(R.id.txtluachonadminsanpham);
        txttendanhmucquanlyadmin=findViewById(R.id.txttendanhmucquanlyadmin);
    }

    private void getdatasanpham(final String id) {
            Toast.makeText(this, id, Toast.LENGTH_SHORT).show();
            DataService dataService= APIServices.getService();
            Call<List<GetdataSanphammoinhat>> callback=dataService.getdatasanphamdanhmuc(id);
            callback.enqueue(new Callback<List<GetdataSanphammoinhat>>() {
                @Override
                public void onResponse(Call<List<GetdataSanphammoinhat>> call, Response<List<GetdataSanphammoinhat>> response) {
                    Log.d("AAA","San Pham tim kiem: "+response.toString());
                    if(response.isSuccessful()){
                        ArrayList<GetdataSanphammoinhat>arrayList= (ArrayList<GetdataSanphammoinhat>) response.body();
                        Collections.sort(arrayList, new Comparator<GetdataSanphammoinhat>() {
                            @Override
                            public int compare(GetdataSanphammoinhat o1, GetdataSanphammoinhat o2) {
                                if(o1.getLoai().equals(o2.getLoai())){
                                    return 1;
                                }else {
                                    return 1;
                                }

                            }
                        });
                      //  Adapter_SanPham adapter=new Adapter_SanPham(QuanLySanPham.this,R.layout.layoutsanpham,arrayList);
                        addrecyclerview(arrayList);
                       // adapter.notifyDataSetChanged();

                    }
                }

                @Override
                public void onFailure(Call<List<GetdataSanphammoinhat>> call, Throwable t) {

                }
            });
        }


    public void addrecyclerview(ArrayList<GetdataSanphammoinhat>arrayList){
        recyclerviewquanlysanphamadmin=findViewById(R.id.recyclerviewquanlysanphamadmin);
        recyclerviewquanlysanphamadmin.setHasFixedSize(true);
        recyclerviewquanlysanphamadmin.setLayoutManager(new GridLayoutManager(this,1));
        Adapter_SanPham adapter=new Adapter_SanPham(getApplicationContext(),R.layout.layoutsanphamadmin,arrayList);
        recyclerviewquanlysanphamadmin.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
