package com.example.cuahang_doan.Fragment.Tai_Khoan.TaiKhoan;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.cuahang_doan.Activity.Login;
import com.example.cuahang_doan.Activity.MainActivity;
import com.example.cuahang_doan.R;
import com.example.cuahang_doan.Services.APIServices;
import com.example.cuahang_doan.Services.DataService;
import com.example.cuahang_doan.model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DialogFragment_Chinhsuadiachi extends DialogFragment {
    private View view;
    private EditText edtdiachi;
    private Button btnhuythongtintaikhoan1,btnsacnhanthongtintaikhoan1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.layout_chinhsuadiachi_dialogfragment,container,false);
        anhxa();
        init();
        return view;
    }

    private void init() {
        if(!MainActivity.sharedPreferences.getString("diachi","").equals("")) {
            edtdiachi.setText(MainActivity.sharedPreferences.getString("diachi", ""));

            btnhuythongtintaikhoan1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    edtdiachi.setText("");
                }
            });
            btnsacnhanthongtintaikhoan1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DataService dataService= APIServices.getService();
                    Call<List<User>>callback=dataService.updatediachi(edtdiachi.getText().toString(),
                            MainActivity.sharedPreferences.getInt("iduser",0)+"");
                    callback.enqueue(new Callback<List<User>>() {
                        @Override
                        public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                            Log.d("AAA","Update diachi: "+response.toString());
                            if(response.isSuccessful()){
                                ArrayList<User>arrayList= (ArrayList<User>) response.body();
                                MainActivity.editor.putString("diachi",arrayList.get(0).getAdress());
                                MainActivity.editor.commit();
                                getActivity().finish();
                                startActivity(getActivity().getIntent());
                                getActivity().overridePendingTransition(0,0);
                            }
                        }

                        @Override
                        public void onFailure(Call<List<User>> call, Throwable t) {

                        }
                    });
                }
            });
        }
    }

    private void anhxa() {
        edtdiachi=view.findViewById(R.id.edtdiachi);
        btnhuythongtintaikhoan1=view.findViewById(R.id.btnhuythongtintaikhoan1);
        btnsacnhanthongtintaikhoan1=view.findViewById(R.id.btnsacnhanthongtintaikhoan1);
    }

}
