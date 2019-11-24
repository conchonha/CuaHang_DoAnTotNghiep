package com.example.cuahang_doan.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;

import com.example.cuahang_doan.Activity.admin.Inssert_TinTuc;
import com.example.cuahang_doan.Adapter.Adapter_Tintuc;
import com.example.cuahang_doan.R;
import com.example.cuahang_doan.Services.APIServices;
import com.example.cuahang_doan.Services.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TinTuc extends AppCompatActivity {
    private Toolbar toolbartintuc;
    private RecyclerView recyclerviewtintuc,recyclerviewtintucphu;
    private ArrayList<com.example.cuahang_doan.model.TinTuc>arrayList;
    private  Adapter_Tintuc adapter;
    private EditText edttimkimtintuc;
    private ImageView imgsearch,imgback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tin_tuc);
        anhxa();
        getdatatintuc();
        setOnclick();
    }
    public void reload(){
        finish();
        startActivity(getIntent());
        overridePendingTransition(0,0);
    }

    private void setOnclick() {
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        edttimkimtintuc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String timkim=edttimkimtintuc.getText().toString();
                DataService dataService=APIServices.getService();
                Call<List<com.example.cuahang_doan.model.TinTuc>>callback=dataService.
                        getdatatimkimtintuc(timkim);
                callback.enqueue(new Callback<List<com.example.cuahang_doan.model.TinTuc>>() {
                    @Override
                    public void onResponse(Call<List<com.example.cuahang_doan.model.TinTuc>> call, Response<List<com.example.cuahang_doan.model.TinTuc>> response) {
                        Log.d("AAA","Getdata timkim tintuc: "+response.toString());
                        if(response.isSuccessful()){
                            ArrayList<com.example.cuahang_doan.model.TinTuc>arrayList= (ArrayList<com.example.cuahang_doan.model.TinTuc>) response.body();
                            setdataRecyclerviewphu(arrayList);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<com.example.cuahang_doan.model.TinTuc>> call, Throwable t) {

                    }
                });
            }
        });
        imgsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu=new PopupMenu(TinTuc.this,imgsearch);
                popupMenu.getMenuInflater().inflate(R.menu.menu_quanlysanpham,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getItemId()==R.id.them){
                            startActivity(new Intent(getApplicationContext(), Inssert_TinTuc.class));
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }

    private void setdataRecyclerview(ArrayList<com.example.cuahang_doan.model.TinTuc>arrayList) {
        recyclerviewtintuc.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));
        recyclerviewtintuc.setHasFixedSize(true);
        Adapter_Tintuc adapter=new Adapter_Tintuc(TinTuc.this,R.layout.layout_tintuc,arrayList);
        recyclerviewtintuc.setAdapter(adapter);
    }
    private void setdataRecyclerviewphu(ArrayList<com.example.cuahang_doan.model.TinTuc>arrayList) {
        recyclerviewtintucphu.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));
        recyclerviewtintucphu.setHasFixedSize(true);
        Adapter_Tintuc adapter=new Adapter_Tintuc(TinTuc.this,R.layout.layouttintucphu,arrayList);
        recyclerviewtintucphu.setAdapter(adapter);
    }


    private void anhxa() {
        imgback=findViewById(R.id.imgback);
        imgsearch=findViewById(R.id.imgsearch);
        edttimkimtintuc=findViewById(R.id.edttimkimtintuc);
        recyclerviewtintucphu=findViewById(R.id.recyclerviewtintucphu);
        arrayList=new ArrayList<>();
        recyclerviewtintuc=findViewById(R.id.recyclerviewtintuc);

    }

    private void getdatatintuc() {
        DataService dataService= APIServices.getService();
        Call<List<com.example.cuahang_doan.model.TinTuc>>callback=dataService.getdatatintuc();
        callback.enqueue(new Callback<List<com.example.cuahang_doan.model.TinTuc>>() {
            @Override
            public void onResponse(Call<List<com.example.cuahang_doan.model.TinTuc>> call, Response<List<com.example.cuahang_doan.model.TinTuc>> response) {
                Log.d("AAA","Get dtatatintuc: "+response.toString());
                if(response.isSuccessful()){
                    arrayList= (ArrayList<com.example.cuahang_doan.model.TinTuc>) response.body();
                    ArrayList<com.example.cuahang_doan.model.TinTuc>arrayListchinh=new ArrayList<>();
                    arrayListchinh.add(new com.example.cuahang_doan.model.TinTuc(
                                                  arrayList.get(0).getId(),
                                                  arrayList.get(0).getHinhbaiviet(),
                                                  arrayList.get(0).getTenbaiviet(),
                                                  arrayList.get(0).getNoidung(),
                                                  arrayList.get(0).getThoigian()));
                    setdataRecyclerview(arrayListchinh);
                    setdataRecyclerviewphu(arrayList);
                }
            }

            @Override
            public void onFailure(Call<List<com.example.cuahang_doan.model.TinTuc>> call, Throwable t) {

            }
        });
    }


}
