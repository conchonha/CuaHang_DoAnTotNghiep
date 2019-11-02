package com.example.cuahang_doan.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuahang_doan.Activity.Chitietsanpham;
import com.example.cuahang_doan.R;
import com.example.cuahang_doan.model.Nhanxetcuaban;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter_nhanxet extends RecyclerView.Adapter<Adapter_nhanxet.Viewhodler> {
    private View view;
    private int layout;
    private Context context;
    private ArrayList<Nhanxetcuaban>arrayList;

    public Adapter_nhanxet(int layout, Context context, ArrayList<Nhanxetcuaban> arrayList) {
        this.layout = layout;
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Viewhodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view=View.inflate(context,layout,null);
        return new Viewhodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewhodler holder, final int position) {
        Picasso.with(context).load(arrayList.get(position).getHinhSanPham()).into(holder.imgchuanhanxet);
        holder.btnnhanxet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, Chitietsanpham.class);
                intent.putExtra("id",arrayList.get(position).getId());
                context.startActivity(intent);
            }
        });
        holder.txtngaygiaohang.setText("Đã giao hàng ngày: "+arrayList.get(position).getNgayDanhGia());
        holder.txttensanpham_nhanxet.setText(arrayList.get(position).getTenSanPham());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Viewhodler extends RecyclerView.ViewHolder{
        private ImageView imgchuanhanxet;
        private TextView txtngaygiaohang,txttensanpham_nhanxet,btnnhanxet;

        public Viewhodler(@NonNull View itemView) {
            super(itemView);
            imgchuanhanxet=itemView.findViewById(R.id.imgchuanhanxet);
            txtngaygiaohang=itemView.findViewById(R.id.txtngaygiaohang);
            txttensanpham_nhanxet=itemView.findViewById(R.id.txttensanpham_nhanxet);
            btnnhanxet=itemView.findViewById(R.id.btnnhanxet1);

        }
    }
}
