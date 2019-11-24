package com.example.cuahang_doan.Fragment.Tai_Khoan.DonHang;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuahang_doan.Activity.DonHangCuaBan;
import com.example.cuahang_doan.Activity.Hoadon;
import com.example.cuahang_doan.Activity.MainActivity;
import com.example.cuahang_doan.Adapter.Adapter_Chitietdondathang;
import com.example.cuahang_doan.R;
import com.example.cuahang_doan.Services.APIServices;
import com.example.cuahang_doan.Services.DataService;
import com.example.cuahang_doan.model.Chitietdondathang;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DialogFragment_Chitietdonhang extends DialogFragment {
    private int id;
    private String trinhtrang;
    private View view;
    private TextView txtiddonhang,txtdiachinhanhan,txtsodienthoa,txttrinhtrangdonhang,txtthongtin;
    private RecyclerView recyclerviewchitietdonhang;
    private RelativeLayout relativelupdatechitietdonhang;

    public DialogFragment_Chitietdonhang(int id,String trinhtrang) {
        this.id = id;
        this.trinhtrang=trinhtrang;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.dialogfragment_chitietdonhang,container,false);
        anhxa();
        if (MainActivity.sharedPreferences.getString("admin", "").equals("")) {
            relativelupdatechitietdonhang.setVisibility(View.GONE);
        }else{
            relativelupdatechitietdonhang.setVisibility(View.VISIBLE);
        }
        getdatachitietdondathang(id);
        txtthongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(id!=0){
                    Intent intent=new Intent(view.getContext(), Hoadon.class);
                    intent.putExtra("idhoadon",id+"");
                    startActivity(intent);
                }
            }
        });
        return view;
    }

    private void updatedonhang(final int id, final String trinhtrang) {
        if(!MainActivity.sharedPreferences.getString("admin","").equals("")) {
            if(trinhtrang.equals("Đã Giao Hàng") || trinhtrang.equals("Đã Hủy")) {
                relativelupdatechitietdonhang.setVisibility(View.GONE);
            }else{
            relativelupdatechitietdonhang.setVisibility(View.VISIBLE);
            relativelupdatechitietdonhang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (trinhtrang.equals("Chờ Xét Duyệt")) {
                        String trinhtrangmoi = "Đang Vận Chuyển";
                        updatechoxetduyetadmin(id + "", trinhtrangmoi);
                    }
                    if (trinhtrang.equals("Đang Vận Chuyển")) {
                        String trinhtrangmoi = "Đã Giao Hàng";
                        updatechoxetduyetadmin(id + "", trinhtrangmoi);
                    }
                }
            });
        }
        }
    }
    private void updatechoxetduyetadmin(final String iddondathang, final String trinhtrang ){
        DataService dataService=APIServices.getService();
        Call<String>callback=dataService.updatedondathangadmin(iddondathang,trinhtrang);

        callback.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d("AAA","Update DangVanChuyen: "+response.toString());
                Log.d("AAA",iddondathang+"");
                Log.d("AAA",trinhtrang+"");
                if(response.isSuccessful()){
                    Toast.makeText(getContext(), "Update Thành Công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getContext(), DonHangCuaBan.class));
                    Fragment prev = getFragmentManager().findFragmentByTag("chitietdonhang");
                    if (prev != null) {
                        DialogFragment df = (DialogFragment) prev;
                        df.dismiss();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    private void getdatachitietdondathang(final int id) {
        if(id!=0){
            DataService dataService= APIServices.getService();
            Call<List<Chitietdondathang>>callback=dataService.getdatachitietdonhang(id+"");
            callback.enqueue(new Callback<List<Chitietdondathang>>() {
                @Override
                public void onResponse(Call<List<Chitietdondathang>> call, Response<List<Chitietdondathang>> response) {
                    Log.d("AAA","Getdata Chitietdonhang"+response.toString());
                    if(response.isSuccessful()){
                        ArrayList<Chitietdondathang>arrayList= (ArrayList<Chitietdondathang>) response.body();
                        if(arrayList!=null) {
                            txtiddonhang.setText("Đơn Hàng: " + arrayList.get(0).getIdDonDatHang() + "");
                            txtdiachinhanhan.setText(arrayList.get(0).getDiaChi());
                            txtsodienthoa.setText(arrayList.get(0).getSoDienThoai() + "");
                            txttrinhtrangdonhang.setText(trinhtrang);
                            Adapter_Chitietdondathang adapter = new Adapter_Chitietdondathang(getActivity()
                                    , R.layout.layout_chitietdonhang, arrayList);
                            recyclerviewchitietdonhang.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                            updatedonhang(id,txttrinhtrangdonhang.getText().toString());
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<Chitietdondathang>> call, Throwable t) {

                }
            });
        }
    }

    private void anhxa() {
        txtthongtin=view.findViewById(R.id.txtthongtin);
        relativelupdatechitietdonhang=view.findViewById(R.id.relativelupdatechitietdonhang);
        txtiddonhang=view.findViewById(R.id.txtiddonhang);
        txtdiachinhanhan=view.findViewById(R.id.txtdiachinhanhan);
        txtsodienthoa=view.findViewById(R.id.txtsodienthoa);
        txttrinhtrangdonhang=view.findViewById(R.id.txttrinhtrangdonhang);
        recyclerviewchitietdonhang=view.findViewById(R.id.recyclerviewchitietdonhang);
        recyclerviewchitietdonhang.setHasFixedSize(true);
        recyclerviewchitietdonhang.setLayoutManager(new GridLayoutManager(getActivity(),1));
    }


}
