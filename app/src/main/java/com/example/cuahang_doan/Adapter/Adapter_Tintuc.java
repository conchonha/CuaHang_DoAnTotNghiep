package com.example.cuahang_doan.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuahang_doan.Activity.ThongTinChiTietTinTuc;
import com.example.cuahang_doan.R;
import com.example.cuahang_doan.model.TinTuc;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Adapter_Tintuc extends RecyclerView.Adapter<Adapter_Tintuc.Viewhdler> {
    private Context context;
    private int layout;
    private ArrayList<TinTuc>arrayList;
    private View view;

    public Adapter_Tintuc(Context context, int layout, ArrayList<TinTuc> arrayList) {
        this.context = context;
        this.layout = layout;
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
        TinTuc tinTuc=arrayList.get(position);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyy,MM,dd");
        Picasso.with(context).load(tinTuc.getHinhbaiviet()).into(holder.imageBackground);
        holder.txtnoidungbantin.setText(tinTuc.getNoidung());
        holder.txttitlebantin.setText(tinTuc.getTenbaiviet());
        holder.txtngaytintuc.setText("Ngày Đăng: "+tinTuc.getThoigian());
        Animation animationUtils=AnimationUtils.loadAnimation(context,R.anim.xoay);
        holder.txtsothutu.setAnimation(animationUtils);
        holder.txtsothutu.setText(position+1+"");
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Viewhdler extends RecyclerView.ViewHolder{
        private ImageView imageBackground;
        private TextView txttitlebantin,txtnoidungbantin,txtngaytintuc,txtsothutu;
        public Viewhdler(@NonNull View itemView) {
            super(itemView);
            imageBackground=itemView.findViewById(R.id.imageBackground);
            txttitlebantin=itemView.findViewById(R.id.txttitlebantin);
            txtnoidungbantin=itemView.findViewById(R.id.txtnoidungbantin);
            txtngaytintuc=itemView.findViewById(R.id.txtngaytintuc);
            txtsothutu=itemView.findViewById(R.id.txtsothutu);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, ThongTinChiTietTinTuc.class);
                    intent.putExtra("tintuc",arrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
