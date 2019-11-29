package com.example.cuahang_doan.Fragment.Tai_Khoan.DonHang;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuahang_doan.Activity.Login;
import com.example.cuahang_doan.Activity.MainActivity;
import com.example.cuahang_doan.Adapter.Adapter_Donhangcuaban;
import com.example.cuahang_doan.R;
import com.example.cuahang_doan.Services.APIServices;
import com.example.cuahang_doan.Services.DataService;
import com.example.cuahang_doan.model.DonDatHang;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Dagiaohang extends Fragment {
    private View view;
    private RecyclerView recyclerviewdagiaohang;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_dagiaohang,container,false);
        anhxa();
        if(MainActivity.sharedPreferences.getString("admin","").equals("")){
            getdatadagiaohangnguoidung();

        }else{
            getdatadagiaohangadmin();
        }


        return view;
    }
    private void getdatadagiaohangnguoidung() {
        DataService dataService= APIServices.getService();
        Call<List<DonDatHang>> callback=dataService.getdatadagiaohang(MainActivity.sharedPreferences.getInt("iduser",0)+"");
        callback.enqueue(new Callback<List<DonDatHang>>() {
            @Override
            public void onResponse(Call<List<DonDatHang>> call, Response<List<DonDatHang>> response) {
                Log.d("AAA","getdata cda giaohang:"+response.toString());
                if(response.isSuccessful()){
                    ArrayList<DonDatHang> arrayList=(ArrayList)response.body();
                    Adapter_Donhangcuaban adapter=new Adapter_Donhangcuaban(arrayList,getActivity(),R.layout.layout_donhangcuaban);
                    recyclerviewdagiaohang.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<DonDatHang>> call, Throwable t) {

            }
        });
    }
    private void getdatadagiaohangadmin() {
        DataService dataService= APIServices.getService();
        Call<List<DonDatHang>> callback=dataService.getdatadagiaohangadmin();
        callback.enqueue(new Callback<List<DonDatHang>>() {
            @Override
            public void onResponse(Call<List<DonDatHang>> call, Response<List<DonDatHang>> response) {
                Log.d("AAA","getdata cda giaohang:"+response.toString());
                if(response.isSuccessful()){
                    ArrayList<DonDatHang> arrayList=(ArrayList)response.body();
                    Adapter_Donhangcuaban adapter=new Adapter_Donhangcuaban(arrayList,getActivity(),R.layout.layout_donhangcuaban);
                    recyclerviewdagiaohang.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<DonDatHang>> call, Throwable t) {

            }
        });
    }
    private void anhxa() {
        recyclerviewdagiaohang =view.findViewById(R.id.recyclerviewdagiaohang);
        recyclerviewdagiaohang.setHasFixedSize(true);
        recyclerviewdagiaohang.setLayoutManager(new GridLayoutManager(getActivity(),1));
    }
}
