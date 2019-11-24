package com.example.cuahang_doan.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuahang_doan.Activity.Hoadon;
import com.example.cuahang_doan.Activity.MainActivity;
import com.example.cuahang_doan.R;
import com.example.cuahang_doan.Services.APIServices;
import com.example.cuahang_doan.Services.DataService;
import com.example.cuahang_doan.model.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimkiemTaikhoan_Adapter extends RecyclerView.Adapter<TimkiemTaikhoan_Adapter.Viewhodler> {
    private View view;
    private ArrayList<User>arrayList;
    private int layout;
    private Context context;

    public TimkiemTaikhoan_Adapter(ArrayList<User> arrayList, int layout, Context context) {
        this.arrayList = arrayList;
        this.layout = layout;
        this.context = context;
    }

    @NonNull
    @Override
    public Viewhodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view=View.inflate(context,layout,null);
        return new Viewhodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewhodler holder, int position) {
        holder.txtsdttaikhoan.setText(arrayList.get(position).getPhoneNumBer());
        holder.txttentaikhoan.setText(arrayList.get(position).getUserName());
        holder.txtdiachitaikhoan.setText(arrayList.get(position).getAdress());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Viewhodler extends RecyclerView.ViewHolder{
        private TextView txttentaikhoan,txtdiachitaikhoan,txtsdttaikhoan;
        public Viewhodler(@NonNull final View itemView) {
            super(itemView);
            txtdiachitaikhoan=itemView.findViewById(R.id.txtdiachitaikhoan);
            txttentaikhoan=itemView.findViewById(R.id.txttentaikhoan);
            txtsdttaikhoan=itemView.findViewById(R.id.txtsdttaikhoan);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (arrayList.get(getPosition()).getId() == MainActivity.sharedPreferences.getInt("iduser", 0)) {
                        Calendar calendar = Calendar.getInstance();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        String data = simpleDateFormat.format(calendar.getTime());
                        DataService dataService = APIServices.getService();
                        Call<String> callback = dataService.posdondathang(MainActivity.sharedPreferences.getInt("iduser", 0) + "",
                                "Chờ Xét Duyệt", data, arrayList.get(getPosition()).getUserName(), arrayList.get(getPosition()).getAdress()
                                , arrayList.get(getPosition()).getEmail(), arrayList.get(getPosition()).getPhoneNumBer());
                        callback.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                Log.d("AAA", "post chitiet donhang: " + response.toString());
                                if (response.isSuccessful()) {
                                    context.startActivity(new Intent(context.getApplicationContext(), Hoadon.class));

                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            MainActivity.mainTablayout.getTabAt(0).select();
                                        }
                                    },100);
                                }
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {

                            }
                        });
                    }else{
                        Toast.makeText(context, "Đây không phải là tài khoản của bạn", Toast.LENGTH_SHORT).show();
                    }
                    
                }
            });
        }
    }
}
