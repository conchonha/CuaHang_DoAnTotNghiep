package com.example.cuahang_doan.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cuahang_doan.Adapter.Adapter_HoaDon;
import com.example.cuahang_doan.Fragment.Fragment_Dondathang;
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
    private Toolbar imgback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoadon);
        getdatahoadon();
        anhxa();
        addfragment();

    }

    private void addfragment() {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        Fragment_Dondathang fragment_dondathang=new Fragment_Dondathang(arrayList);
        fragmentTransaction.add(R.id.linnerlaouthoadon,fragment_dondathang,"fragmentdondathang");
        fragmentTransaction.commit();
    }

    private void getdatahoadon() {
        DataService dataService= APIServices.getService();
        Call<List<HoaDon>>callback=dataService.getdatahoadon(MainActivity.sharedPreferences.getInt("iduser",0)+"");
        callback.enqueue(new Callback<List<HoaDon>>() {
            @Override
            public void onResponse(Call<List<HoaDon>> call, Response<List<HoaDon>> response) {
                Log.d("AAA","Hoa Don : "+response.toString());
                if(response.isSuccessful()){
                    arrayList=response.body();
                    txtmadonhang.setText(arrayList.get(0).getIdDonHang()+"");
                    txtkhachhang.setText(arrayList.get(0).getUsername());
                    txtdiachi.setText(arrayList.get(0).getDiaChi());
                    txtngaydat.setText(arrayList.get(0).getNgayDat());
                    txtsodienthoai.setText(arrayList.get(0).getSoDienThoai());

                }
            }

            @Override
            public void onFailure(Call<List<HoaDon>> call, Throwable t) {

            }
        });
    }

    private void anhxa() {
        imgback=findViewById(R.id.imgback);
        setSupportActionBar(imgback);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        imgback.setNavigationIcon(R.drawable.back);
        imgback.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
        txtmadonhang=findViewById(R.id.txtmadonhang);
        txtkhachhang=findViewById(R.id.txtkhachhang);
        txtsodienthoai=findViewById(R.id.txtsodienthoai);
        txtdiachi=findViewById(R.id.txtdiachi);
        txtngaydat=findViewById(R.id.txtngaydat);

    }
}
