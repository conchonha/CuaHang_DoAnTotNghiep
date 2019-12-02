package com.example.cuahang_doan.Activity.admin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cuahang_doan.R;
import com.example.cuahang_doan.Services.APIServices;
import com.example.cuahang_doan.Services.DataService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThemSanPham extends AppCompatActivity {
    private ImageView img_AddImageviewsProducthai,img_AddImageviewsProductmot,img_PictureProduct;
    private LinearLayout linnerlayouthinhmota;
    private int REQUEST_CODE_IMAGEVIEWMO_TA=123;
    private int REQUEST_CODE_IMAGEVIEW_PRODUCT=456;
    private String hinhanhsanpham="";
    private int idloai=0;
    private String HangSanXuat="";
    private TextView txt_InsertTypeProduct,txt_PromotionDay,txt_TheFirm;
    private EditText edt_InsertDescription,edt_InsertProduct,edt_InsertNameProduct,txt_InsertPriceProduct,
            txt_NumberOfProduct,txt_PromotionPriceProduct;
    private Button btn_InsertProduct;
    private JSONArray jsonArray;
    private Toolbar toolbar_UpdateProduct;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_san_pham);
        anhxa();
        addhinhmota();
        addhinhanhsanpham();
        loaisanpham();
        ngaykhuyenmai();
        hangsanxuat();
       onclickinssert();
    }

    private void onclickinssert() {
        btn_InsertProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!hinhanhsanpham.equals("") && jsonArray.length()>0){
                    InsertProduct();
                }else{
                    Toast.makeText(ThemSanPham.this, "Vui lòng điền đầy đủ thông tin ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void hangsanxuat() {
        txt_TheFirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu=new PopupMenu(getApplicationContext(),txt_TheFirm);
                popupMenu.getMenuInflater().inflate(R.menu.menuhangsanxuat,popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.macbook:
                                HangSanXuat="Macbook";
                                txt_TheFirm.setText("Macbook");
                                break;
                            case R.id.hp:
                                HangSanXuat="HP";
                                txt_TheFirm.setText("HP");
                                break;
                            case R.id.asus:
                                HangSanXuat="Asus";
                                txt_TheFirm.setText("Asus");
                                break;
                            case R.id.dell:
                                HangSanXuat="Dell";
                                txt_TheFirm.setText("Dell");
                                break;
                            case R.id.lenovo:
                                HangSanXuat="Lenovo";
                                txt_TheFirm.setText("Lenovo");
                                break;
                            case R.id.acer:
                                HangSanXuat="Acer";
                                txt_TheFirm.setText("Acer");
                                break;
                            case R.id.masstel:
                                HangSanXuat="Masstel";
                                txt_TheFirm.setText("Masstel");
                                break;
                            case R.id.samsung:
                                HangSanXuat="Samsung";
                                txt_TheFirm.setText("Samsung");
                                break;
                            case R.id.sony:
                                HangSanXuat="Sony";
                                txt_TheFirm.setText("Sony");
                            case R.id.tosiba:
                                HangSanXuat="Toshiba";
                                txt_TheFirm.setText("Toshiba");
                           break;
                        }
                        return false;
                    }
                });
            }
        });
    }

    private void InsertProduct() {
        if(edt_InsertDescription.getText().toString().equals("") || edt_InsertProduct.getText().toString().equals("")
        || edt_InsertNameProduct.getText().toString().equals("") || txt_InsertPriceProduct.getText().toString().equals("")
         ){
            Toast.makeText(this, "Không được để trống dữ liệu", Toast.LENGTH_SHORT).show();
        }else{
            Calendar calendar=Calendar.getInstance();
            int ngay=calendar.get(Calendar.DATE);
            int thang=calendar.get(Calendar.MONTH);
            int nam=calendar.get(Calendar.YEAR);
            String ngaykhuyenmai="";
            String khuyenmai="";
            String ngayhientai=ngay+"-"+thang+"-"+nam;
            String mota=edt_InsertDescription.getText().toString();
            String thongsokythuat=edt_InsertProduct.getText().toString();
            String tensp=edt_InsertNameProduct.getText().toString();
            String gia=txt_InsertPriceProduct.getText().toString();
            String soluong=txt_NumberOfProduct.getText().toString();
            if (!txt_PromotionDay.getText().toString().equals("")) {
                if(!txt_PromotionPriceProduct.getText().toString().equals("")){
                     ngaykhuyenmai=txt_PromotionDay.getText().toString();
                     khuyenmai=txt_PromotionPriceProduct.getText().toString();
                }
            }
            DataService dataService= APIServices.getService();
            Call<String>callback=dataService.insert_ProductAdmin(mota,thongsokythuat,tensp,gia,
                    soluong,ngaykhuyenmai,khuyenmai,idloai+"",ngayhientai,HangSanXuat,
                    hinhanhsanpham,jsonArray);
            callback.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Log.d("AAA","insertProductAdmin: "+response.toString());
                    if(response.isSuccessful()){
                        Toast.makeText(ThemSanPham.this, "Thành Công", Toast.LENGTH_SHORT).show();
                        Log.d("AAA","Insert Product: "+response.body());
                        finish();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                }
            });


        }

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
                        txt_PromotionDay.setText(dayOfMonth+"-"+month+1+"-"+year);
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
                            txt_InsertTypeProduct.setText("LapTop-Macbook");
                            idloai=1;
                        }
                        if(item.getItemId()==R.id.linhkien){
                           idloai=2;
                            txt_InsertTypeProduct.setText("Linh Kiện LapTop");
                        }
                        if(item.getItemId()==R.id.tbluutrupk){
                            txt_InsertTypeProduct.setText("Lưu Trữ - Phụ Kiện");
                            idloai=3;
                        }
                        if(item.getItemId()==R.id.tbnghenhin){
                            txt_InsertTypeProduct.setText("TB Nghe - Nhìn");
                            idloai=4;
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

                if(jsonArray!=null){

                }else{
                    jsonArray=new JSONArray();
                }
                JSONObject jsonObject=new JSONObject();

                String stringhinh="";
                stringhinh=Tobyarrrayhinh(bitmap);
                jsonObject.put("hinhmota",stringhinh);
                jsonArray.put(jsonObject);
                stringhinh="";

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
         if(requestCode==REQUEST_CODE_IMAGEVIEW_PRODUCT && resultCode==RESULT_OK && data!=null){
             Uri uri=data.getData();
             try {
                 InputStream inputStream=getContentResolver().openInputStream(uri);
                 Bitmap bitmap=BitmapFactory.decodeStream(inputStream);
                 img_PictureProduct.setImageBitmap(bitmap);
                 hinhanhsanpham=Tobyarrrayhinh(bitmap);

                // Log.d("AAA","hinhanhsp: "+hinhanhsanpham);
             } catch (FileNotFoundException e) {
                 e.printStackTrace();
             }
         }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void anhxa() {
        btn_InsertProduct=findViewById(R.id.btn_InsertProduct);
        txt_TheFirm=findViewById(R.id.txt_TheFirm);
        txt_PromotionPriceProduct=findViewById(R.id.txt_PromotionPriceProduct);
        txt_NumberOfProduct=findViewById(R.id.txt_NumberOfProduct);
        txt_InsertPriceProduct=findViewById(R.id.txt_InsertPriceProduct);
        edt_InsertNameProduct=findViewById(R.id.edt_InsertNameProduct);
        edt_InsertProduct=findViewById(R.id.edt_InsertProduct);
        edt_InsertDescription=findViewById(R.id.edt_InsertDescription);
        txt_PromotionDay=findViewById(R.id.txt_PromotionDay);
        txt_InsertTypeProduct=findViewById(R.id.txt_InsertTypeProduct);
        img_PictureProduct=findViewById(R.id.img_PictureProduct);
        img_AddImageviewsProductmot=findViewById(R.id.img_AddImageviewsProductmot);
        linnerlayouthinhmota=findViewById(R.id.linnerlayouthinhmota);
        img_AddImageviewsProducthai=findViewById(R.id.img_AddImageviewsProducthai);
        toolbar_UpdateProduct=findViewById(R.id.toolbar_UpdateProduct);
        setSupportActionBar(toolbar_UpdateProduct);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar_UpdateProduct.setNavigationIcon(R.drawable.back);
        toolbar_UpdateProduct.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private String Tobyarrrayhinh(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte []array=byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(array,Base64.DEFAULT);
    }
}
