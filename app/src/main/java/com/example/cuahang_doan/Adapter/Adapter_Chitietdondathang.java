package com.example.cuahang_doan.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuahang_doan.R;
import com.example.cuahang_doan.model.Chitietdondathang;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Adapter_Chitietdondathang extends RecyclerView.Adapter <Adapter_Chitietdondathang.Viewhodler>{
    private View view;
    private Context context;
    private int layout;
    private ArrayList<Chitietdondathang>arrayList;

    public Adapter_Chitietdondathang(Context context, int layout, ArrayList<Chitietdondathang> arrayList) {
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Viewhodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view=View.inflate(context,layout,null);
        return new Viewhodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewhodler holder, int position) {
        Chitietdondathang chitietdondathang=arrayList.get(position);
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        holder.txtgiachitietdondathang.setText(decimalFormat.format(chitietdondathang.getGiaSanPham())+"Đ");
        holder.txtsoluongchitietdondathang.setText(chitietdondathang.getSoLuongSanPham()+"");
        holder.txttensanphamchitietdondathang.setText(chitietdondathang.getTenSanPham());
        Picasso.with(context).load(chitietdondathang.getHinhSanPham()).into(holder.roundimageviewchitietdondathang);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Viewhodler extends RecyclerView.ViewHolder{
        private TextView txttensanphamchitietdondathang,txtgiachitietdondathang,
                txtsoluongchitietdondathang;
        private RoundedImageView roundimageviewchitietdondathang;
        public Viewhodler(@NonNull View itemView) {
            super(itemView);
            txttensanphamchitietdondathang=itemView.findViewById(R.id.txttensanphamchitietdondathang);
            txtgiachitietdondathang=itemView.findViewById(R.id.txtgiachitietdondathang);
            txtsoluongchitietdondathang=itemView.findViewById(R.id.txtsoluongchitietdondathang);
            roundimageviewchitietdondathang=itemView.findViewById(R.id.roundimageviewchitietdondathang);
        }
    }
}
