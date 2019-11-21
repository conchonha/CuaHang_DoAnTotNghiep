package com.example.cuahang_doan.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuahang_doan.Activity.Chitietsanpham;
import com.example.cuahang_doan.R;
import com.example.cuahang_doan.model.GetdataSanphammoinhat;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Adapter_SanPham extends RecyclerView.Adapter<Adapter_SanPham.Viewhodler> {
    private Context context;
    private int layout;
    private ArrayList<GetdataSanphammoinhat> arrayList;
    private View view;

    public Adapter_SanPham(Context context, int layout, ArrayList<GetdataSanphammoinhat> arrayList) {
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
    public void onBindViewHolder(@NonNull Viewhodler holder, final int position) {
        GetdataSanphammoinhat sanpham=arrayList.get(position);
        Calendar calendar=Calendar.getInstance();
        DecimalFormat simpleDateFormat=new DecimalFormat("###,###,###");
        SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy");
        if(sanpham.getGiamGia()>0 && !sanpham.getNgayKhuyenMai().equals("")){
            Date ngaykhuyenmai= null;
            Date ngayhientai=null;
            try {
                ngaykhuyenmai = format.parse(sanpham.getNgayKhuyenMai()+"");
                ngayhientai=format.parse(calendar.get(Calendar.DATE)+"-"+calendar.get(Calendar.MONTH)+"-"+calendar.get(Calendar.YEAR));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if(ngaykhuyenmai.compareTo(ngayhientai)>0){
                Log.d("AAA","ngay"+ngayhientai);
                holder.txtsale.setText("Giảm Giá: "+sanpham.getGiamGia()+"%");
                holder.txtgiasp.setPaintFlags(holder.txtgiasp.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG );
                holder.txtgiasp.setTextColor(context.getResources().getColor(R.color.maugiasanpham));
                holder.txtgiasp.setText(simpleDateFormat.format(sanpham.getGia())+"");
                float giagiam=(float) (100-sanpham.getGiamGia())/100;
                float giaspsaukhuyenmai=(float)giagiam*sanpham.getGia();
                Log.d("AAA",giaspsaukhuyenmai+"");
                holder.txtgiaspsaukhuyenmai.setText(simpleDateFormat.format((int)giaspsaukhuyenmai)+"Đ");
                holder.txtgiaspsaukhuyenmai.setTextColor(Color.RED);
                holder.txtngaygiamgia.setText("Hạn: "+sanpham.getNgayKhuyenMai());
            }
        }else{
            holder.txtgiaspsaukhuyenmai.setText("");
            holder.txtgiasp.setText(simpleDateFormat.format(sanpham.getGia())+"Đ");
            holder.txtgiasp.setTextColor(Color.RED);
        }
        holder.txttensanpham.setText(sanpham.getTenSanPham());
        Picasso.with(context).load(sanpham.getHinhAnhSanPham()).into(holder.roundedImageView);
       // holder.txtloai.setText(sanpham.getLoai());
        holder.txttensanpham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, Chitietsanpham.class);
                intent.putExtra("id",arrayList.get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Viewhodler extends RecyclerView.ViewHolder{
        private RoundedImageView roundedImageView;
        private TextView txtsale,txtgiasp,txttensanpham,txtloai,txtthongso;
        private TextView txtgiaspsaukhuyenmai,txtngaygiamgia;
        private RatingBar ratingBar;
        public Viewhodler(@NonNull View itemView) {
            super(itemView);
            txtngaygiamgia=itemView.findViewById(R.id.txtngaygiamgia);
            roundedImageView=itemView.findViewById(R.id.roundimageview);
            txttensanpham=itemView.findViewById(R.id.txttensanpham);
            txtgiasp=itemView.findViewById(R.id.txtgiasp);
            txtsale=itemView.findViewById(R.id.txtsale);
            ratingBar=itemView.findViewById(R.id.ratingBar);
            txtgiaspsaukhuyenmai=itemView.findViewById(R.id.txtgiaspsaukhuyenmai);
            txtloai=itemView.findViewById(R.id.txtloai);
            txtthongso=itemView.findViewById(R.id.txtthongso);


        }
    }
}
