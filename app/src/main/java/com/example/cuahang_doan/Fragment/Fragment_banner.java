package com.example.cuahang_doan.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.cuahang_doan.Adapter.BannerAdapter;
import com.example.cuahang_doan.R;
import com.example.cuahang_doan.Services.APIServices;
import com.example.cuahang_doan.Services.DataService;
import com.example.cuahang_doan.model.QuangCao;
import com.rd.PageIndicatorView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_banner extends Fragment {
    private View view;
    private ViewPager FragmentBanerViewpager;
    private PageIndicatorView pageIndicatorView;
    private BannerAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view=inflater.inflate(R.layout.fragment_banner,container,false);
       anhxa();
       GetdataBanner();
        return view;
    }

    private void GetdataBanner() {
        DataService dataService= APIServices.getService();
        Call<List<QuangCao>>callback=dataService.GetdataQuangCao();
        callback.enqueue(new Callback<List<QuangCao>>() {
            @Override
            public void onResponse(Call<List<QuangCao>> call, Response<List<QuangCao>> response) {
                Log.d("AAA","Banner"+response.toString());
                if(response!=null){
                    ArrayList arrayList= (ArrayList) response.body();
                    adapter=new BannerAdapter(view.getContext(),arrayList);
                    FragmentBanerViewpager.setAdapter(adapter);
                    pageIndicatorView.setViewPager(FragmentBanerViewpager);
                }
            }

            @Override
            public void onFailure(Call<List<QuangCao>> call, Throwable t) {
                Toast.makeText(getActivity(), "Tạm thời không lấy được dữ liệu vui lòng quay lại sau", Toast.LENGTH_SHORT).show();
                Log.d("AAA","Banner"+t.toString());
            }
        });
    }

    private void anhxa() {
        FragmentBanerViewpager=view.findViewById(R.id.FragmentBanerViewpager);
        pageIndicatorView=view.findViewById(R.id.PageIndicatorview);
    }
}
