package com.example.cuahang_doan.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.cuahang_doan.Adapter.MainAdapter;
import com.example.cuahang_doan.Fragment.Fragment_Chuanhanxet;
import com.example.cuahang_doan.Fragment.Fragment_Danhanxet;
import com.example.cuahang_doan.R;
import com.google.android.material.tabs.TabLayout;

public class Nhanxetcuaban extends AppCompatActivity {
    private ViewPager viewpagernhanxetcuaban;
    private TabLayout tablayoutnhanxetcuaban;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhanxetcuaban);
        anhxa();
    }

    private void anhxa() {
        viewpagernhanxetcuaban=findViewById(R.id.viewpagernhanxetcuaban);
        tablayoutnhanxetcuaban=findViewById(R.id.tablayoutnhanxetcuaban);
        MainAdapter mainAdapter=new MainAdapter(getSupportFragmentManager());
        mainAdapter.addFragment(new Fragment_Chuanhanxet(),"Lịch Sử");
        mainAdapter.addFragment(new Fragment_Danhanxet(),"Nhận xét");
        viewpagernhanxetcuaban.setAdapter(mainAdapter);
        tablayoutnhanxetcuaban.setupWithViewPager(viewpagernhanxetcuaban);
        tablayoutnhanxetcuaban.getTabAt(0).setIcon(R.drawable.ratingdanhanxet);
        tablayoutnhanxetcuaban.getTabAt(1).setIcon(R.drawable.ratingchuanhanxet);
    }
}
