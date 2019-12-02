package com.example.cuahang_doan.Activity.admin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.cuahang_doan.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;

public class ThemSanPham extends AppCompatActivity {
    private ImageView img_AddImageviewsProducthai,img_AddImageviewsProductmot,img_PictureProduct;
    private LinearLayout linnerlayouthinhmota;
    private int REQUEST_CODE_IMAGEVIEWMO_TA=123;
    private int REQUEST_CODE_IMAGEVIEW_PRODUCT=456;
    private String string_ImageviewProduct="";
    private TextView txt_InsertTypeProduct,txt_PromotionDay;
    ArrayList<String>arrayListhinhmota=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_san_pham);
        anhxa();
        addhinhmota();
        addhinhanhsanpham();
        loaisanpham();
        ngaykhuyenmai();
        InsertProduct();
    }

    private void InsertProduct() {

    }

    private void ngaykhuyenmai() {
        txt_PromotionDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                final int nam=calendar.get(Calendar.YEAR);
                final int thang=calendar.get(Calendar.MONTH);
                final int ngay=calendar.get(Calendar.DATE);
                DatePickerDialog datePickerDialog=new DatePickerDialog(ThemSanPham.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        txt_PromotionDay.setText(ngay+"/"+thang+"/"+nam);
                    }
                },nam,thang,ngay);
                datePickerDialog.show();
            }
        });
    }


    private void loaisanpham() {
        txt_InsertTypeProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu=new PopupMenu(ThemSanPham.this,txt_InsertTypeProduct);
                popupMenu.getMenuInflater().inflate(R.menu.menuluachonsanphamadmin,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getItemId()==R.id.laptopmacbook){

                        }
                        if(item.getItemId()==R.id.linhkien){

                        }
                        if(item.getItemId()==R.id.tbluutrupk){

                        }
                        if(item.getItemId()==R.id.tbnghenhin){

                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }

    private void addhinhanhsanpham() {
        img_AddImageviewsProductmot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE_IMAGEVIEW_PRODUCT);
            }
        });
    }

    private void addhinhmota() {
        img_AddImageviewsProducthai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE_IMAGEVIEWMO_TA);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==REQUEST_CODE_IMAGEVIEWMO_TA && resultCode==RESULT_OK && data!=null){
            Uri uri=data.getData();
            try {
                ImageView imageView=new ImageView(getApplicationContext());
                LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(200,200);
                layoutParams.setMargins(10,10,10,10);
                imageView.setLayoutParams(layoutParams);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                linnerlayouthinhmota.addView(imageView);

                InputStream inputStream=getContentResolver().openInputStream(uri);
                Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);

                String stringhinh=Tobyarrrayhinh(bitmap);
                arrayListhinhmota.add(Tobyarrrayhinh(bitmap));
               Log.d("AAA","arayhinhmota: "+stringhinh);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
         if(requestCode==REQUEST_CODE_IMAGEVIEW_PRODUCT && resultCode==RESULT_OK && data!=null){
             Uri uri=data.getData();
             try {
                 InputStream inputStream=getContentResolver().openInputStream(uri);
                 Bitmap bitmap=BitmapFactory.decodeStream(inputStream);
                 img_PictureProduct.setImageBitmap(bitmap);
                 string_ImageviewProduct=Tobyarrrayhinh(bitmap);
                 Log.d("AAA","hinhanhsp: "+string_ImageviewProduct);
             } catch (FileNotFoundException e) {
                 e.printStackTrace();
             }
         }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void anhxa() {
        txt_PromotionDay=findViewById(R.id.txt_PromotionDay);
        txt_InsertTypeProduct=findViewById(R.id.txt_InsertTypeProduct);
        img_PictureProduct=findViewById(R.id.img_PictureProduct);
        img_AddImageviewsProductmot=findViewById(R.id.img_AddImageviewsProductmot);
        linnerlayouthinhmota=findViewById(R.id.linnerlayouthinhmota);
        img_AddImageviewsProducthai=findViewById(R.id.img_AddImageviewsProducthai);
    }
    private String Tobyarrrayhinh(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte []array=byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(array,Base64.DEFAULT);
    }
}
