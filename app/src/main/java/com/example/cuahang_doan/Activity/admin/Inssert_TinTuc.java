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

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Inssert_TinTuc extends AppCompatActivity {
    private TinTuc tinTuc;
    private EditText edt_InsertContent,edt_NameNewsInsert;
    private ImageView img_PictureInsert,img_AddInsert;
    private Button btn_Insert;
    private int REQUEST_CODE_IMAGEVIEW=123;
    private Bitmap bitmap;
    private Toolbar toolbar_InsertNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inssert__tin_tuc);
        anhxa();
        add_Imageview();
        insert_OnclickNews();
        Actionbar();
    }

    private void Actionbar() {
        setSupportActionBar(toolbar_InsertNews);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar_InsertNews.setNavigationIcon(R.drawable.back);
        toolbar_InsertNews.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), com.example.cuahang_doan.Activity.TinTuc.class));
                finish();
            }
        });
    }

    private void insert_OnclickNews() {
        btn_Insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert_News();
            }
        });
    }

    private void insert_News() {
        String arrayPicture="";
        if(bitmap!=null) {
             arrayPicture = toArrayString(bitmap);
        }
        if(arrayPicture.equals("")){
            Toast.makeText(this, "Vui lòng chọn hình ảnh sản phẩm", Toast.LENGTH_SHORT).show();
        }else if(edt_InsertContent.getText().toString().equals("")){
            Toast.makeText(this, "Không để trống nội dung bài viết", Toast.LENGTH_SHORT).show();
        }  else if(edt_NameNewsInsert.getText().equals("")){
            Toast.makeText(this, "Không để trống tiêu đề", Toast.LENGTH_SHORT).show();
        } else{
            DataService dataService= APIServices.getService();
            Call<String>callback=dataService.insert_NewsAdmin(arrayPicture,edt_InsertContent.getText().toString(),
                    edt_NameNewsInsert.getText().toString());
            callback.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Log.d("AAA","insert_News: "+response.toString());
                    if(response.isSuccessful()){
                        Toast.makeText(Inssert_TinTuc.this, "thanh cong", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), com.example.cuahang_doan.Activity.TinTuc.class));
                        finish();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                }
            });
        }
    }

    private void add_Imageview() {
        img_AddInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE_IMAGEVIEW);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==REQUEST_CODE_IMAGEVIEW && resultCode==RESULT_OK && data!=null){
            Uri uri=data.getData();
            try {
                InputStream inputStream=getContentResolver().openInputStream(uri);
                bitmap= BitmapFactory.decodeStream(inputStream);
                img_PictureInsert.setImageBitmap(bitmap);
                img_PictureInsert.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    private String toArrayString(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] array=byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(array,Base64.DEFAULT);
    }

    private void anhxa() {
        toolbar_InsertNews=findViewById(R.id.toolbar_InsertNews);
        edt_InsertContent=findViewById(R.id.edt_InsertContent);
        edt_NameNewsInsert=findViewById(R.id.edt_NameNewsInsert);
        img_PictureInsert=findViewById(R.id.img_PictureInsert);
        img_AddInsert=findViewById(R.id.img_AddInsert);
        btn_Insert=findViewById(R.id.btn_Insert);
        Picasso.with(getApplicationContext()).load("http://icons.iconarchive.com/icons/dryicons/aesthetica-2/128/image-add-icon.png").
                into(img_AddInsert);
        Picasso.with(getApplicationContext()).load("https://i1.taimienphi.vn/Images/thumb.gif").
                into(img_PictureInsert);
    }

}
