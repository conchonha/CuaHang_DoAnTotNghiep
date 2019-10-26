package com.example.cuahang_doan.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuahang_doan.Fragment.DialogFragment_Chitietdonhang;
import com.example.cuahang_doan.R;
import com.example.cuahang_doan.model.DonDatHang;

import java.util.ArrayList;

public class Adapter_Donhangcuaban extends RecyclerView.Adapter<Adapter_Donhangcuaban.Viewhodler> {
    private View view;
    private ArrayList<DonDatHang>arrayList;
    private Context context;
    private int layout;

    public Adapter_Donhangcuaban(ArrayList<DonDatHang> arrayList, Context context, int layout) {
        this.arrayList = arrayList;
        this.context = context;
        this.layout = layout;
    }

    @NonNull
    @Override
    public Viewhodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view=View.inflate(context,layout,null);
        return new Viewhodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewhodler holder, int position) {
        DonDatHang donDatHang=arrayList.get(position);
        holder.trinhtrang.setText(donDatHang.getTrinhTrang());
        holder.ngaydat.setText(donDatHang.getNgayDat());
        holder.iddonhang.setText(donDatHang.getIdDonHang()+"");
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Viewhodler extends RecyclerView.ViewHolder{
        private TextView iddonhang,ngaydat,trinhtrang;
        public Viewhodler(@NonNull View itemView) {
            super(itemView);
            iddonhang=itemView.findViewById(R.id.iddonhang);
            ngaydat=itemView.findViewById(R.id.ngaydat);
            trinhtrang=itemView.findViewById(R.id.trinhtrang);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentManager fragmentManager= ((AppCompatActivity)context).getSupportFragmentManager();
                    DialogFragment_Chitietdonhang dialogFragment_chitietdonhang=new DialogFragment_Chitietdonhang(
                            arrayList.get(getPosition()).getIdDonHang(),arrayList.get(getPosition()).getTrinhTrang());
                    dialogFragment_chitietdonhang.show(fragmentManager,"chitietdonhang");
                }
            });
        }
    }
}
