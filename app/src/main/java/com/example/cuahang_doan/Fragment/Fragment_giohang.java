package com.example.cuahang_doan.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.cuahang_doan.Activity.Thanhtoangiohang;
import com.example.cuahang_doan.Adapter.Adapter_Giohang;
import com.example.cuahang_doan.Activity.MainActivity;
import com.example.cuahang_doan.R;
import com.example.cuahang_doan.Services.APIServices;
import com.example.cuahang_doan.Services.DataService;
import com.example.cuahang_doan.model.GioHang;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.cuahang_doan.Activity.MainActivity.mainTablayout;

public class Fragment_giohang extends Fragment {
    private Toolbar toolbargiohang;
    private ListView listgiohang;
    private View view;
    private ArrayList<GioHang>arrayList=new ArrayList<>();
    private TextView txtgiasanphamgiohang;
    private TextView txttrinhtranggiohang;
    private FloatingActionButton floattingactionbutondathang;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_giohang,container,false);
        anhxa();
        actionbar();
        getDatagiohang();
        thanhtoangiohang();
        return view;
    }
    public  void reloaddulieu(){
        getActivity().finish();
        startActivity(getActivity().getIntent());
        getActivity().overridePendingTransition(0,0);
        new Handler().postDelayed(
                new Runnable(){
                    @Override
                    public void run() {
                        mainTablayout.getTabAt(1).select();
                    }
                }, 100);
    }

    private void thanhtoangiohang() {
        floattingactionbutondathang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(arrayList.size()>0) {
                    FragmentManager fragmentManager = getFragmentManager();
                    DialogFragment_Thanhtoangiohang dialogFragment = new DialogFragment_Thanhtoangiohang();
                    dialogFragment.show(fragmentManager, "dialogkhachhangmoi");
                }else{
                    Toast.makeText(getActivity(), "Giỏ hàng trống", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void actionbar() {
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbargiohang);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbargiohang.setNavigationIcon(R.drawable.back);
        toolbargiohang.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               getActivity().finish();
               startActivity(getActivity().getIntent());
               getActivity().overridePendingTransition(0,0);
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        getActivity().getMenuInflater().inflate(R.menu.reload,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.reload){
            reloaddulieu();

        }
        return super.onOptionsItemSelected(item);
    }

    private void getDatagiohang()
    {
        txtgiasanphamgiohang.setText("");
        DataService dataService= APIServices.getService();
        Call<List<GioHang>>callback=dataService.getdataGiohang();
        callback.enqueue(new Callback<List<GioHang>>() {
            @Override
            public void onResponse(Call<List<GioHang>> call, Response<List<GioHang>> response) {
                Log.d("AAA","get data giohang: "+response.toString());
                if(response.isSuccessful()){
                    MainActivity mainActivity= (MainActivity) getActivity();
                    arrayList= (ArrayList<GioHang>) response.body();
                    Adapter_Giohang adapter=new Adapter_Giohang(mainActivity,arrayList);
                    adapter.notifyDataSetChanged();
                    listgiohang.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    setListViewHeightBasedOnChildren(listgiohang);
                    int tong=0;
                    for (int i=0;i<arrayList.size();i++){
                        tong+=arrayList.get(i).getThanhTien();
                    }
                    DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
                    txtgiasanphamgiohang.setText("Thành Tiền: "+decimalFormat.format(tong)+"");
                    txttrinhtranggiohang.setVisibility(View.GONE);
                }else{
                    txttrinhtranggiohang.setVisibility(View.VISIBLE);
                    txttrinhtranggiohang.setText("Giỏ Hàng Trống");
                }
            }

            @Override
            public void onFailure(Call<List<GioHang>> call, Throwable t) {

            }
        });
    }

    private void anhxa() {
        txttrinhtranggiohang=view.findViewById(R.id.txttrinhtranggiohang);
        txtgiasanphamgiohang=view.findViewById(R.id.txtgiasanphamgiohang);
        listgiohang=view.findViewById(R.id.listgiohang);
        toolbargiohang=view.findViewById(R.id.toolbargiohang);
        floattingactionbutondathang=view.findViewById(R.id.floattingactionbutondathang);
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
