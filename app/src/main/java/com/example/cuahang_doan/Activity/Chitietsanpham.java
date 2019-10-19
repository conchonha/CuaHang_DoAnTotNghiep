package com.example.cuahang_doan.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cuahang_doan.Adapter.Adapter_SlideChitietsanpham;
import com.example.cuahang_doan.Fragment.Fragment_Danhgiasanpham;
import com.example.cuahang_doan.R;
import com.example.cuahang_doan.Services.APIServices;
import com.example.cuahang_doan.Services.DataService;
import com.example.cuahang_doan.model.GetdataSanphammoinhat;
import com.example.cuahang_doan.model.SanPham;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rd.PageIndicatorView;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Chitietsanpham extends AppCompatActivity {
    private ViewPager ChitietsanphamViewpaget;
    private TextView txttensanphamchitiet,txtthongsokythuat,txtmotasanphamchitiet,
            txtgiasanphamgoc,txtgiasanphamsaukhigiam,txtngaydang,txtngaykhuyenmai,txttrinhtrang;
    public  ArrayList<SanPham>arrayList=new ArrayList<>();
    public  int id;
    private CollapsingToolbarLayout collapsingtoolbar;
    private Toolbar toolbarchitietsp;
    private FloatingActionButton floattingactionbuton;
    private PageIndicatorView PageIndicatorview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietsanpham);
        actionbar();
        anhxa();
        init();
    }

    private void addFragment(String masp) {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        Fragment_Danhgiasanpham fragment=new Fragment_Danhgiasanpham();
        Bundle bundle=new Bundle();
        bundle.putString("masp",masp);
        fragment.setArguments(bundle);
        fragmentTransaction.add(R.id.linnerlaout,fragment);
        fragmentTransaction.commit();
    }


    private void actionbar() {
        toolbarchitietsp=findViewById(R.id.toolbarchitietsp);
        setSupportActionBar(toolbarchitietsp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarchitietsp.setNavigationIcon(R.drawable.back);
        toolbarchitietsp.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void init() {
        Intent intent=getIntent();
        if(intent!=null){
            if(intent.hasExtra("id")){
                id=intent.getIntExtra("id",0);
                Log.d("AAA",id+"Ma san pham chi tiet san pham");
                getdatasanpham(id);
                addFragment(id+"");
            }
        }
    }

    private void anhxa() {
        txttrinhtrang=findViewById(R.id.txttrinhtrang);
        txtngaykhuyenmai=findViewById(R.id.txtngaykhuyenmai);
        txtngaydang=findViewById(R.id.txtngaydang);
        txtgiasanphamsaukhigiam=findViewById(R.id.txtgiasanphamsaukhigiam);
        txtgiasanphamgoc=findViewById(R.id.txtgiasanphamgoc);
        floattingactionbuton=findViewById(R.id.floattingactionbuton);
        ChitietsanphamViewpaget=findViewById(R.id.ChitietsanphamViewpaget);
        txttensanphamchitiet=findViewById(R.id.txttensanphamchitiet);
        txtthongsokythuat=findViewById(R.id.txtthongsokythuat);
        txtmotasanphamchitiet=findViewById(R.id.txtmotasanphamchitiet);
        collapsingtoolbar=findViewById(R.id.collapsingtoolbar);
        //Expanded set màu mở rộng
        collapsingtoolbar.setExpandedTitleColor(getResources().getColor(R.color.colorwhile));
        //set màu thu hẹp
        collapsingtoolbar.setCollapsedTitleTextColor(Color.WHITE);
        PageIndicatorview=findViewById(R.id.PageIndicatorview);
    }


    private void getdatasanpham(int id){
        DataService dataService= APIServices.getService();
        Call<List<SanPham>>callback=dataService.postSanphamchitiet(id+"");
        callback.enqueue(new Callback<List<SanPham>>() {
            @Override
            public void onResponse(Call<List<SanPham>> call, Response<List<SanPham>> response) {
                Log.d("AAA","chitietsanpham"+response.toString());
                if(response.isSuccessful()){
                    arrayList= (ArrayList<SanPham>) response.body();
                    if(arrayList!=null){
                        txtmotasanphamchitiet.setText(arrayList.get(0).getMota());
                        txttensanphamchitiet.setText(arrayList.get(0).getTenSanPham());
                        txtthongsokythuat.setText(arrayList.get(0).getThongSoKyThuat());
                        String[]arrayhinh=arrayList.get(0).getHinhMoTa().split("@");
                        Adapter_SlideChitietsanpham adapter1=new Adapter_SlideChitietsanpham(arrayhinh,Chitietsanpham.this);
                        ChitietsanphamViewpaget.setAdapter(adapter1);
                        PageIndicatorview.setViewPager(ChitietsanphamViewpaget);
                        SanPham sanpham=arrayList.get(0);
                        Tinhtrangsanpham(sanpham);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<SanPham>> call, Throwable t) {
                Toast.makeText(Chitietsanpham.this, "Đã hết hạn dữ liệu vui lòng thử lại sau", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @SuppressLint("ResourceType")
    private void Tinhtrangsanpham(SanPham sanpham) {
        int giasanphamsaukhuyenmai=0;
        Calendar calendar=Calendar.getInstance();
        DecimalFormat simpleDateFormat=new DecimalFormat("###,###,###");
        SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy");
        txtngaydang.setText(sanpham.getNgayDang());
        if(sanpham.getSoLuong()>0){
            txttrinhtrang.setText("Còn Hàng");
        }else{
            txttrinhtrang.setText("Hết Hàng");
        }
        if(sanpham.getGiamGia()>0 && !sanpham.getNgayKhuyenMai().equals("")){
            Date ngaykhuyenmai= null;
            Date ngayhientai=null;
            try {
                ngaykhuyenmai = format.parse(sanpham.getNgayKhuyenMai()+"");
                ngayhientai=format.parse(calendar.get(Calendar.DATE)+"-"+calendar.get(Calendar.MONTH)+"-"+calendar.get(Calendar.YEAR));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if(ngaykhuyenmai.compareTo(ngayhientai)>0){
                Log.d("AAA","ngay"+ngayhientai);
                txtgiasanphamgoc.setPaintFlags(txtgiasanphamgoc.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG );
                txtgiasanphamgoc.setText(simpleDateFormat.format(sanpham.getGia())+"");
                float giagiam=(float) (100-sanpham.getGiamGia())/100;
                float giaspsaukhuyenmai=(float)giagiam*sanpham.getGia();
                 giasanphamsaukhuyenmai=(int)giaspsaukhuyenmai;
                Log.d("AAA",giaspsaukhuyenmai+"");
                txtgiasanphamsaukhigiam.setText(simpleDateFormat.format(giasanphamsaukhuyenmai)+"Đ");
                txtngaykhuyenmai.setText(sanpham.getNgayKhuyenMai());
            }

        }else{
             giasanphamsaukhuyenmai=sanpham.getGia();
            txtgiasanphamsaukhigiam.setText(simpleDateFormat.format(giasanphamsaukhuyenmai)+"Đ");
            txtngaykhuyenmai.setText("");
            txtgiasanphamgoc.setText("");
        }

        themvaogiohang(arrayList.get(0).getId(),giasanphamsaukhuyenmai,arrayList.get(0).getHinhAnhSanPham(),
                arrayList.get(0).getTenSanPham());
    }

    private void themvaogiohang(final Integer id, final int giasanphamsaukhuyenmai, final String hinhAnhSanPham, final String tenSanPham) {
            floattingactionbuton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(MainActivity.sharedPreferences.getString("username","").equals("") &&
                            MainActivity.sharedPreferences.getInt("iduser",0)==0){
                        Toast.makeText(Chitietsanpham.this, "co click", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(Chitietsanpham.this,Login.class);
                            intent.putExtra("phandanhgia",id+"");
                            startActivity(intent);
                            finish();
                    }else {
                        int iduser = MainActivity.sharedPreferences.getInt("iduser", 0);
                        int idsanpham = id;
                        int giasanpham = giasanphamsaukhuyenmai;
                        String hinhsanpham = hinhAnhSanPham;
                        String tensanpham = tenSanPham;
                        Log.d("AAA","id_user: "+iduser);
                        Log.d("AAA","id_sanpham: "+idsanpham);
                        Log.d("AAA","hinh san pham: "+hinhsanpham);
                        Log.d("AAA","tensanpham"+tenSanPham);
                        Log.d("AAA","gia san pham: "+giasanpham);
                       postgiohang(iduser,idsanpham,giasanpham,hinhsanpham,tensanpham);
                    }

                }
            });
    }

    private void postgiohang(int iduser, int idsanpham, int giasanpham, String hinhsanpham, String tensanpham) {
        DataService dataService=APIServices.getService();
        Call<String>callback=dataService.postGiohang(iduser,idsanpham,giasanpham);
        callback.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d("AAA","post gio hang: "+response.toString());
                if(response.isSuccessful()){
                    String mess=response.body();
                    Log.d("AAA","Messsage: "+mess);
                    Toast.makeText(Chitietsanpham.this, "San pham da duoc them vao gio hang", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Chitietsanpham.this, "Tam thoi khong them vao dc vui long thu lai sau", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("AAA","Faid gio hang: "+t.toString());
            }
        });
    }


}
