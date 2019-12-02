package com.example.cuahang_doan.Fragment.TrangChu;

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

public class Fragment_Thietbiluutruphukien extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private TextView txtxemthem1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_thietbiluutruphukien,container,false);
        anhxa();
        getDataThietbiluutruphukien();
        return view;
    }
//    private void setonclickxemthem() {
//        txtxemthem1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(getActivity(), SanPham.class);
//                intent.putExtra("id","tbluutru");
//                startActivity(intent);
//            }
//        });
//    }

    private void getDataThietbiluutruphukien() {
        DataService dataService= APIServices.getService();
        Call<List<GetdataSanphammoinhat>>callback=dataService.getdataThietbiluutruphukien();
        callback.enqueue(new Callback<List<GetdataSanphammoinhat>>() {
            @Override
            public void onResponse(Call<List<GetdataSanphammoinhat>> call, Response<List<GetdataSanphammoinhat>> response) {
                Log.d("AAA","thietbiluutruphukien"+response.toString());
                if(response.isSuccessful()){
                    final ArrayList<GetdataSanphammoinhat>arrayList= (ArrayList<GetdataSanphammoinhat>) response.body();
                    Adapter_Sanphammoinhat adapter=new Adapter_Sanphammoinhat(getActivity(),R.layout.layout_linhkienlaptop,arrayList);
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);

                    txtxemthem1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(getActivity(), SanPham.class);
                        intent.putExtra("id",arrayList.get(0).getIdDanhmuc()+"");
                        startActivity(intent);
                    }
                });
                }
            }

            @Override
            public void onFailure(Call<List<GetdataSanphammoinhat>> call, Throwable t) {

            }
        });
    }

    private void anhxa() {
        txtxemthem1=view.findViewById(R.id.txtxemthem1);
        recyclerView=view.findViewById(R.id.recyclerviewthietbiluutruphukien);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
    }
}
