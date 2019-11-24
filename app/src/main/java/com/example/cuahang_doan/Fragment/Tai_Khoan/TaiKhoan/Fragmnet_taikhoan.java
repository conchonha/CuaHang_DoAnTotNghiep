package com.example.cuahang_doan.Fragment.Tai_Khoan.TaiKhoan;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.cuahang_doan.Activity.DonHangCuaBan;
import com.example.cuahang_doan.Activity.Login;
import com.example.cuahang_doan.Activity.MainActivity;
import com.example.cuahang_doan.Activity.Nhanxetcuaban;
import com.example.cuahang_doan.Activity.QuanLyTaiKhoan;
import com.example.cuahang_doan.R;
import com.example.cuahang_doan.Services.APIServices;
import com.example.cuahang_doan.Services.DataService;
import com.example.cuahang_doan.model.User;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class Fragmnet_taikhoan extends Fragment {
    private View view;
    private Toolbar toolbartaikhoan;
    private TextView txttenuser;
    private RelativeLayout relivelayoutquanlytaikhoan,relivelayoutdonhangcuatoi,relivelayoutnhanxetcuatoi,
            relivelayoutdonhangdoitra,relivelayoutdangxuat;
    private ImageView imghinhusser,imguploadphotouser;
    private int Requestcode=123;
    private Handler handler;
    private Runnable runnable;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_taikhoan,container,false);
        anhxa();
        if(MainActivity.sharedPreferences.getInt("iduser",0)==0){
            startActivity(new Intent(getActivity(), Login.class));
        }else{
            sukienclickview();
        }
        return view;
    }
    private void sethinhusser(){
        if(!MainActivity.sharedPreferences.getString("hinh","").equals("")){
            Picasso.with(getActivity()).load(MainActivity.sharedPreferences.getString("hinh",""))
                    .into(imghinhusser);
            Log.d("AAA","hinhuser: "+MainActivity.sharedPreferences.getString("hinh",""));
        }
    }

    private void sukienclickview() {
        sethinhusser();
        imguploadphotouser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,Requestcode);
            }
        });
        relivelayoutdangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),Login.class));
            }
        });
        relivelayoutdonhangdoitra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Bạn Chưa Có Đơn Hàng Đổi Trả Nào", Toast.LENGTH_SHORT).show();
            }
        });
        relivelayoutnhanxetcuatoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Nhanxetcuaban.class));
            }
        });
        relivelayoutdonhangcuatoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), DonHangCuaBan.class));
            }
        });
        relivelayoutquanlytaikhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),QuanLyTaiKhoan.class));
            }
        });
        txttenuser.setText(MainActivity.sharedPreferences.getString("username","chưa có tài khoản"));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==Requestcode && resultCode == RESULT_OK && data!=null){
            Uri uri=data.getData();
            try {
                InputStream inputStream=getActivity().getContentResolver().openInputStream(uri);
                Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
                imghinhusser.setImageBitmap(bitmap);
                String bytehinh=imagetostring(bitmap);
                updatehinh(bytehinh);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void updatehinh(String bytehinh) {
        DataService dataService= APIServices.getService();
        Call<List<User>>callback=dataService.updatephotouser(bytehinh,
                MainActivity.sharedPreferences.getInt("iduser",0)+"");
        callback.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                Log.d("AAA","Updatehinhuser: "+response.toString());

                if(response.isSuccessful()){
                    ArrayList<User>arrayList= (ArrayList<User>) response.body();
                    MainActivity.editor.putString("hinh", APIServices.urlhinh+MainActivity.sharedPreferences
                    .getInt("iduser",0)+".jpg");
                    MainActivity.editor.commit();
                    getActivity().finish();
                    startActivity(getActivity().getIntent());
                    handler=new Handler();
                    runnable=new Runnable() {
                        @Override
                        public void run() {
                            MainActivity.mainTablayout.getTabAt(2).select();
                        }
                    };
                    handler.postDelayed(runnable,100);

                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
    }

    private String imagetostring(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[]hinhanh=byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(hinhanh,Base64.DEFAULT);
    }

    private void anhxa() {
        imguploadphotouser=view.findViewById(R.id.imguploadphotouser);
        imghinhusser=view.findViewById(R.id.imghinhusser);
        relivelayoutdangxuat=view.findViewById(R.id.relivelayoutdangxuat);
        relivelayoutdonhangdoitra=view.findViewById(R.id.relivelayoutdonhangdoitra);
        relivelayoutnhanxetcuatoi=view.findViewById(R.id.relivelayoutnhanxetcuatoi);
        relivelayoutdonhangcuatoi=view.findViewById(R.id.relivelayoutdonhangcuatoi);
        relivelayoutquanlytaikhoan=view.findViewById(R.id.relivelayoutquanlytaikhoan);
        txttenuser=view.findViewById(R.id.txttenuser);
        toolbartaikhoan=view.findViewById(R.id.toolbartaikhoan);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbartaikhoan);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbartaikhoan.setNavigationIcon(R.drawable.back);
        toolbartaikhoan.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
                startActivity(getActivity().getIntent());
                getActivity().overridePendingTransition(0,0);
            }
        });
    }
}
