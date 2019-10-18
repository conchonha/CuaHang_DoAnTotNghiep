package com.example.cuahang_doan.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cuahang_doan.Adapter.Adapter_HoaDon;
import com.example.cuahang_doan.R;
import com.example.cuahang_doan.Services.APIServices;
import com.example.cuahang_doan.Services.DataService;
import com.example.cuahang_doan.model.HoaDon;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Hoadon extends AppCompatActivity {
    private TextView txtmadonhang,txtkhachhang,txtsodienthoai,txtdiachi,txtngaydat,txtgiasanphamhoadon;
    private List<HoaDon> arrayList;
    private RecyclerView recyclerView;
    private ImageView imgback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoadon);
        getdatahoadon();
        anhxa();
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
    }

    private void getdatahoadon() {
        DataService dataService= APIServices.getService();
        Call<List<HoaDon>>callback=dataService.getdatahoadon(MainActivity.sharedPreferences.getInt("iduser",0)+"");
        callback.enqueue(new Callback<List<HoaDon>>() {
            @Override
            public void onResponse(Call<List<HoaDon>> call, Response<List<HoaDon>> response) {
                Log.d("AAA","Hoa Don : "+response.toString());
                if(response.isSuccessful()){
                    DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
                    arrayList=response.body();
                    txtmadonhang.setText(arrayList.get(0).getIdDonHang()+"");
                    txtkhachhang.setText(arrayList.get(0).getUsername());
                    txtdiachi.setText(arrayList.get(0).getDiaChi());
                    txtngaydat.setText(arrayList.get(0).getNgayDat());
                    txtsodienthoai.setText(arrayList.get(0).getSoDienThoai());
                    Adapter_HoaDon adapter=new Adapter_HoaDon(getApplicationContext(),R.layout.layout_hoadon,arrayList);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    int tong=0;
                    for (int i=0;i<arrayList.size();i++){
                        tong+=arrayList.get(i).getGiaSanPham();
                    }
                    txtgiasanphamhoadon.setText(decimalFormat.format(tong)+" Đồng");
                }
            }

            @Override
            public void onFailure(Call<List<HoaDon>> call, Throwable t) {

            }
        });
    }

    private void anhxa() {
        imgback=findViewById(R.id.imgback);
        txtgiasanphamhoadon=findViewById(R.id.txtgiasanphamhoadon);
        txtmadonhang=findViewById(R.id.txtmadonhang);
        txtkhachhang=findViewById(R.id.txtkhachhang);
        txtsodienthoai=findViewById(R.id.txtsodienthoai);
        txtdiachi=findViewById(R.id.txtdiachi);
        txtngaydat=findViewById(R.id.txtngaydat);
        recyclerView=findViewById(R.id.recyclerviewhoadon);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
    }
}
