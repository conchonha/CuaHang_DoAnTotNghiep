package com.example.cuahang_doan.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cuahang_doan.Activity.MainActivity;
import com.example.cuahang_doan.Adapter.Comment_Adapter;
import com.example.cuahang_doan.Fragment.Fragment_giohang;
import com.example.cuahang_doan.R;
import com.example.cuahang_doan.Services.APIServices;
import com.example.cuahang_doan.Services.DataService;
import com.example.cuahang_doan.model.GioHang;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Adapter_Giohang extends BaseAdapter {
    private MainActivity context;
    private ArrayList<GioHang>arrayList;
//    private ImageView imggiohang11;
//    private TextView txttensanphamgiohang,txtgiasanphamgiohang,txtsoluonggiohang;
//    private Button btngiam,btntang;
//    private ImageButton imgbttonxoa;

    public Adapter_Giohang(MainActivity context, ArrayList<GioHang> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public class Viewhodler{
        private ImageView imggiohang11;
        private TextView txttensanphamgiohang,txtgiasanphamgiohang,txtsoluonggiohang;
        private Button btngiam,btntang;
        private ImageButton imgbttonxoa;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Viewhodler viewhodler=null;
        if(view==null){
            viewhodler=new Viewhodler();
            view=View.inflate(context, R.layout.layout_giohang,null);
            viewhodler.imggiohang11=view.findViewById(R.id.imggiohang11);
            viewhodler.txttensanphamgiohang=view.findViewById(R.id.txttensanphamgiohang);
            viewhodler.txtgiasanphamgiohang=view.findViewById(R.id.txtgiasanphamgiohang);
            viewhodler.txtsoluonggiohang=view.findViewById(R.id.txtsoluonggiohang);
            viewhodler.btngiam=view.findViewById(R.id.btngiam);
            viewhodler.btntang=view.findViewById(R.id.btntang);
            viewhodler.imgbttonxoa=view.findViewById(R.id.imgbttonxoa);
            viewhodler.btngiam=view.findViewById(R.id.btngiam);
            view.setTag(viewhodler);
        }else{
            viewhodler= (Viewhodler) view.getTag();
        }
        final GioHang gioHang=arrayList.get(i);
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        try {
            Picasso.with(context).load(gioHang.getHinh()).into(viewhodler.imggiohang11);
        }catch (Exception e1){
            Log.d("AAA","loi picaso: "+e1.toString());
        }

        viewhodler.txttensanphamgiohang.setText(gioHang.getTenSanpham());
        viewhodler.txtgiasanphamgiohang.setText(decimalFormat.format(gioHang.getThanhTien())+"");
        viewhodler.txtsoluonggiohang.setText(gioHang.getSoLuong()+"");
        viewhodler.btngiam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updategiohanggiam(gioHang.getSoLuong(),gioHang.getThanhTien(),gioHang.getIdSanpham());
            }
        });
        viewhodler.btntang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updategiohangTang(gioHang.getSoLuong(),gioHang.getThanhTien(),gioHang.getIdSanpham());
            }
        });
        viewhodler.imgbttonxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletesanpham(gioHang.getIdSanpham());
            }
        });
        return view;
    }

    private void deletesanpham(final String idSanpham) {
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle("Bạn có muốn xóa sản phẩm?");
        builder.setNegativeButton("không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DataService dataService=APIServices.getService();
                Call<String>callback=dataService.Deletesanpham(idSanpham);
                callback.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Log.d("AAA","delete sanpham giohang: "+response.toString());
                        if(response.isSuccessful()){
                            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                            context.reloaddulieu();
                        }else{
                            Toast.makeText(context, "Không thành công vui lòng thử lại sau", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.d("AAA","delete sanpham giohang: "+t.toString());

                    }
                });
            }
        });
        builder.show();
    }

    private void updategiohangTang(Integer soLuong, Integer thanhTien, String idSanpham) {
        if(soLuong < 10){
            int slcu=soLuong;
            int giacu=thanhTien/soLuong;
            int soluongmoi=slcu+1;
            int giamoi=thanhTien+giacu;
            Log.d("AAA","slcu: "+slcu);
            Log.d("AAA","giacu: "+giacu);
            Log.d("AAA","slmoi: "+soluongmoi);
            Log.d("AAA","gia moi: "+giamoi);

            postgiohang(soluongmoi,giamoi,idSanpham);
        }else{
            Toast.makeText(context, "Số lượng sp đã đạt mức Cao nhất", Toast.LENGTH_SHORT).show();
        }
    }

    private void updategiohanggiam(Integer soLuong, Integer thanhTien, String idSanpham) {
        if(soLuong>1){
            int slcu=soLuong;
            int giacu=thanhTien/soLuong;
            int soluongmoi=slcu-1;
            int giamoi=thanhTien-giacu;
            Log.d("AAA","slcu: "+slcu);
            Log.d("AAA","giacu: "+giacu);
            Log.d("AAA","slmoi: "+soluongmoi);
            Log.d("AAA","gia moi: "+giamoi);

            postgiohang(soluongmoi,giamoi,idSanpham);
        }else{
            Toast.makeText(context, "Số lượng sp đã đạt mức thấp nhất", Toast.LENGTH_SHORT).show();
        }
    }

    private void postgiohang(int soluongmoi, int giamoi, String idSanpham) {
        DataService dataService= APIServices.getService();
        Call<String>callback=dataService.updategiohang(idSanpham,soluongmoi,giamoi);
        callback.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d("AAA","Cập nhật giam giohang: "+response.toString());
                if(response.isSuccessful()){
                    Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    context.reloaddulieu();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("AAA","erro giam: "+t.toString());
            }
        });
    }
}
