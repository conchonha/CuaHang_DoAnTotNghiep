package com.example.cuahang_doan.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuahang_doan.Activity.Hoadon;
import com.example.cuahang_doan.R;
import com.example.cuahang_doan.model.HoaDon;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Adapter_HoaDon extends RecyclerView.Adapter<Adapter_HoaDon.Viewhodler> {
    private Context context;
    private int layout;
    private List<HoaDon> arrayList;
    private View view;
    private int stt=1;

    public Adapter_HoaDon(Context context, int layout, List<HoaDon> arrayList) {
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
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        HoaDon hoadon=arrayList.get(position);
        holder.stt.setText(stt+"");
        stt++;
        int dongia=hoadon.getGiaSanPham()/hoadon.getSoLuongSanPham();
        holder.txtdongia.setText(decimalFormat.format(dongia)+"");
        holder.txtsoluong.setText(hoadon.getSoLuongSanPham()+"");
        holder.txttensanpham1.setText(hoadon.getTenSanPham());
        holder.txtthanhtien.setText(decimalFormat.format(hoadon.getGiaSanPham())+"");
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Viewhodler extends RecyclerView.ViewHolder{
        private TextView stt,txttensanpham1,txtsoluong,txtdongia,txtthanhtien;
        public Viewhodler(@NonNull View itemView) {
            super(itemView);
            stt=itemView.findViewById(R.id.stt);
            txttensanpham1=itemView.findViewById(R.id.txttensanpham1);
            txtsoluong=itemView.findViewById(R.id.txtsoluong);
            txtdongia=itemView.findViewById(R.id.txtdongia);
            txtthanhtien=itemView.findViewById(R.id.txtthanhtien);
        }
    }
}
