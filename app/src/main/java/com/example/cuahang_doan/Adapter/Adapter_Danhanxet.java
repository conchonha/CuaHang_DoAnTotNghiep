package com.example.cuahang_doan.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuahang_doan.R;
import com.example.cuahang_doan.model.Nhanxetcuaban;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter_Danhanxet extends RecyclerView.Adapter<Adapter_Danhanxet.Viewhodler>{
    private View view;
    private Context context;
    private int layout;
    private ArrayList<Nhanxetcuaban>arrayList;

    public Adapter_Danhanxet(Context context, int layout, ArrayList<Nhanxetcuaban> arrayList) {
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Viewhodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view=View.inflate(context,R.layout.layout_danhanxet,null);
        return new Viewhodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewhodler holder, int position) {
        Nhanxetcuaban nhanxetcuaban=arrayList.get(position);
        if(nhanxetcuaban.getMotsao()>0){
            holder.rattingdanhanxet.setRating(1);
        }
        if(nhanxetcuaban.getHaisao()>0){
            holder.rattingdanhanxet.setRating(2);
        }
        if(nhanxetcuaban.getBasao()>0){
            holder.rattingdanhanxet.setRating(3);
        }
        if(nhanxetcuaban.getBonsao()>0){
            holder.rattingdanhanxet.setRating(4);
        }
        if(nhanxetcuaban.getNamsao()>0){
            holder.rattingdanhanxet.setRating(5);
        }
        holder.txttensanpham_nhanxet.setText(nhanxetcuaban.getTenSanPham());
        holder.txtnhanxet.setText(nhanxetcuaban.getComMent());
        Picasso.with(context).load(nhanxetcuaban.getHinhSanPham()).into(holder.imgnhanxet);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Viewhodler extends RecyclerView.ViewHolder{
        private ImageView imgnhanxet;
        private TextView txttensanpham_nhanxet,txtnhanxet;
        private RatingBar rattingdanhanxet;
        public Viewhodler(@NonNull View itemView) {
            super(itemView);
            imgnhanxet=itemView.findViewById(R.id.imgnhanxet);
            txtnhanxet=itemView.findViewById(R.id.txtnhanxet);
            txttensanpham_nhanxet=itemView.findViewById(R.id.txttensanpham_nhanxet);
            rattingdanhanxet=itemView.findViewById(R.id.rattingdanhanxet);
        }
    }
}
