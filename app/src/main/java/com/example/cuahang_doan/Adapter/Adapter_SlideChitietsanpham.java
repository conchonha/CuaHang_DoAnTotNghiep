package com.example.cuahang_doan.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.cuahang_doan.R;
import com.example.cuahang_doan.Services.APIServices;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter_SlideChitietsanpham extends PagerAdapter {
    private String[]arrayhinh;
    private View view;
    private Context context;
    private ImageView imgslidechitietsanpham;

    public Adapter_SlideChitietsanpham(String[] arrayhinh, Context context) {
        this.arrayhinh = arrayhinh;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayhinh.length;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        view=View.inflate(context, R.layout.layout_slidechitietsanpham,null);
        imgslidechitietsanpham=view.findViewById(R.id.imgslidechitietsanpham);
        if(arrayhinh[position].endsWith("news.jpg")){
            Picasso.with(context).load(APIServices.urlhinhsanpham+arrayhinh[position]).into(imgslidechitietsanpham);
            Log.d("AAA","hinhsp: "+APIServices.urlhinhsanpham+arrayhinh[position]);
        }else {
            Picasso.with(context).load(arrayhinh[position]).into(imgslidechitietsanpham);
        }
        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
