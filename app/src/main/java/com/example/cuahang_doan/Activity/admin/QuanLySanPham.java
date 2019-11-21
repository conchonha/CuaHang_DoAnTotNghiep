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
    private ImageView imgsearchadmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_san_pham);
        anhxa();
        getdatasanpham("laptop");
        PopupMenuluachon();
       timkim();
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
                        if(item.getItemId()==R.id.laptop){
                            getdatasanpham("laptop");
                        }
                        if(item.getItemId()==R.id.manhinh){
                            getdatasanpham("manhinh");
                        }
                        if(item.getItemId()==R.id.banphim){
                            getdatasanpham("banphim");
                        }
                        if(item.getItemId()==R.id.pin){
                            getdatasanpham("pin");
                        }
                        if(item.getItemId()==R.id.sac){
                            getdatasanpham("sac");
                        }
                        if(item.getItemId()==R.id.loa){
                            getdatasanpham("loa");
                        }
                        if(item.getItemId()==R.id.microphone){
                            getdatasanpham("microphone");
                        }
                        if(item.getItemId()==R.id.tainghe){
                            getdatasanpham("tainghe");
                        }
                        if(item.getItemId()==R.id.webcame){
                            getdatasanpham("webcam");
                        }
                        if(item.getItemId()==R.id.amthanh){
                            getdatasanpham("cardamthanh");
                        }
                        if(item.getItemId()==R.id.linhkienlaptop){
                            getdatasanpham("linhkienlaptop");
                        }
                        if(item.getItemId()==R.id.thietbinghenhin){
                            getdatasanpham("thietbinghenhin1");
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }

    public void thaytendanhmucluachon(String giatri){
        if(giatri.equals("laptop")){
            txttendanhmucquanlyadmin.setText("LapTop - Macbook");
        }
        if(giatri.equals("manhinh")){
            txttendanhmucquanlyadmin.setText("Màn Hình - LapTop");
        }
        if(giatri.equals("banphim")){
            txttendanhmucquanlyadmin.setText("Bàn Phím - LapTop");
        }
        if(giatri.equals("pin")){
            txttendanhmucquanlyadmin.setText("Pin - LapTop");
        }
        if(giatri.equals("sac")){
            txttendanhmucquanlyadmin.setText("Sạc - LapTop");
        }
        if(giatri.equals("tbluutru")){
            txttendanhmucquanlyadmin.setText("TB Lưu Trữ - Phụ Kiện");
        }
        if(giatri.equals("loa")){
            txttendanhmucquanlyadmin.setText("Loa - LapTop");
        }
        if(giatri.equals("microphone")){
            txttendanhmucquanlyadmin.setText("Microphone");
        }
        if(giatri.equals("tainghe")){
            txttendanhmucquanlyadmin.setText("Tai Nghe");
        }
        if(giatri.equals("webcam")){
            txttendanhmucquanlyadmin.setText("Webcam");
        }
        if(giatri.equals("cardamthanh")){
            txttendanhmucquanlyadmin.setText("Card Âm Thanh");
        }
        if(giatri.equals("linhkienlaptop")){
            txttendanhmucquanlyadmin.setText("Linh Kiện - LapTop");
        }
        if(giatri.equals("thietbinghenhin1")){
            txttendanhmucquanlyadmin.setText("TB Nghe - Nhìn");
        }
    }



    private void anhxa() {
        edtseachsanphamadmin=findViewById(R.id.edtseachsanphamadmin);
        imgsearchadmin=findViewById(R.id.imgsearchadmin);
        toolbaradminquanlysp=findViewById(R.id.toolbaradminquanlysp);
        setSupportActionBar(toolbaradminquanlysp);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbaradminquanlysp.setNavigationIcon(R.drawable.back);
        toolbaradminquanlysp.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
                        thaytendanhmucluachon(id);
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
