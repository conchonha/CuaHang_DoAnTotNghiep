package com.example.cuahang_doan.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cuahang_doan.Activity.Chitietsanpham;
import com.example.cuahang_doan.R;
import com.example.cuahang_doan.Services.APIServices;
import com.example.cuahang_doan.Services.DataService;
import com.example.cuahang_doan.model.Danhgia;
import com.example.cuahang_doan.model.SanPham;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Danhgiasanpham extends Fragment {
    private View view;
    private Chitietsanpham chitietsanpham;
    private ArrayList<Danhgia>arrayDanhgia=new ArrayList<>();
    private TextView txt5sao,txt4sao,txt3sao,txt2sao,txt1sao;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
     view=inflater.inflate(R.layout.fragment_danhgiasanpham,container,false);
          danhgiasanpham();
          Bundle bundle=getArguments();
          if(bundle!=null) {
              String msp = bundle.getString("masp");
              Log.d("AAA", "Mã san pham fragment danhgia"+msp);
              if(!msp.equals("0")){
                  getdatadanhgia(msp);
              }
          }
     return view;
    }
    private void danhgiasanpham(){
        txt5sao=view.findViewById(R.id.txt5sao);
        txt4sao=view.findViewById(R.id.txt4sao);
        txt3sao=view.findViewById(R.id.txt3sao);
        txt2sao=view.findViewById(R.id.txt2sao);
        txt1sao=view.findViewById(R.id.txt1sao);
    }

    private void getdatadanhgia(String msp) {
        if(!msp.equals("")){
            DataService dataService= APIServices.getService();
            Call<List<Danhgia>>callback=dataService.getdataDanhgia(msp+"");
            callback.enqueue(new Callback<List<Danhgia>>() {
                @Override
                public void onResponse(Call<List<Danhgia>> call, Response<List<Danhgia>> response) {
                    Log.d("AAA","getdataDanhgia"+response.toString());
                    if(response!=null){
                        int namsao=0;int bonsao=0;int basao=0;int haisao=0;int motsao=0;
                        arrayDanhgia= (ArrayList<Danhgia>) response.body();
                       if(arrayDanhgia!=null){
                           for (int i=0; i < arrayDanhgia.size(); i++){
                               namsao+=arrayDanhgia.get(i).get5sao();
                               bonsao+=arrayDanhgia.get(i).get4sao();
                               basao+=arrayDanhgia.get(i).get3sao();
                               haisao+=arrayDanhgia.get(i).get2sao();
                               motsao+=arrayDanhgia.get(i).get1sao();
                           }
                           txt5sao.setText(namsao+" Đánh Giá");
                           txt4sao.setText(bonsao+" Đánh Giá");
                           txt3sao.setText(basao+" Đánh Giá");
                           txt2sao.setText(haisao+" Đánh Giá");
                           txt1sao.setText(motsao+" Đánh Giá");
                       }
                    }
                }

                @Override
                public void onFailure(Call<List<Danhgia>> call, Throwable t) {
                    Log.d("AAA","Ero getdata danhgia san pham"+t.toString());
                }
            });
        }
    }
}
