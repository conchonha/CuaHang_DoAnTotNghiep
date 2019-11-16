package com.example.cuahang_doan.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.cuahang_doan.Activity.admin.Admin;
import com.example.cuahang_doan.Adapter.MainAdapter;
import com.example.cuahang_doan.Fragment.Fragment_Choxetduyet;
import com.example.cuahang_doan.Fragment.Fragment_DaHuy;
import com.example.cuahang_doan.Fragment.Fragment_Dagiaohang;
import com.example.cuahang_doan.Fragment.Fragment_Dangvanchuyen;
import com.example.cuahang_doan.R;
import com.google.android.material.tabs.TabLayout;

public class DonHangCuaBan extends AppCompatActivity {
    private Toolbar toolbardonhangcuaban;
    private ViewPager viewpagerdonhangcuaban;
    private TabLayout Donhangcuabantablayout;
    private Toolbar Toobardonhangcuaban;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_don_hang_cua_ban);
        anhxa();
        init();
    }

    private void anhxa() {
        Toobardonhangcuaban=findViewById(R.id.Toobardonhangcuaban);
        setSupportActionBar(Toobardonhangcuaban);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Toobardonhangcuaban.setNavigationIcon(R.drawable.back);
        Toobardonhangcuaban.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(!MainActivity.sharedPreferences.getString("admin","").equals("")){
                   startActivity(new Intent(getApplicationContext(), Admin.class));
                   finish();
               }else{
                   finish();
               }

            }
        });
        viewpagerdonhangcuaban=findViewById(R.id.viewpagerdonhangcuaban);
        Donhangcuabantablayout=findViewById(R.id.Donhangcuabantablayout);
    }

    private void init() {
        MainAdapter mainAdapter=new MainAdapter(getSupportFragmentManager());
        mainAdapter.addFragment(new Fragment_Choxetduyet(),"Chờ Duyệt");
        mainAdapter.addFragment(new Fragment_Dangvanchuyen(),"Đang Giao");
        mainAdapter.addFragment(new Fragment_Dagiaohang(),"Hoàn Thành");
        mainAdapter.addFragment(new Fragment_DaHuy(),"Đã Hủy");
        viewpagerdonhangcuaban.setAdapter(mainAdapter);

        Donhangcuabantablayout.setupWithViewPager(viewpagerdonhangcuaban);
        Donhangcuabantablayout.getTabAt(0).setIcon(R.drawable.choduyet);
        Donhangcuabantablayout.getTabAt(1).setIcon(R.drawable.vanchuyen);
        Donhangcuabantablayout.getTabAt(2).setIcon(R.drawable.hoanthanh);
        Donhangcuabantablayout.getTabAt(3).setIcon(R.drawable.delete);
    }
}
