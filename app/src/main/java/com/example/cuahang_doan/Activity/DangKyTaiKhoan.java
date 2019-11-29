package com.example.cuahang_doan.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.cuahang_doan.Activity.admin.QuanLytaikhoan;
import com.example.cuahang_doan.R;
import com.example.cuahang_doan.Services.APIServices;
import com.example.cuahang_doan.Services.DataService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangKyTaiKhoan extends AppCompatActivity {
    private RelativeLayout relativedangkytaikhoan;
    private EditText edtusername,edtpassword,edtemaill,edtsodienthoaii,edtdiachii;
    private Button btndangky;
    private Toolbar toolbardangkytaikhoan;
    private int idloai=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky_tai_khoan);
        anhxa();
        init();
        getintent();
       dangky();

    }

    private void getintent() {
        Intent intent=getIntent();
        if(intent!=null){
            if(intent.hasExtra("khachhang")){
                idloai=0;
                Log.d("AAA","idloai: "+idloai);
            }
            if(intent.hasExtra("nhanvien")){
                idloai=1;
                Log.d("AAA","idloai: "+idloai);
            }
            if(intent.hasExtra("admin")){
                idloai=2;
                Log.d("AAA","idloai: "+idloai);
            }
        }
    }

    private void dangky() {
        btndangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtusername.getText().toString().equals("") || edtpassword.getText().toString().equals("") ||
                        edtemaill.getText().toString().equals("") || edtsodienthoaii.getText().toString().equals("") ||
                        edtdiachii.getText().toString().equals("") ){
                    Toast.makeText(DangKyTaiKhoan.this, "Không Được Để Trống Dữ Liệu", Toast.LENGTH_SHORT).show();
                }else if(!edtemaill.getText().toString().endsWith("@gmail.com")){
                    Toast.makeText(DangKyTaiKhoan.this, "Sai Email", Toast.LENGTH_SHORT).show();
                }else if(edtsodienthoaii.getText().toString().startsWith("086")||
                        edtsodienthoaii.getText().toString().startsWith("096") ||
                        edtsodienthoaii.getText().toString().startsWith("097")||
                        edtsodienthoaii.getText().toString().startsWith("098") ||
                        edtsodienthoaii.getText().toString().startsWith("032")||
                        edtsodienthoaii.getText().toString().startsWith("032") ||
                        edtsodienthoaii.getText().toString().startsWith("034")||
                        edtsodienthoaii.getText().toString().startsWith("035") ||
                        edtsodienthoaii.getText().toString().startsWith("036")||
                        edtsodienthoaii.getText().toString().startsWith("036") ||
                        edtsodienthoaii.getText().toString().startsWith("037")||
                        edtsodienthoaii.getText().toString().startsWith("038") ||
                        edtsodienthoaii.getText().toString().startsWith("039")||
                        edtsodienthoaii.getText().toString().startsWith("089") ||
                        edtsodienthoaii.getText().toString().startsWith("090")||
                        edtsodienthoaii.getText().toString().startsWith("093") ||
                        edtsodienthoaii.getText().toString().startsWith("070")||
                        edtsodienthoaii.getText().toString().startsWith("079") ||
                        edtsodienthoaii.getText().toString().startsWith("079")||
                        edtsodienthoaii.getText().toString().startsWith("077") ||
                        edtsodienthoaii.getText().toString().startsWith("076")||
                        edtsodienthoaii.getText().toString().startsWith("078") &&
                                edtsodienthoaii.getText().toString().length()==10){

                    DataService dataService= APIServices.getService();
                    Call<String>callback=dataService.dangkytaikhoan(edtusername.getText().toString(),
                            edtpassword.getText().toString(), edtemaill.getText().toString(),edtsodienthoaii.getText().toString(),
                            edtdiachii.getText().toString(),idloai+"");
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            Log.d("AAA","dang ky tai khoan: "+response.toString());
                            if(response.isSuccessful()){
                                Toast.makeText(DangKyTaiKhoan.this, "Đang Ký Thành Công", Toast.LENGTH_SHORT).show();
                                if(MainActivity.sharedPreferences.getString("admin","").equals("")){
                                    startActivity(new Intent(getApplicationContext(),Login.class));
                                    finish();
                                }else{
                                    startActivity(new Intent(getApplicationContext(), QuanLytaikhoan.class));
                                    finish();
                                }

                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                }else{
                    Toast.makeText(DangKyTaiKhoan.this, "sai so dien thoai", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void init() {
        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.animalpha);
        //relativedangkytaikhoan.setAnimation(animation);
        setSupportActionBar(toolbardangkytaikhoan);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbardangkytaikhoan.setNavigationIcon(R.drawable.back);
        toolbardangkytaikhoan.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void anhxa() {
        toolbardangkytaikhoan=findViewById(R.id.toolbardangkytaikhoan);
        btndangky=findViewById(R.id.btndangky);
        edtdiachii=findViewById(R.id.edtdiachii);
        edtsodienthoaii=findViewById(R.id.edtsodienthoaii);
        edtemaill=findViewById(R.id.edtemaill);
        edtpassword=findViewById(R.id.edtpassword);
        edtusername=findViewById(R.id.edtusername);
        //relativedangkytaikhoan=findViewById(R.id.relativedangkytaikhoan);
    }
}
