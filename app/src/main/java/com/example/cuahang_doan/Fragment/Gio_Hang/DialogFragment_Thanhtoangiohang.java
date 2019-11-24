package com.example.cuahang_doan.Fragment.Gio_Hang;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.cuahang_doan.Activity.Hoadon;
import com.example.cuahang_doan.Activity.MainActivity;
import com.example.cuahang_doan.R;
import com.example.cuahang_doan.Services.APIServices;
import com.example.cuahang_doan.Services.DataService;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DialogFragment_Thanhtoangiohang extends DialogFragment {
    private View view;
    private ImageView imglaythongtin;
    private Button btnsacnhan;
    private EditText edthoten,edtdiachi,edtsodienthoai,edtemail;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.dialogfragment_thanhtoangiohang,container,false);
        anhxa();
        sukienclick();
        return view;
    }

    private void sukienclick() {
        imglaythongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager=getFragmentManager();
                DialogFragment_timkiem dialogFragment_timkiem=new DialogFragment_timkiem();
                dialogFragment_timkiem.show(fragmentManager,"dialogtimkiem");
            }
        });
        btnsacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edthoten.getText().toString().equals("") || edtdiachi.getText().toString().equals("")
                || edtsodienthoai.getText().toString().equals("")||edtemail.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "Vui lòng không để trống dữ liệu", Toast.LENGTH_SHORT).show();
                }else if(edtsodienthoai.getText().toString().length()!=10){
                    Toast.makeText(getActivity(), "sai số điện thoại", Toast.LENGTH_SHORT).show();
                }else{
                    Calendar calendar=Calendar.getInstance();
                    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
                    String ngay=simpleDateFormat.format(calendar.getTime());
                    DataService dataService= APIServices.getService();
                    Call<String>callback=dataService.posdondathang(MainActivity.sharedPreferences.getInt("iduser",0)+"",
                            "Chờ Xét Duyệt",ngay,edthoten.getText().toString(),edtdiachi.getText().toString(),
                            edtemail.getText().toString(),edtsodienthoai.getText().toString());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            Log.d("AAA","Post đơn đặt hang: "+response.toString());
                            if(response.isSuccessful()){
                                Toast.makeText(getActivity(), "Đơn hàng của bạn đã được chuyển đi", Toast.LENGTH_SHORT).show();
                                try {
                                    Thread.sleep(3000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                startActivity(new Intent(getContext(), Hoadon.class));
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        MainActivity.mainTablayout.getTabAt(0).select();
                                    }
                                },100);
                                Fragment fragment=getFragmentManager().findFragmentByTag("dialogkhachhangmoi");
                                if(fragment!=null){
                                    DialogFragment df= (DialogFragment) fragment;
                                    df.dismiss();
                                }
                                Toast.makeText(getActivity(), "Đơn hàng của bạn đã được chuyển đi", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                }
            }
        });
    }

    private void anhxa() {
        imglaythongtin=view.findViewById(R.id.imglaythongtin);
        edthoten=view.findViewById(R.id.edthoten);
        edtdiachi=view.findViewById(R.id.edtdiachi);
        edtemail=view.findViewById(R.id.edtemail);
        edtsodienthoai=view.findViewById(R.id.edtsodienthoai);
        btnsacnhan=view.findViewById(R.id.btnsacnhan);
    }
}
