package com.example.cuahang_doan.Fragment.Tai_Khoan.NhanXet;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuahang_doan.Activity.MainActivity;
import com.example.cuahang_doan.Adapter.Adapter_Danhanxet;
import com.example.cuahang_doan.Adapter.Adapter_nhanxet;
import com.example.cuahang_doan.R;
import com.example.cuahang_doan.Services.APIServices;
import com.example.cuahang_doan.Services.DataService;
import com.example.cuahang_doan.model.Nhanxetcuaban;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Danhanxet extends Fragment {
    private View view;
    private RecyclerView recyclerviewdanhanxet;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_danhanxet,container,false);
        anhxa();
        getdatadanhanxet();
        return view;
    }

    private void getdatadanhanxet() {
        if(!MainActivity.sharedPreferences.getString("username","").equals("")){
            DataService dataService= APIServices.getService();
            Call<List<Nhanxetcuaban>> callback=dataService.getdatachuanhanxet(
                    MainActivity.sharedPreferences.getInt("iduser",0)+"","danhanxet");
            callback.enqueue(new Callback<List<Nhanxetcuaban>>() {
                @Override
                public void onResponse(Call<List<Nhanxetcuaban>> call, Response<List<Nhanxetcuaban>> response) {
                    Log.d("AAA","get Data chua nhan xet: "+response.toString());
                    if(response.isSuccessful()){
                        ArrayList<Nhanxetcuaban> arrayList= (ArrayList<Nhanxetcuaban>) response.body();
                        Adapter_Danhanxet adapter=new Adapter_Danhanxet(getContext(),R.layout.layout_danhanxet,arrayList);
                        recyclerviewdanhanxet.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<List<Nhanxetcuaban>> call, Throwable t) {

                }
            });
        }else{
            Toast.makeText(getContext(), "Đăng nhập tài khoản để tiếp tục", Toast.LENGTH_SHORT).show();
        }
    }

    private void anhxa() {
        recyclerviewdanhanxet=view.findViewById(R.id.recyclerviewdanhanxet);
        recyclerviewdanhanxet.setHasFixedSize(true);
        recyclerviewdanhanxet.setLayoutManager(new GridLayoutManager(getContext(),1));

    }
}
