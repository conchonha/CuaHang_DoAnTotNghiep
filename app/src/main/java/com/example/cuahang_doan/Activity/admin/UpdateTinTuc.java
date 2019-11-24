package com.example.cuahang_doan.Activity.admin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cuahang_doan.R;
import com.example.cuahang_doan.Services.APIServices;
import com.example.cuahang_doan.Services.DataService;
import com.example.cuahang_doan.model.TinTuc;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Request;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateTinTuc extends AppCompatActivity {
    private TinTuc tinTuc;
    private EditText edt_UpdateContent,edt_NameNewsUpdate;
    private ImageView img_PictureContentUpdate,img_AddPictureUpdate;
    private Button btn_UpdateNews;
    private int REQUEST_CODE_IMAGEVIEW=123;
    private Bitmap bitmap;
    private Toolbar toolbar_UpdateNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_tin_tuc);
        anhxa();
        actionbar();
        Intent intent=getIntent();
        if(intent!=null){
            if(intent.hasExtra("News")){
                tinTuc= (TinTuc) intent.getSerializableExtra("News");
                add_ContentNewsUpdate(tinTuc);
            }
        }
    }

    private void actionbar() {
        setSupportActionBar(toolbar_UpdateNews);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar_UpdateNews.setNavigationIcon(R.drawable.back);
        toolbar_UpdateNews.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void add_ContentNewsUpdate(final TinTuc tinTuc) {
//        Picasso.with(getApplicationContext()).load("http://www.gibc.com.vn/public/img/default/no-image-icon.jpg").into(img_PictureContentUpdate);
        Picasso.with(getApplicationContext()).load("https://www.sanadig.com/img/add.png").into(img_AddPictureUpdate);
        edt_NameNewsUpdate.setText(tinTuc.getTenbaiviet());
        edt_UpdateContent.setText(tinTuc.getNoidung());
        try {
            String urlLink="https://i1.taimienphi.vn/Images/thumb.gif";
            if(tinTuc.getHinhbaiviet().endsWith("news.jpg")){
                urlLink=APIServices.urlhinh+tinTuc.getHinhbaiviet();
            }else{
                urlLink=tinTuc.getHinhbaiviet();
            }
            URL url=new URL(urlLink);
            InputStream inputStream=url.openConnection().getInputStream();
             bitmap= BitmapFactory.decodeStream(inputStream);
            img_PictureContentUpdate.setImageBitmap(bitmap);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        img_AddPictureUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE_IMAGEVIEW);
            }
        });
        btn_UpdateNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string_Picture=imagetostring(bitmap);
                if(string_Picture!=null){
                    DataService dataService= APIServices.getService();
                    Call<String>callback=dataService.update_NewsAdmin(string_Picture,edt_UpdateContent.getText().toString(),
                            edt_NameNewsUpdate.getText().toString(), tinTuc.getId()+"");
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            Log.d("AAA","update_NewsAdmin: "+response.toString());
                            if(response.isSuccessful()){
                                Toast.makeText(UpdateTinTuc.this, "thanh cong", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(UpdateTinTuc.this, com.example.cuahang_doan.Activity.TinTuc.class));
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==REQUEST_CODE_IMAGEVIEW && resultCode==RESULT_OK && data!=null){
            Uri uri=data.getData();
            try {
                InputStream inputStream=getContentResolver().openInputStream(uri);
                bitmap=BitmapFactory.decodeStream(inputStream);
                img_PictureContentUpdate.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    private String imagetostring(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[]hinhanh=byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(hinhanh,Base64.DEFAULT);
    }


    private void anhxa() {
        toolbar_UpdateNews=findViewById(R.id.toolbar_UpdateNews);
        edt_UpdateContent=findViewById(R.id.edt_UpdateContent);
        edt_NameNewsUpdate=findViewById(R.id.edt_NameNewsUpdate);
        img_PictureContentUpdate=findViewById(R.id.img_PictureContentUpdate);
        img_AddPictureUpdate=findViewById(R.id.img_AddPictureUpdate);
        btn_UpdateNews=findViewById(R.id.btn_UpdateNews);
    }
}
