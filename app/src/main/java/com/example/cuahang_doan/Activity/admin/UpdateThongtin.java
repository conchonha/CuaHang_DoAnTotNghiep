package com.example.cuahang_doan.Activity.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.cuahang_doan.Activity.Gioi_Thieu;
import com.example.cuahang_doan.R;
import com.example.cuahang_doan.Services.APIServices;
import com.example.cuahang_doan.Services.DataService;
import com.example.cuahang_doan.model.GioithieuShop;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateThongtin extends AppCompatActivity {
    private EditText edttencuahang,edttrusochinh,edtsodienthoaigioithieu,edittexemail,edittextwebsite,edittexFanpage;
    private GioithieuShop gioithieu;
    private Button btnupdatethongtin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_thongtin);
        anhxa();
        setdulieuchocactruong();
    }

    private void setdulieuchocactruong() {
        Intent intent=getIntent();
        if(intent!=null){
            gioithieu= (GioithieuShop) intent.getSerializableExtra("updatethongtinshop");
            edittexemail.setText(gioithieu.getEmail());
            edittexFanpage.setText(gioithieu.getFanpage());
            edittextwebsite.setText(gioithieu.getWebsite());
            edtsodienthoaigioithieu.setText(gioithieu.getDienThoai());
            edttencuahang.setText(gioithieu.getTenCuaHang());
            edttrusochinh.setText(gioithieu.getTruSoChinh());
            btnupdatethongtin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DataService dataService= APIServices.getService();
                    Call<String>callback=dataService.Updatethongtinshop(
                            edttencuahang.getText().toString(),
                             edttrusochinh.getText().toString(),
                            edtsodienthoaigioithieu.getText().toString(),
                            edittexemail.getText().toString(),
                            edittextwebsite.getText().toString(),
                            edittexFanpage.getText().toString()
                    );
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            Log.d("AAA","Update thongtinshop: "+response.toString());
                            if(response.isSuccessful()){
                                startActivity(new Intent(getApplicationContext(), Gioi_Thieu.class));

                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                }
            });
        }
    }

    private void anhxa() {
        btnupdatethongtin=findViewById(R.id.btnupdatethongtin);
        edttencuahang=findViewById(R.id.edttencuahang);
        edttrusochinh=findViewById(R.id.edttrusochinh);
        edtsodienthoaigioithieu=findViewById(R.id.edtsodienthoaigioithieu);
        edittexemail=findViewById(R.id.edittexemail);
        edittextwebsite=findViewById(R.id.edittextwebsite);
        edittexFanpage=findViewById(R.id.edittexFanpage);
    }
}
