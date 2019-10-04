package com.example.cuahang_doan.Services;

import com.example.cuahang_doan.model.DanhMuc;
import com.example.cuahang_doan.model.GetdataSanphammoinhat;
import com.example.cuahang_doan.model.QuangCao;
import com.example.cuahang_doan.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataService {
    @FormUrlEncoded
    @POST("model/login/dangnhap")
    Call<List<User>>postLogin(@Field("user") String user,
                                  @Field("pass") String pass);

    @GET("model/quangcao/getdata")
    Call<List<QuangCao>>GetdataQuangCao();

    @GET("model/danhmuc/getdata")
    Call<List<DanhMuc>>getdataDanhmuc();

    @GET("model/danhmuc/getdatalaptopmoinhat")
    Call<List<GetdataSanphammoinhat>>getDataSanphammoinhat();
}
