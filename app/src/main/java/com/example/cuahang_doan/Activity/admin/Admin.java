package com.example.cuahang_doan.Activity.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.cuahang_doan.Activity.DonHangCuaBan;
import com.example.cuahang_doan.Activity.Gioi_Thieu;
import com.example.cuahang_doan.Activity.Login;
import com.example.cuahang_doan.Activity.MainActivity;
import com.example.cuahang_doan.Activity.TinTuc;
import com.example.cuahang_doan.R;

public class Admin extends AppCompatActivity {
    private LinearLayout linnerThontin,linerThongKe,linnerdangxuat,linerdonhang;
    private CardView carsanpham,cardtaikhoan,cardtintuc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        anhxa();
        onclickview();
    }

    private void onclickview() {
        linnerThontin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Gioi_Thieu.class));
            }
        });
        linerThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ThongKe.class));
            }
        });
        linnerdangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
                MainActivity.editor.remove("admin").commit();
            }
        });
        linerdonhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DonHangCuaBan.class));
            }
        });
        carsanpham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),QuanLySanPham.class));
            }
        });
        cardtaikhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),QuanLytaikhoan.class));
            }
        });
        cardtintuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), TinTuc.class));
            }
        });
    }

    private void anhxa() {
        cardtintuc=findViewById(R.id.cardtintuc);
        cardtaikhoan=findViewById(R.id.cardtaikhoan);
        carsanpham=findViewById(R.id.carsanpham);
        linerdonhang=findViewById(R.id.linerdonhang);
        linnerdangxuat=findViewById(R.id.dangxuat);
        linerThongKe=findViewById(R.id.linerThongKe);
        linnerThontin=findViewById(R.id.linnerThontin);
    }
}
