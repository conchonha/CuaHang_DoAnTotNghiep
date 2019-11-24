package com.example.cuahang_doan.Fragment.TrangChu;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuahang_doan.Adapter.DanhmucAdapter;
import com.example.cuahang_doan.Adapter.danhMucCon_Adapter;
import com.example.cuahang_doan.R;
import com.example.cuahang_doan.Services.APIServices;
import com.example.cuahang_doan.Services.DataService;
import com.example.cuahang_doan.model.DanhMucCon;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DialogFragment_DanhMuc extends DialogFragment {
    private View view;
    private RecyclerView recyclerviewdialogdanhmuc;
    private String id;

    public DialogFragment_DanhMuc(String id) {
        this.id = id;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view=inflater.inflate(R.layout.dialogfragment_danhmuc,container,false);
       anhxa();
        if(!id.equals("")){
            DataService dataService= APIServices.getService();
            Call<List<DanhMucCon>>callback=dataService.getdatadanhmuccon(id+"");
            callback.enqueue(new Callback<List<DanhMucCon>>() {
                @Override
                public void onResponse(Call<List<DanhMucCon>> call, Response<List<DanhMucCon>> response) {
                    Log.d("AAA","Danh muc con: "+response.toString());
                    if (response.isSuccessful()){
                        ArrayList<DanhMucCon>arrayList= (ArrayList<DanhMucCon>) response.body();
                        danhMucCon_Adapter adapter=new danhMucCon_Adapter(getActivity(),R.layout.layout_danhmuc_dialog,id,arrayList);
                        recyclerviewdialogdanhmuc.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<List<DanhMucCon>> call, Throwable t) {

                }
            });
        }
        return view;
    }

    private void anhxa() {
        recyclerviewdialogdanhmuc=view.findViewById(R.id.recyclerviewdialogdanhmuc);
        recyclerviewdialogdanhmuc.setHasFixedSize(true);
        recyclerviewdialogdanhmuc.setLayoutManager(new GridLayoutManager(getActivity(),2));
    }
}
