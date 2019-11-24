package com.example.cuahang_doan.Fragment.TrangChu;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuahang_doan.Adapter.Adapter_Sanphammoinhat;
import com.example.cuahang_doan.R;
import com.example.cuahang_doan.Services.APIServices;
import com.example.cuahang_doan.Services.DataService;
import com.example.cuahang_doan.model.GetdataSanphammoinhat;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Sanphammoinhat extends Fragment {
    private RecyclerView recyclerviewSanphammoinhat;
    private View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_sanphammoinhat,container,false);
        anhxa();
        getdatasanphammoinhat();
        return view;
    }

    private void getdatasanphammoinhat() {
        DataService dataService= APIServices.getService() ;
        Call<List<GetdataSanphammoinhat>>callback=dataService.getDataSanphammoinhat();
        callback.enqueue(new Callback<List<GetdataSanphammoinhat>>() {
            @Override
            public void onResponse(Call<List<GetdataSanphammoinhat>> call, Response<List<GetdataSanphammoinhat>> response) {
                Log.d("AAA","getdatasanphammoinhat"+response.toString());
                if(response.isSuccessful()){
                    ArrayList<GetdataSanphammoinhat>arrayList= (ArrayList<GetdataSanphammoinhat>) response.body();
                    if(!arrayList.get(0).getHinhAnhSanPham().equals("")) {
                        Adapter_Sanphammoinhat adapter_sanphammoinhat = new Adapter_Sanphammoinhat(getActivity(),
                                R.layout.layout_sanphammoinhat, arrayList);
                        adapter_sanphammoinhat.notifyDataSetChanged();
                        recyclerviewSanphammoinhat.setAdapter(adapter_sanphammoinhat);
                    }

                }
            }

            @Override
            public void onFailure(Call<List<GetdataSanphammoinhat>> call, Throwable t) {

            }
        });
    }

    private void anhxa() {
        recyclerviewSanphammoinhat=view.findViewById(R.id.recyclerviewSanphammoinhat);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerviewSanphammoinhat.setLayoutManager(linearLayoutManager);
        recyclerviewSanphammoinhat.setHasFixedSize(true);
        recyclerviewSanphammoinhat.setItemViewCacheSize(20);
    }
}
