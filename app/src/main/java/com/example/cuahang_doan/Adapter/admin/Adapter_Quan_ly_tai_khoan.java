package com.example.cuahang_doan.Adapter.admin;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuahang_doan.Activity.admin.QuanLytaikhoan;
import com.example.cuahang_doan.R;
import com.example.cuahang_doan.Services.APIServices;
import com.example.cuahang_doan.Services.DataService;
import com.example.cuahang_doan.model.User;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Adapter_Quan_ly_tai_khoan extends RecyclerView.Adapter <Adapter_Quan_ly_tai_khoan.Viewhodler> {
    private QuanLytaikhoan context;
    private int layout;
    private ArrayList<User>arrayList;
    private View view;

    public Adapter_Quan_ly_tai_khoan(QuanLytaikhoan context, int layout, ArrayList<User> arrayList) {
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
        User taikhoan=arrayList.get(position);
        //holder.textViewMat_khau.setText();
        holder.textViewDia_chi.setText(taikhoan.getAdress());
        holder.textViewEmail.setText(taikhoan.getEmail());
        holder.textViewSDT.setText(taikhoan.getPhoneNumBer());
        holder.textViewTen_tai_khoan.setText(taikhoan.getUserName());
        if(taikhoan.getLoai()==0){
            holder.txtloaitaikhoan.setText("Khách Hàng");
        }else if(taikhoan.getLoai()==1){
            holder.txtloaitaikhoan.setText("Nhân Viên");
        }else{
            holder.txtloaitaikhoan.setText("Admin");
        }
        Picasso.with(context).load(taikhoan.getHinh()).into(holder.roundImageViewQuan_ly_tai_khoan);
        holder.imageViewDeleteTai_khoan_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataService dataService= APIServices.getService();
                Call<String>callback=dataService.deleteAdminTai_khoan(arrayList.get(position).getId()+"");
                callback.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Log.d("AAA","deleteAdminTai_khoan: "+response.toString());
                        if(response.isSuccessful()){
                            Toast.makeText(context, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                            context.reload();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Viewhodler extends RecyclerView.ViewHolder{
        private TextView textViewMat_khau,textViewSDT,textViewEmail,textViewDia_chi,textViewTen_tai_khoan,
                txtloaitaikhoan;
        private RoundedImageView roundImageViewQuan_ly_tai_khoan;
        private ImageView imageViewDeleteTai_khoan_admin;
        public Viewhodler(@NonNull View itemView) {
            super(itemView);
            txtloaitaikhoan=itemView.findViewById(R.id.txtloaitaikhoan);
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
