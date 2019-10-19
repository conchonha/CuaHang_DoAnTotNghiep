package com.example.cuahang_doan.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuahang_doan.Activity.SanPham;
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

public class Fragment_Linhkienlaptop extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private TextView txtxemthem;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view=inflater.inflate(R.layout.fragment_linhkienlaptop,container,false);
       anhxa();
       getdataLinhkienlaptop();
        setonclickxemthem();
       return view;
    }

    private void setonclickxemthem() {
        txtxemthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), SanPham.class);
                intent.putExtra("id","linhkienlaptop");
                startActivity(intent);
            }
        });
    }

    private void getdataLinhkienlaptop() {
        DataService dataService= APIServices.getService();
        Call<List<GetdataSanphammoinhat>>callback=dataService.getDataLinhkienlaptop();
        callback.enqueue(new Callback<List<GetdataSanphammoinhat>>() {
            @Override
            public void onResponse(Call<List<GetdataSanphammoinhat>> call, Response<List<GetdataSanphammoinhat>> response) {
                Log.d("AAA","linhkienlaptop"+response.toString());
                if(response.isSuccessful()){
                    ArrayList<GetdataSanphammoinhat>arrayList= (ArrayList<GetdataSanphammoinhat>) response.body();
                    Adapter_Sanphammoinhat adapter=new Adapter_Sanphammoinhat(getActivity(),R.layout.layout_linhkienlaptop,arrayList);
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<GetdataSanphammoinhat>> call, Throwable t) {

            }
        });
    }

    private void anhxa() {
        txtxemthem=view.findViewById(R.id.txtxemthem);
        recyclerView=view.findViewById(R.id.recyclerviewlinhkienlaptop);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
    }


}
