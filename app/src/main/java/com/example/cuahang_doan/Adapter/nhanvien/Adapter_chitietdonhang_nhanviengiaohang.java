package com.example.cuahang_doan.Adapter.nhanvien;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuahang_doan.Activity.nhanvien.NhanVien;
import com.example.cuahang_doan.Fragment.Tai_Khoan.DonHang.DialogFragment_Chitietdonhang;
import com.example.cuahang_doan.R;
import com.example.cuahang_doan.Services.APIServices;
import com.example.cuahang_doan.Services.DataService;
import com.example.cuahang_doan.model.DonDatHang;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Adapter_chitietdonhang_nhanviengiaohang extends RecyclerView.Adapter<Adapter_chitietdonhang_nhanviengiaohang.Viewhodler> {
    private View view;
    private ArrayList<DonDatHang>arrayList;
    private NhanVien context;
    private int layout;

    public Adapter_chitietdonhang_nhanviengiaohang(ArrayList<DonDatHang> arrayList, NhanVien context, int layout) {
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
        final DonDatHang donDatHang=arrayList.get(position);
        holder.trinhtrang.setText(donDatHang.getTrinhTrang());
        holder.ngaydat.setText(donDatHang.getNgayDat());
        holder.iddonhang.setText(donDatHang.getIdDonHang()+"");
        holder.imgdeletedonhangcuaban.setVisibility(View.VISIBLE);
        holder.imgdeletedonhangcuaban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(donDatHang.getTrinhTrang().equals("Chờ Xét Duyệt")){
                    DataService dataService= APIServices.getService();
                    Call<String>callback=dataService.UpdateHuydondathang(donDatHang.getIdDonHang()+"");
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            Log.d("AAA","Upfate huy don dat hang; "+response.toString());
                            if(response.isSuccessful()){
                                Toast.makeText(context, "Da Huy Don thanh Cong", Toast.LENGTH_SHORT).show();
                                context.startActivity(((Activity)context).getIntent());
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Viewhodler extends RecyclerView.ViewHolder{
        private TextView iddonhang,ngaydat,trinhtrang;
        private ImageView imgdeletedonhangcuaban;
        public Viewhodler(@NonNull View itemView) {
            super(itemView);
            iddonhang=itemView.findViewById(R.id.iddonhang);
            imgdeletedonhangcuaban=itemView.findViewById(R.id.imgdeletedonhangcuaban);
            ngaydat=itemView.findViewById(R.id.ngaydat);
            trinhtrang=itemView.findViewById(R.id.trinhtrang);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentManager fragmentManager=context.getSupportFragmentManager();
                    DialogFragment_Chitietdonhang dialogFragment_chitietdonhang=new DialogFragment_Chitietdonhang(
                            arrayList.get(getPosition()).getIdDonHang(),arrayList.get(getPosition()).getTrinhTrang());
                    dialogFragment_chitietdonhang.show(fragmentManager,"chitietdonhang");
                }
            });
        }
    }
}
