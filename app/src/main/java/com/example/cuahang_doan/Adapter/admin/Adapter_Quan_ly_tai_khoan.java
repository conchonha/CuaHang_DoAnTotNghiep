package com.example.cuahang_doan.Adapter.admin;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuahang_doan.R;
import com.example.cuahang_doan.model.User;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter_Quan_ly_tai_khoan extends RecyclerView.Adapter <Adapter_Quan_ly_tai_khoan.Viewhodler> {
    private Context context;
    private int layout;
    private ArrayList<User>arrayList;
    private View view;

    public Adapter_Quan_ly_tai_khoan(Context context, int layout, ArrayList<User> arrayList) {
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
        User taikhoan=arrayList.get(position);
        //holder.textViewMat_khau.setText();
        holder.textViewDia_chi.setText(taikhoan.getAdress());
        holder.textViewEmail.setText(taikhoan.getEmail());
        holder.textViewSDT.setText(taikhoan.getPhoneNumBer());
        holder.textViewTen_tai_khoan.setText(taikhoan.getUserName());

        Picasso.with(context).load(taikhoan.getHinh()).into(holder.roundImageViewQuan_ly_tai_khoan);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Viewhodler extends RecyclerView.ViewHolder{
        private TextView textViewMat_khau,textViewSDT,textViewEmail,textViewDia_chi,textViewTen_tai_khoan;
        private RoundedImageView roundImageViewQuan_ly_tai_khoan;
        private ImageView imageViewDeleteTai_khoan_admin;
        public Viewhodler(@NonNull View itemView) {
            super(itemView);
            textViewMat_khau=itemView.findViewById(R.id.textViewMat_khau);
            textViewSDT=itemView.findViewById(R.id.textViewSDT);
            textViewEmail=itemView.findViewById(R.id.textViewEmail);
            textViewDia_chi=itemView.findViewById(R.id.textViewDia_chi);
            textViewTen_tai_khoan=itemView.findViewById(R.id.textViewTen_tai_khoan);
            imageViewDeleteTai_khoan_admin=itemView.findViewById(R.id.imageViewDeleteTai_khoan_admin);
            roundImageViewQuan_ly_tai_khoan=itemView.findViewById(R.id.roundImageViewQuan_ly_tai_khoan);
        }
    }
}
