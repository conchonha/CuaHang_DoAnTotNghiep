package com.example.cuahang_doan.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cuahang_doan.Activity.admin.Admin;
import com.example.cuahang_doan.Activity.nhanvien.NhanVien;
import com.example.cuahang_doan.R;
import com.example.cuahang_doan.Services.APIServices;
import com.example.cuahang_doan.Services.DataService;
import com.example.cuahang_doan.model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    private EditText edittextusername,edittextpassword;
    private Button btndangnhap;
    private CheckBox ckeckbox;
    private ArrayList<User>arrayList;
    private TextView textdangky;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setanim();
        anhxa();
        login();
        init();

    }

    private void init() {
        textdangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),DangKyTaiKhoan.class));
            }
        });
    }

    private void login() {
        if(MainActivity.sharedPreferences.getString("username","")!=null &&
                MainActivity.sharedPreferences.getString("password","")!=null){
            edittextusername.setText(MainActivity.sharedPreferences.getString("username",""));
            edittextpassword.setText(MainActivity.sharedPreferences.getString("password",""));
        }
        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dangnhap();
            }
        });

    }

    private void anhxa() {
        textdangky=findViewById(R.id.textdangky);
        edittextusername=findViewById(R.id.edittextusername);
        edittextpassword=findViewById(R.id.edittextpassword);
        btndangnhap=findViewById(R.id.btndangnhap);
        ckeckbox=findViewById(R.id.ckeckbox);
        arrayList=new ArrayList<>();
    }

    private void setanim() {
        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.animalpha);
        RelativeLayout relativelayout=findViewById(R.id.relativelayout);
        relativelayout.setAnimation(animation);
    }
    private void dangnhap(){
        if(!edittextusername.getText().toString().trim().equals("") && !edittextpassword.getText().toString().trim().equals("")){
            DataService dataService= APIServices.getService();
            Call<List<User>>callback=dataService.postLogin(edittextusername.getText().toString(),edittextpassword.getText().toString());
            callback.enqueue(new Callback<List<User>>() {
                @Override
                public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                    Log.d("AAA",response.toString());
                    if(response.isSuccessful()){
                        arrayList= (ArrayList<User>) response.body();
                        if(arrayList.size()>0) {
                            if(arrayList.get(0).getLoai()==1){
                                MainActivity.editor.remove("admin");
                                MainActivity.editor.putString("nhanvien","nhanvien");
                                MainActivity.editor.commit();
                                startActivity(new Intent(getApplicationContext(), NhanVien.class));
                                Toast.makeText(Login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            }else if(arrayList.get(0).getLoai()==2){
                                AlertDialog.Builder dialog=new AlertDialog.Builder(Login.this);
                                dialog.setTitle("Bạn Đang Đăng Nhập Với Quyền Admin");
                                dialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        startActivity(new Intent(getApplicationContext(), Admin.class));
                                        MainActivity.editor.remove("nhanvien");
                                        MainActivity.editor.putString("admin",edittextusername.getText().toString());
                                        MainActivity.editor.putString("username", edittextusername.getText().toString());
                                        MainActivity.editor.putString("password", edittextpassword.getText().toString());
                                        MainActivity.editor.putInt("iduser", arrayList.get(0).getId());
                                        MainActivity.editor.putString("email", arrayList.get(0).getEmail());
                                        MainActivity.editor.putString("sodienthoai", arrayList.get(0).getPhoneNumBer());
                                        MainActivity.editor.putString("hinh", APIServices.urlhinh+arrayList.get(0).getId()+".jpg");
                                        MainActivity.editor.putString("diachi",arrayList.get(0).getAdress());
                                        MainActivity.editor.commit();
                                        Toast.makeText(Login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                dialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                        MainActivity.editor.remove("nhanvien");
                                        MainActivity.editor.remove("admin");
                                        MainActivity.editor.putString("username", edittextusername.getText().toString());
                                        MainActivity.editor.putString("password", edittextpassword.getText().toString());
                                        MainActivity.editor.putInt("iduser", arrayList.get(0).getId());
                                        MainActivity.editor.putString("email", arrayList.get(0).getEmail());
                                        MainActivity.editor.putString("sodienthoai", arrayList.get(0).getPhoneNumBer());
                                        MainActivity.editor.putString("hinh", APIServices.urlhinh+arrayList.get(0).getId()+".jpg");
                                        MainActivity.editor.putString("diachi",arrayList.get(0).getAdress());
                                        MainActivity.editor.commit();
                                        Toast.makeText(Login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                    }
                                });

                                dialog.show();


                            }else{
                                Toast.makeText(Login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                MainActivity.editor.remove("admin");
                                MainActivity.editor.remove("nhanvien");
                                Toast.makeText(Login.this, "Đăng Nhập Thành Công", Toast.LENGTH_SHORT).show();
                                MainActivity.editor.putString("username", edittextusername.getText().toString());
                                MainActivity.editor.putString("password", edittextpassword.getText().toString());
                                MainActivity.editor.putInt("iduser", arrayList.get(0).getId());
                                MainActivity.editor.putString("email", arrayList.get(0).getEmail());
                                MainActivity.editor.putString("sodienthoai", arrayList.get(0).getPhoneNumBer());
                                MainActivity.editor.putString("hinh", APIServices.urlhinh+arrayList.get(0).getId()+".jpg");
                                MainActivity.editor.putString("diachi",arrayList.get(0).getAdress());
                                MainActivity.editor.remove("admin");
                                MainActivity.editor.commit();
                                Log.d("AAA", "user sharedPreferences" + MainActivity.sharedPreferences.getString("username", ""));
                                setintentt();
                            }

                        }else{
                            Toast.makeText(getApplicationContext(), "tài khoản không chính sác", Toast.LENGTH_SHORT).show();
                        }

                    }
                }

                @Override
                public void onFailure(Call<List<User>> call, Throwable t) {
                    Toast.makeText(Login.this, "Xảy ra lỗi vui lòng quay lại sau", Toast.LENGTH_SHORT).show();
                    Log.d("AAA",t.toString());
                }
            });
        }
    }
    private void setintentt(){
        Intent intent=getIntent();
        if(intent.hasExtra("phandanhgia")){
            String msp=intent.getStringExtra("phandanhgia");
            if(!msp.equals("")){
                int mspmoi=Integer.parseInt(msp);
                Log.d("AAA","masanpham"+msp);
                Intent intent1=new Intent(getApplicationContext(),Chitietsanpham.class);
                intent1.putExtra("id",mspmoi);
                startActivity(intent1);
                finish();
            }
        }else{
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }
    }
}
