package com.example.cuahang_doan.Fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.cuahang_doan.Activity.MainActivity;
import com.example.cuahang_doan.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class Fragment_trangchu extends Fragment {
    private Toolbar toolbarlayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private DrawerLayout drawerlayout;
    private View view;
    private MainActivity context;

    public Fragment_trangchu(MainActivity context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_trangchu,container,false);
        anhxa();
        actionbar();
        return view;
    }
    private void actionbar() {
        //Expanded set màu mở rộng
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.colorwhile));
        //set màu thu hẹp
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        context.setSupportActionBar(toolbarlayout);
        context.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarlayout.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbarlayout.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerlayout.openDrawer(GravityCompat.START);
            }
        });
    }
    private void anhxa() {
        drawerlayout=view.findViewById(R.id.drawerlayout);
        collapsingToolbarLayout=view.findViewById(R.id.collapsingToolbarLayout);
        toolbarlayout =view.findViewById(R.id.toolbarlayout);
    }
}
