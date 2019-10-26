package com.example.cuahang_doan.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    private TextView txtiddonhang,txtdiachinhanhan,txtsodienthoa,txttrinhtrangdonhang;
    private RecyclerView recyclerviewchitietdonhang;

    public DialogFragment_Chitietdonhang(int id,String trinhtrang) {
        this.id = id;
        this.trinhtrang=trinhtrang;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.dialogfragment_chitietdonhang,container,false);
        anhxa();
        getdatachitietdondathang(id);
        return view;
    }

    private void getdatachitietdondathang(int id) {
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
        txtiddonhang=view.findViewById(R.id.txtiddonhang);
        txtdiachinhanhan=view.findViewById(R.id.txtdiachinhanhan);
        txtsodienthoa=view.findViewById(R.id.txtsodienthoa);
        txttrinhtrangdonhang=view.findViewById(R.id.txttrinhtrangdonhang);
        recyclerviewchitietdonhang=view.findViewById(R.id.recyclerviewchitietdonhang);
        recyclerviewchitietdonhang.setHasFixedSize(true);
        recyclerviewchitietdonhang.setLayoutManager(new GridLayoutManager(getActivity(),1));
    }


}
