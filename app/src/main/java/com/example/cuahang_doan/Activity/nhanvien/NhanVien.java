package com.example.cuahang_doan.Activity.nhanvien;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;

import com.example.cuahang_doan.Activity.Login;
import com.example.cuahang_doan.Activity.MainActivity;
import com.example.cuahang_doan.Adapter.Adapter_Donhangcuaban;
import com.example.cuahang_doan.Adapter.nhanvien.Adapter_chitietdonhang_nhanviengiaohang;
import com.example.cuahang_doan.R;
import com.example.cuahang_doan.Services.APIServices;
import com.example.cuahang_doan.Services.DataService;
import com.example.cuahang_doan.model.DonDatHang;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NhanVien extends AppCompatActivity {
    private RecyclerView recyclerviewdangvanchuyennhanvien;
    private ImageView imageviewmenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhan_vien);
        anhxa();
        getdatadangvanchuyennadmin();
        menu();
    }

    private void menu() {
        imageviewmenu=findViewById(R.id.imageviewmenu);
        imageviewmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu=new PopupMenu(NhanVien.this,imageviewmenu);
                popupMenu.getMenuInflater().inflate(R.menu.menudangxuat,popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getItemId()==R.id.dangxuat){
                            MainActivity.editor.remove("nhanvien");
                            startActivity(new Intent(getApplicationContext(),Login.class));
                            finish();
                        }
                        return false;
                    }
                });
            }
        });


    }

    private void getdatadangvanchuyennadmin() {
        DataService dataService= APIServices.getService();
        Call<List<DonDatHang>> callback=dataService.getdatadangvanchuyenadmin();
        callback.enqueue(new Callback<List<DonDatHang>>() {
            @Override
            public void onResponse(Call<List<DonDatHang>> call, Response<List<DonDatHang>> response) {
                Log.d("AAA","getdata Dangvanchuyen:"+response.toString());
                if(response.isSuccessful()){
                    ArrayList<DonDatHang> arrayList=(ArrayList)response.body();
                    Adapter_chitietdonhang_nhanviengiaohang adapter=new Adapter_chitietdonhang_nhanviengiaohang(arrayList,NhanVien.this,R.layout.layout_donhangcuaban);
                    recyclerviewdangvanchuyennhanvien.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<DonDatHang>> call, Throwable t) {

            }
        });
    }
    private void anhxa() {
        recyclerviewdangvanchuyennhanvien =findViewById(R.id.recyclerviewdangvanchuyennhanvien);
        recyclerviewdangvanchuyennhanvien.setHasFixedSize(true);
        recyclerviewdangvanchuyennhanvien.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));
    }
}
