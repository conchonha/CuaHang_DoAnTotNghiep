package com.example.cuahang_doan.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.example.cuahang_doan.Activity.admin.Admin;
import com.example.cuahang_doan.Activity.nhanvien.NhanVien;
import com.example.cuahang_doan.Adapter.MainAdapter;
import com.example.cuahang_doan.Fragment.Gio_Hang.Fragment_giohang;
import com.example.cuahang_doan.Fragment.Tim_Kiem.Fragment_timkiem;
import com.example.cuahang_doan.Fragment.TrangChu.Fragment_trangchu;
import com.example.cuahang_doan.Fragment.Tai_Khoan.TaiKhoan.Fragmnet_taikhoan;
import com.example.cuahang_doan.R;
import com.google.android.material.tabs.TabLayout;



public class MainActivity extends AppCompatActivity {
    public static ViewPager MainViewpager;
    public static TabLayout mainTablayout;
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        if(!sharedPreferences.getString("admin","").equals("")){
            startActivity(new Intent(getApplicationContext(), Admin.class));
        }else if(!sharedPreferences.getString("nhanvien","").equals("")){
            startActivity(new Intent(getApplicationContext(), NhanVien.class));
        }else{

        }
        init();

    }

    private void init() {
        MainAdapter adapter=new MainAdapter(getSupportFragmentManager());
        adapter.addFragment(new Fragment_trangchu(MainActivity.this),"Cửa Hàng");
        adapter.addFragment(new Fragment_giohang(),"Giỏ Hàng");
        adapter.addFragment(new Fragmnet_taikhoan(),"Tài Khoản");
        adapter.addFragment(new Fragment_timkiem(),"Tìm Kiếm");
        MainViewpager.setAdapter(adapter);
        mainTablayout.setupWithViewPager(MainViewpager);
        mainTablayout.getTabAt(0).setIcon(R.drawable.icontrangchu);
        mainTablayout.getTabAt(1).setIcon(R.drawable.cart32);
        mainTablayout.getTabAt(2).setIcon(R.drawable.user);
        mainTablayout.getTabAt(3).setIcon(R.drawable.iconsearch);
        MainViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position==1 || position==2 || position==3){
                    mainTablayout.setVisibility(View.GONE);
                }else{
                    mainTablayout.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void anhxa() {
        sharedPreferences=getSharedPreferences("datalogin",MODE_PRIVATE);
        editor=sharedPreferences.edit();
        MainViewpager=findViewById(R.id.MainViewpager);
        mainTablayout=findViewById(R.id.mainTablayout);
    }
    public  void reloaddulieu(){
        finish();
        startActivity(getIntent());
        overridePendingTransition(0,0);
        new Handler().postDelayed(
                new Runnable(){
                    @Override
                    public void run() {
                            mainTablayout.getTabAt(1).select();
                    }
                }, 100);
    }


}
