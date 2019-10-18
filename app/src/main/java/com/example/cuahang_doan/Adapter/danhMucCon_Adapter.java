package com.example.cuahang_doan.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuahang_doan.Activity.SanPham;
import com.example.cuahang_doan.R;
import com.example.cuahang_doan.model.DanhMucCon;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class danhMucCon_Adapter extends RecyclerView.Adapter <danhMucCon_Adapter.Viewhdler>{
    private Context context;
    private int layout;
    private String id;
    private ArrayList<DanhMucCon>arrayList;
    private View view;

    public danhMucCon_Adapter(Context context, int layout, String id, ArrayList<DanhMucCon> arrayList) {
        this.context = context;
        this.layout = layout;
        this.id = id;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Viewhdler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view=View.inflate(context,layout,null);
        return new Viewhdler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewhdler holder, int position) {
        Picasso.with(context).load(arrayList.get(position).getHinh()).into(holder.imghinhicon);
        holder.txttendanhmuccon.setText(arrayList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class Viewhdler extends RecyclerView.ViewHolder {
        private TextView txttendanhmuccon;
        private ImageView imghinhicon;

        public Viewhdler(@NonNull View itemView) {
            super(itemView);
            txttendanhmuccon = itemView.findViewById(R.id.txttendanhmuccon);
            imghinhicon = itemView.findViewById(R.id.imghinhicon);
            if (id.equals("2")) {
                final String[] array = {"manhinh", "banphim", "pin", "sac"};
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, SanPham.class);
                        intent.putExtra("id", array[getPosition()]);
                        context.startActivity(intent);
                    }
                });
            }
            if (id.equals("4")) {
                final String[] array = {"loa", "microphone", "tainghe", "webcam", "cardamthanh"};
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, SanPham.class);
                        intent.putExtra("id", array[getPosition()]);
                        context.startActivity(intent);
                    }
                });
            }
            }
        }
    }

