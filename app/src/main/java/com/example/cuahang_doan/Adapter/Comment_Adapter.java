package com.example.cuahang_doan.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.cuahang_doan.R;
import com.example.cuahang_doan.model.Danhgia;

import java.util.ArrayList;

public class Comment_Adapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Danhgia>arrayList;
    private View view;
    private TextView txttenuser,txtcommentnguoidung,txtngaythangdanhgia;
    private RatingBar ratingBar;

    public Comment_Adapter(Context context, int layout, ArrayList<Danhgia> arrayList) {
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view=View.inflate(context,layout,null);
         anhxa();
         txttenuser.setText(arrayList.get(i).getIdUser()+"");
         txtcommentnguoidung.setText(arrayList.get(i).getComMent());
         txtngaythangdanhgia.setText(arrayList.get(i).getNgayDanhGia());
         //ratingBar.setRating(arrayList.get(i).);
        return view;
    }

    private void anhxa() {
        txttenuser=view.findViewById(R.id.txttenuser);
        txtcommentnguoidung=view.findViewById(R.id.txtcommentnguoidung);
        txtngaythangdanhgia=view.findViewById(R.id.txtngaythangdanhgia);
        ratingBar=view.findViewById(R.id.ratingdanhgianguoidung);

    }
}
