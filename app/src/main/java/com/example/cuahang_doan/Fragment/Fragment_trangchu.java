package com.example.cuahang_doan.Fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.cuahang_doan.Activity.MainActivity;
import com.example.cuahang_doan.Adapter.DanhmucAdapter;
import com.example.cuahang_doan.R;
import com.example.cuahang_doan.Services.APIServices;
import com.example.cuahang_doan.Services.DataService;
import com.example.cuahang_doan.model.DanhMuc;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_trangchu extends Fragment {
    private Toolbar toolbarlayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private DrawerLayout drawerlayout;
    private View view;
    private MainActivity context;
    private ListView listviewDanhmuc;

    public Fragment_trangchu(MainActivity context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_trangchu,container,false);
        anhxa();
        actionbar();
        getdataDanhmuc();
        return view;
    }

    private void getdataDanhmuc() {
        DataService dataService= APIServices.getService();
        Call<List<DanhMuc>>callback=dataService.getdataDanhmuc();
        callback.enqueue(new Callback<List<DanhMuc>>() {
            @Override
            public void onResponse(Call<List<DanhMuc>> call, Response<List<DanhMuc>> response) {
                Log.d("AAA","ListView_DanhMuc"+response.toString());
                if(response!=null){
                    ArrayList<DanhMuc>arrayList= (ArrayList<DanhMuc>) response.body();
                    DanhmucAdapter adapter=new DanhmucAdapter(getActivity(),R.layout.layout_danhmuc,arrayList);
                    listviewDanhmuc.setAdapter(adapter);
                    setListViewHeightBasedOnChildren(listviewDanhmuc);
                }
            }

            @Override
            public void onFailure(Call<List<DanhMuc>> call, Throwable t) {
                Log.d("AAA","ListView_DanhMuc"+t.toString());
            }
        });
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
        listviewDanhmuc=view.findViewById(R.id.listviewDanhmuc);
        drawerlayout=view.findViewById(R.id.drawerlayout);
        collapsingToolbarLayout=view.findViewById(R.id.collapsingToolbarLayout);
        toolbarlayout =view.findViewById(R.id.toolbarlayout);
    }
    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);

            if(listItem != null){
                // This next line is needed before you call measure or else you won't get measured height at all. The listitem needs to be drawn first to know the height.
                listItem.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                totalHeight += listItem.getMeasuredHeight();

            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}
