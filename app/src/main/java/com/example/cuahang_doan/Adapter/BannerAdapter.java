package com.example.cuahang_doan.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.cuahang_doan.Activity.Chitietsanpham;
import com.example.cuahang_doan.R;
import com.example.cuahang_doan.model.QuangCao;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BannerAdapter extends PagerAdapter {
    private View view;
    private Context context;
    private ImageView imageBackground,imageHinhsanpham;
    private TextView txtTitle,txtMota;
    private ArrayList<QuangCao>arrayList;

    public BannerAdapter(Context context, ArrayList<QuangCao> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        view=View.inflate(context, R.layout.layout_fragmentbanner,null);
        anhxa();
        Picasso.with(context).load(arrayList.get(position).getHinhAnh()).into(imageBackground);
        Picasso.with(context).load(arrayList.get(position).getHinhAnhSanPham()).into(imageHinhsanpham);
        txtTitle.setText(arrayList.get(position).getTenSanPham());
        txtMota.setText(arrayList.get(position).getNoiDung());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context.getApplicationContext(),Chitietsanpham.class);
                intent.putExtra("id",arrayList.get(position).getId());
                context.startActivity(intent);
            }
        });
        container.addView(view);
        return view;
    }

    private void anhxa() {
        imageBackground=view.findViewById(R.id.imageBackground);
        imageHinhsanpham=view.findViewById(R.id.imageHinhsanpham);
        txtTitle=view.findViewById(R.id.txtTitle);
        txtMota=view.findViewById(R.id.txtMota);

    }
}
