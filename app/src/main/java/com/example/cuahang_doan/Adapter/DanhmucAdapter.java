package com.example.cuahang_doan.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cuahang_doan.R;
import com.example.cuahang_doan.model.DanhMuc;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhmucAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<DanhMuc>arrayList;

    public DanhmucAdapter(Context context, int layout, ArrayList<DanhMuc> arrayList) {
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
    private class Viewhodeler{
        private ImageView imageviewDanhmucLayout;
        private TextView txtTitleDanhmuc;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Viewhodeler viewhodeler=null;
        if(view==null) {
            viewhodeler=new Viewhodeler();
            view = View.inflate(context, layout, null);
            viewhodeler.imageviewDanhmucLayout=view.findViewById(R.id.imageviewDanhmucLayout);
            viewhodeler.txtTitleDanhmuc=view.findViewById(R.id.txtTitleDanhmuc);
            view.setTag(viewhodeler);
        }else{
            viewhodeler= (Viewhodeler) view.getTag();
        }
        Picasso.with(context).load(arrayList.get(i).getHinhicon()).into(viewhodeler.imageviewDanhmucLayout);
        viewhodeler.txtTitleDanhmuc.setText(arrayList.get(i).getTen());
        return view;
    }
}
