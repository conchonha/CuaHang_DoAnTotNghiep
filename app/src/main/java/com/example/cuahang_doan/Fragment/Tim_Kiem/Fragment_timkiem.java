package com.example.cuahang_doan.Fragment.Tim_Kiem;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuahang_doan.Adapter.Adapter_Sanphammoinhat;
import com.example.cuahang_doan.R;
import com.example.cuahang_doan.Services.APIServices;
import com.example.cuahang_doan.Services.DataService;
import com.example.cuahang_doan.model.GetdataSanphammoinhat;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_timkiem extends Fragment {
    private View view;
    private ImageView imgback,imgseach;
    private TextView txttitlefragmenttimkiem;
    private EditText edtfragmenttimkiem;
    private RecyclerView recyclerviewTimkimsp;
    private Button btnxemthem;
    private Adapter_Sanphammoinhat adpter;
    private int dem=1;
    private ArrayList<GetdataSanphammoinhat>arrayList;
    private RelativeLayout relativeLayouttollbar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_timkiem,container,false);
       anhxa();
       init();
       getdataspPhantrang(0);
       sukienxemthem();
       return view;
    }

    private void sukienxemthem() {
        btnxemthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dem++;
                int sl=dem*7;
                getdataspPhantrang(sl);
                adpter.notifyDataSetChanged();
            }
        });
    }

    private void getdataspPhantrang(int soluong) {
        DataService dataService= APIServices.getService();
        Call<List<GetdataSanphammoinhat>>callback=dataService.getdataPhantrang(soluong+"");
        callback.enqueue(new Callback<List<GetdataSanphammoinhat>>() {
            @Override
            public void onResponse(Call<List<GetdataSanphammoinhat>> call, Response<List<GetdataSanphammoinhat>> response) {
                Log.d("AAA","Getdataphantrang: "+response.toString());
                if(response.isSuccessful()){
                   ArrayList<GetdataSanphammoinhat> array= (ArrayList<GetdataSanphammoinhat>) response.body();
                   for (int i=0;i<array.size();i++){
                       arrayList.add(new GetdataSanphammoinhat(
                               array.get(i).getId(),
                               array.get(i).getTenSanPham(),
                               array.get(i).getHinhAnhSanPham(),
                               array.get(i).getThongSoKyThuat(),
                               array.get(i).getGia(),
                               array.get(i).getNgayKhuyenMai(),
                               array.get(i).getGiamGia(),
                               array.get(i).getDanhGiaSao(),
                               array.get(i).getLoai()
                               ));
                   }
                    setdatarecyclerview(arrayList);

                }
            }

            @Override
            public void onFailure(Call<List<GetdataSanphammoinhat>> call, Throwable t) {
                Log.d("AAA","kocodulieuGetdata phantrang: "+t.toString());
                btnxemthem.setVisibility(View.GONE);
            }
        });
    }

    private void init() {
        relativeLayouttollbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtfragmenttimkiem.setVisibility(View.GONE);
                txttitlefragmenttimkiem.setVisibility(View.VISIBLE);
            }
        });
        imgseach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtfragmenttimkiem.setVisibility(View.VISIBLE);
                txttitlefragmenttimkiem.setVisibility(View.GONE);
            }
        });
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
                startActivity(getActivity().getIntent());
            }
        });
        edtfragmenttimkiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String search=edtfragmenttimkiem.getText().toString();
                DataService dataService=APIServices.getService();
                Call<List<GetdataSanphammoinhat>>callback=dataService.getdataTimkiemsanpham(search);
                callback.enqueue(new Callback<List<GetdataSanphammoinhat>>() {
                    @Override
                    public void onResponse(Call<List<GetdataSanphammoinhat>> call, Response<List<GetdataSanphammoinhat>> response) {
                        Log.d("AAA","Tim kim sp: "+response.toString());
                        if(response.isSuccessful()){
                            ArrayList<GetdataSanphammoinhat>arrayList= (ArrayList<GetdataSanphammoinhat>) response.body();
                            setdatarecyclerview(arrayList);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<GetdataSanphammoinhat>> call, Throwable t) {
                        Log.d("AAA","Loi timkim Sp: "+t.toString());
                        btnxemthem.setVisibility(View.GONE);
                    }
                });
            }
        });

    }

    private void anhxa() {
        if(arrayList==null){
            arrayList=new ArrayList<>();
        }else {

        }
        relativeLayouttollbar=view.findViewById(R.id.ttt);
        btnxemthem=view.findViewById(R.id.btnxemthem);
        recyclerviewTimkimsp=view.findViewById(R.id.recyclerviewTimkimsp);
        imgback=view.findViewById(R.id.imgback);
        imgseach=view.findViewById(R.id.imgseach);
        txttitlefragmenttimkiem=view.findViewById(R.id.txttitlefragmenttimkiem);
        edtfragmenttimkiem=view.findViewById(R.id.edtfragmenttimkiem);
    }
   public void setdatarecyclerview(ArrayList<GetdataSanphammoinhat>arrayList){
       recyclerviewTimkimsp.setHasFixedSize(true);
       recyclerviewTimkimsp.setLayoutManager(new GridLayoutManager(getContext(),2));
       adpter=new Adapter_Sanphammoinhat(getContext(),R.layout.layout_linhkienlaptop,arrayList);
       recyclerviewTimkimsp.setAdapter(adpter);
       adpter.notifyDataSetChanged();
    }

}
