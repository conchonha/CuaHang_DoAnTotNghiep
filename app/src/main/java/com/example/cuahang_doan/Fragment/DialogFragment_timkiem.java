package com.example.cuahang_doan.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuahang_doan.Adapter.TimkiemTaikhoan_Adapter;
import com.example.cuahang_doan.R;
import com.example.cuahang_doan.Services.APIServices;
import com.example.cuahang_doan.Services.DataService;
import com.example.cuahang_doan.model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DialogFragment_timkiem extends DialogFragment {
    private View view;
    private ArrayList<User>arrayList;
    private TimkiemTaikhoan_Adapter adapter;
    private RecyclerView recyclerviewtimkiemtaikhoan;
    private EditText edttimkiem;
    private ImageView imgtimkiem;
    private TextView txtdong;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.dialogfragment_timkiem,container,false);
        anhxa();
        getdatataikhoan();
        timkiem();
        dong();
        return view;
    }

    private void dong() {
        txtdong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment prev = getFragmentManager().findFragmentByTag("dialogtimkiem");
                if (prev != null) {
                    DialogFragment df = (DialogFragment) prev;
                    df.dismiss();
                }

            }
        });
    }

    private void timkiem() {
        imgtimkiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if(!edttimkiem.getText().toString().equals("") && arrayList.size()>0){
                    DataService dataService=APIServices.getService();
                    Call<List<User>>callback=dataService.timkiem(edttimkiem.getText().toString());
                    callback.enqueue(new Callback<List<User>>() {
                        @Override
                        public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                            Log.d("AAA","event timkiem: "+response.toString());
                            if(response.isSuccessful()){
                                arrayList= (ArrayList<User>) response.body();
                                adapter=new TimkiemTaikhoan_Adapter(arrayList,R.layout.layout_taikhoan_giohang,view.getContext());
                                adapter.notifyDataSetChanged();
                                recyclerviewtimkiemtaikhoan.setAdapter(adapter);
                            }else{
                                Toast.makeText(view.getContext(), "Không tìm thấy kết quả", Toast.LENGTH_SHORT).show();
                                getdatataikhoan();
                            }
                        }

                        @Override
                        public void onFailure(Call<List<User>> call, Throwable t) {

                        }
                    });
                }else{
                    Toast.makeText(view.getContext(), "không để trống dữ liệu", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getdatataikhoan() {
        DataService dataService= APIServices.getService();
        Call<List<User>>callback =dataService.getdatataikhoan();
        callback.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                Log.d("AAA","getdata taikhoan: "+response.toString());
                if(response.isSuccessful()){
                    arrayList= (ArrayList<User>) response.body();
                    adapter=new TimkiemTaikhoan_Adapter(arrayList,R.layout.layout_taikhoan_giohang,view.getContext());
                    adapter.notifyDataSetChanged();
                    recyclerviewtimkiemtaikhoan.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
    }

    private void anhxa() {
        txtdong=view.findViewById(R.id.txtdong);
        edttimkiem=view.findViewById(R.id.edttimkiem);
        imgtimkiem=view.findViewById(R.id.imgtimkiem);
        arrayList=new ArrayList<>();
        recyclerviewtimkiemtaikhoan=view.findViewById(R.id.recyclerviewtimkiemtaikhoan);
        recyclerviewtimkiemtaikhoan.setLayoutManager(new GridLayoutManager(view.getContext(),2));
        recyclerviewtimkiemtaikhoan.setHasFixedSize(true);
        recyclerviewtimkiemtaikhoan.setItemViewCacheSize(20);
    }
}
