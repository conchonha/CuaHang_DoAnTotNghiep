package com.example.cuahang_doan.Activity;

import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.Toast;

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
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setanim();
        anhxa();
        login();
    }

    private void login() {
        if(sharedPreferences.getString("username","")!=null &&
                sharedPreferences.getString("password","")!=null){
            edittextusername.setText(sharedPreferences.getString("username",""));
            edittextpassword.setText(sharedPreferences.getString("password",""));
        }
        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dangnhap();
            }
        });

    }

    private void anhxa() {
        sharedPreferences=getSharedPreferences("datalogin",MODE_PRIVATE);
        editor=sharedPreferences.edit();
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
                    if(response!=null){
                        arrayList= (ArrayList<User>) response.body();
                        Toast.makeText(Login.this, "Đăng Nhập Thành Công", Toast.LENGTH_SHORT).show();
                        if(ckeckbox.isChecked() && arrayList!=null){
                            editor.putString("username",edittextusername.getText().toString());
                            editor.putString("password",edittextpassword.getText().toString());
                            editor.putInt("iduser",arrayList.get(0).getId());
                            editor.putString("email",arrayList.get(0).getEmail());
                            editor.putString("sodienthoai",arrayList.get(0).getPhoneNumBer());
                            editor.commit();
                        }
                    }else{
                        Toast.makeText(Login.this, "Sai tài khoản", Toast.LENGTH_SHORT).show();
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
}
