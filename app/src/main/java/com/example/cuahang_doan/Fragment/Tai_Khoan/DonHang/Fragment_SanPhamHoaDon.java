package com.example.cuahang_doan.Fragment.Tai_Khoan.DonHang;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuahang_doan.Activity.MainActivity;
import com.example.cuahang_doan.Adapter.Adapter_HoaDon;
import com.example.cuahang_doan.R;
import com.example.cuahang_doan.Services.APIServices;
import com.example.cuahang_doan.Services.DataService;
import com.example.cuahang_doan.model.HoaDon;

import java.text.DecimalFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_SanPhamHoaDon extends Fragment {
    private RecyclerView recyclerView;
    private View view;
    private List<HoaDon> arrayList;
    private TextView txtgiasanphamhoadon;

    public Fragment_SanPhamHoaDon(List<HoaDon> arrayList) {
        this.arrayList = arrayList;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view=inflater.inflate(R.layout.dondathang,container,false);
       anhxa();
        getdatahoadon();
        return view;
    }
    private void getdatahoadon() {
        if(arrayList!=null) {
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            Adapter_HoaDon adapter = new Adapter_HoaDon(getActivity(), R.layout.layout_hoadon, arrayList);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            int tong = 0;
            for (int i = 0; i < arrayList.size(); i++) {
                tong += arrayList.get(i).getGiaSanPham();
            }
            txtgiasanphamhoadon.setText(decimalFormat.format(tong) + " Đồng");
        }
    }

    private void anhxa() {
        txtgiasanphamhoadon=view.findViewById(R.id.txtgiasanphamhoadon);
        recyclerView=view.findViewById(R.id.recyclerviewhoadon);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
    }
}
