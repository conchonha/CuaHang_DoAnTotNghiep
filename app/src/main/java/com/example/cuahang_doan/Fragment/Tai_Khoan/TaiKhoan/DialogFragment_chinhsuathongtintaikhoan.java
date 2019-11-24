package com.example.cuahang_doan.Fragment.Tai_Khoan.TaiKhoan;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class DialogFragment_chinhsuathongtintaikhoan extends DialogFragment {
    private View view;
    private EditText edttentaikhoan,edtsodienthoai,edtemail;
    private Button btnsacnhanthongtintaikhoan,btnhuythongtintaikhoan;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.layout_chinhsuathongtin_dialog,container,false);
        anhxa();
        init();
        sukienclick();
        return view;
    }

    private void sukienclick() {
        btnhuythongtintaikhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtemail.setText("");
                edtsodienthoai.setText("");
                edttentaikhoan.setText("");
            }
        });
        btnsacnhanthongtintaikhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(edtemail.getText().toString().endsWith("@gmail.com")&&
                        edtsodienthoai.getText().toString().startsWith("086")||
                        edtsodienthoai.getText().toString().startsWith("096")
                        ||edtsodienthoai.getText().toString().startsWith("097")||
                        edtsodienthoai.getText().toString().startsWith("098")
                        ||edtsodienthoai.getText().toString().startsWith("032")||
                        edtsodienthoai.getText().toString().startsWith("032")
                        ||edtsodienthoai.getText().toString().startsWith("034")||
                        edtsodienthoai.getText().toString().startsWith("035")
                        ||edtsodienthoai.getText().toString().startsWith("036")||
                        edtsodienthoai.getText().toString().startsWith("036")
                        ||edtsodienthoai.getText().toString().startsWith("037")||
                        edtsodienthoai.getText().toString().startsWith("038")
                        ||edtsodienthoai.getText().toString().startsWith("039")||
                        edtsodienthoai.getText().toString().startsWith("089")
                        ||edtsodienthoai.getText().toString().startsWith("090")||
                        edtsodienthoai.getText().toString().startsWith("093")
                        ||edtsodienthoai.getText().toString().startsWith("070")||
                        edtsodienthoai.getText().toString().startsWith("079")
                        ||edtsodienthoai.getText().toString().startsWith("079")||
                        edtsodienthoai.getText().toString().startsWith("077")
                        ||edtsodienthoai.getText().toString().startsWith("076")||
                        edtsodienthoai.getText().toString().startsWith("078")
                        && !edttentaikhoan.getText().toString().equals("")){

                    DataService dataService= APIServices.getService();
                    Call<List<User>> callback=dataService.updatethongtintaikhoan(
                            edttentaikhoan.getText().toString(),
                            edtsodienthoai.getText().toString(),
                            edtemail.getText().toString(),
                            MainActivity.sharedPreferences.getInt("iduser", 0)+""
                            );
                    callback.enqueue(new Callback<List<User>>() {
                        @Override
                        public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                            Log.d("AAA","Update thongtintaikhoan: "+response.toString());
                            if (response.isSuccessful()){
                                Toast.makeText(getActivity(), "Update Thành Công", Toast.LENGTH_SHORT).show();
                                ArrayList<User>arrayList= (ArrayList<User>) response.body();
                                MainActivity.editor.putString("username", edttentaikhoan.getText().toString());
                                MainActivity.editor.putInt("iduser", arrayList.get(0).getId());
                                MainActivity.editor.putString("email", arrayList.get(0).getEmail());
                                MainActivity.editor.putString("sodienthoai", arrayList.get(0).getPhoneNumBer());
                                MainActivity.editor.putString("hinh", APIServices.urlhinh+arrayList.get(0).getId()+".jpg");
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


            }
        });
    }

    private void init() {
        if(!MainActivity.sharedPreferences.getString("username","").equals("")) {
            edttentaikhoan.setText(MainActivity.sharedPreferences.getString("username", ""));
            edtemail.setText(MainActivity.sharedPreferences.getString("email", ""));
            edtsodienthoai.setText(MainActivity.sharedPreferences.getString("sodienthoai", ""));
        }else{
            startActivity(new Intent(getActivity(), Login.class));
        }
    }

    private void anhxa() {
        btnhuythongtintaikhoan=view.findViewById(R.id.btnhuythongtintaikhoan);
        btnsacnhanthongtintaikhoan=view.findViewById(R.id.btnsacnhanthongtintaikhoan);
        edtsodienthoai=view.findViewById(R.id.edtsodienthoai);
        edtemail=view.findViewById(R.id.edtemail);
        edttentaikhoan=view.findViewById(R.id.edttentaikhoan);
    }
}
