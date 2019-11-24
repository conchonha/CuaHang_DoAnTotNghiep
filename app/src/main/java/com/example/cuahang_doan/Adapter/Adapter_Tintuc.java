package com.example.cuahang_doan.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuahang_doan.Activity.MainActivity;
import com.example.cuahang_doan.Activity.ThongTinChiTietTinTuc;
import com.example.cuahang_doan.Activity.admin.UpdateTinTuc;
import com.example.cuahang_doan.R;
import com.example.cuahang_doan.Services.APIServices;
import com.example.cuahang_doan.Services.DataService;
import com.example.cuahang_doan.model.TinTuc;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Adapter_Tintuc extends RecyclerView.Adapter<Adapter_Tintuc.Viewhdler> {
    private com.example.cuahang_doan.Activity.TinTuc context;
    private int layout;
    private ArrayList<TinTuc>arrayList;
    private View view;

    public Adapter_Tintuc(com.example.cuahang_doan.Activity.TinTuc context, int layout, ArrayList<TinTuc> arrayList) {
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
    public void onBindViewHolder(@NonNull final Viewhdler holder, final int position) {
        final TinTuc tinTuc=arrayList.get(position);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyy,MM,dd");
        if(tinTuc.getHinhbaiviet().endsWith("news.jpg")){
            Picasso.with(context).load(APIServices.urlhinh+tinTuc.getHinhbaiviet()).into(holder.imageBackground);
        }else {
            Picasso.with(context).load(tinTuc.getHinhbaiviet()).into(holder.imageBackground);
        }
        holder.txtnoidungbantin.setText(tinTuc.getNoidung());
        holder.txttitlebantin.setText(tinTuc.getTenbaiviet());
        holder.txtngaytintuc.setText("Ngày Đăng: "+tinTuc.getThoigian());
        Animation animationUtils=AnimationUtils.loadAnimation(context,R.anim.xoay);
        holder.txtsothutu.setAnimation(animationUtils);
        if(arrayList.size()==1){
            holder.txtsothutu.setText("0");
        }else {
            holder.txtsothutu.setText(position + 1 + "");
        }
        if(MainActivity.sharedPreferences.getString("admin","").equals("")){
            holder.imgMenuItemTin_tuc.setVisibility(View.GONE);
        }else{
            holder.imgMenuItemTin_tuc.setVisibility(View.VISIBLE);
        }
        holder.imgMenuItemTin_tuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu=new PopupMenu(context,holder.imgMenuItemTin_tuc);
                popupMenu.getMenuInflater().inflate(R.menu.menu_item_quanly_xoa_xua,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getItemId()==R.id.xoa){
                            DataService dataService= APIServices.getService();
                            Call<String>callback=dataService.delete_NewsItemAdmin(arrayList.get(position).getId()+"");
                            callback.enqueue(new Callback<String>() {
                                @Override
                                public void onResponse(Call<String> call, Response<String> response) {
                                    Log.d("AAA","delete_NewsAdmin: "+response.toString());
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
                        if(item.getItemId()==R.id.sua){
                            Intent intent=new Intent(context, UpdateTinTuc.class);
                            intent.putExtra("News",arrayList.get(position));
                            context.startActivity(intent);
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Viewhdler extends RecyclerView.ViewHolder{
        private ImageView imageBackground,imgMenuItemTin_tuc;
        private TextView txttitlebantin,txtnoidungbantin,txtngaytintuc,txtsothutu;
        public Viewhdler(@NonNull View itemView) {
            super(itemView);
            imageBackground=itemView.findViewById(R.id.imageBackground);
            txttitlebantin=itemView.findViewById(R.id.txttitlebantin);
            txtnoidungbantin=itemView.findViewById(R.id.txtnoidungbantin);
            txtngaytintuc=itemView.findViewById(R.id.txtngaytintuc);
            txtsothutu=itemView.findViewById(R.id.txtsothutu);
            imgMenuItemTin_tuc=itemView.findViewById(R.id.imgMenuItemTin_tuc);
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
