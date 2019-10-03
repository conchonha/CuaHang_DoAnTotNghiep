package com.example.cuahang_doan.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.example.cuahang_doan.Adapter.MainAdapter;
import com.example.cuahang_doan.Fragment.Fragment_timkiem;
import com.example.cuahang_doan.Fragment.Fragment_trangchu;
import com.example.cuahang_doan.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private ViewPager MainViewpager;
    private TabLayout mainTablayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        init();
    }

    private void init() {
        MainAdapter adapter=new MainAdapter(getSupportFragmentManager());
        adapter.addFragment(new Fragment_trangchu(MainActivity.this),"Trang Chủ");
        adapter.addFragment(new Fragment_timkiem(),"Tìm Kiếm");
        MainViewpager.setAdapter(adapter);
        mainTablayout.setupWithViewPager(MainViewpager);
        mainTablayout.getTabAt(0).setIcon(R.drawable.icontrangchu);
        mainTablayout.getTabAt(1).setIcon(R.drawable.iconsearch);
    }

    private void anhxa() {
        MainViewpager=findViewById(R.id.MainViewpager);
        mainTablayout=findViewById(R.id.mainTablayout);
    }


}