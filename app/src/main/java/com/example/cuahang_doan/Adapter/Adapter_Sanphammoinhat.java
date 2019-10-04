package com.example.cuahang_doan.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuahang_doan.R;
import com.example.cuahang_doan.model.GetdataSanphammoinhat;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Adapter_Sanphammoinhat extends RecyclerView.Adapter<Adapter_Sanphammoinhat.Viewholdler> {
    private Context context;
    private int layout;
    private ArrayList<GetdataSanphammoinhat>arrayList;
    private View view;

    public Adapter_Sanphammoinhat(Context context, int layout, ArrayList<GetdataSanphammoinhat> arrayList) {
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Viewholdler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view=View.inflate(context,R.layout.layout_sanphammoinhat,null);
        return new Viewholdler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholdler holder, int position) {
        GetdataSanphammoinhat sanpham=arrayList.get(position);
        if(sanpham.getGiamGia()>0){
            holder.txtsale.setText("-"+sanpham.getGiamGia()+"%");
        }
        DecimalFormat simpleDateFormat=new DecimalFormat("###,###,###");
        holder.txtgiasp.setText(simpleDateFormat.format(sanpham.getGia())+"Đ");
        holder.txttensanpham.setText(sanpham.getTenSanPham());
        Picasso.with(context).load(sanpham.getHinhAnhSanPham()).into(holder.roundedImageView);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Viewholdler extends  RecyclerView.ViewHolder{
        private RoundedImageView roundedImageView;
        private TextView txtsale,txtgiasp,txttensanpham;
        private RatingBar ratingBar;
        public Viewholdler(@NonNull View itemView) {
            super(itemView);
            roundedImageView=itemView.findViewById(R.id.roundimageview);
            txttensanpham=itemView.findViewById(R.id.txttensanpham);
            txtgiasp=itemView.findViewById(R.id.txtgiasp);
            txtsale=itemView.findViewById(R.id.txtsale);
            ratingBar=itemView.findViewById(R.id.ratingBar);

        }
    }
}
