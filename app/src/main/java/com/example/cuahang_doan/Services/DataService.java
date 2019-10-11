package com.example.cuahang_doan.Services;

import com.example.cuahang_doan.model.DanhMuc;
import com.example.cuahang_doan.model.Danhgia;
import com.example.cuahang_doan.model.GetdataSanphammoinhat;
import com.example.cuahang_doan.model.GioHang;
import com.example.cuahang_doan.model.QuangCao;
import com.example.cuahang_doan.model.SanPham;
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

    @GET("model/linhkienlaptop/getdatalinhkienlaptop")
    Call<List<GetdataSanphammoinhat>>getDataLinhkienlaptop();

    @GET("model/danhmuc/getdatathietbiluutruphukien")
    Call<List<GetdataSanphammoinhat>>getdataThietbiluutruphukien();

    @GET("model/danhmuc/getdatathietbinghenhin")
    Call<List<GetdataSanphammoinhat>>getDataThietbinghenhin();

    @FormUrlEncoded
    @POST("model/sanpham/getdatasanphamchitiet")
    Call<List<SanPham>>postSanphamchitiet(@Field("id") String id);

    @FormUrlEncoded
    @POST("model/danhgia/getdanhgia")
    Call<List<Danhgia>>getdataDanhgia(@Field("masanpham") String masanpham);

    @FormUrlEncoded
    @POST("model/danhgia/postDanhgia")
    Call<String>postDanhgia(@Field("username") String username,
                                   @Field("id_sanpham") String idsanpham,
                                   @Field("ngaydanhgia") String ngaydanhgia,
                                   @Field("namsao") int namsao,
                                   @Field("bonsao") int bonsao,
                                   @Field("basao") int basao,
                                   @Field("haisao") int haisao,
                                   @Field("motsao") int motsao,
                                   @Field("comment") String comment);

    @FormUrlEncoded
    @POST("model/giohang/postgiohang")
    Call<String>postGiohang(@Field("iduser") int iduser,
                            @Field("idsanpham") int idsanpham,
                            @Field("giasp") int giasp
                            );

    @GET("model/giohang/getdatagiohang")
    Call<List<GioHang>>getdataGiohang();

    @FormUrlEncoded
    @POST("model/giohang/updategiohang")
    Call<String>updategiohang(@Field("idsanpham") String idsanpham,
                                    @Field("soluong") int soluong,
                                    @Field("thanhtien") int thanhtien);
    @FormUrlEncoded
    @POST("model/giohang/delete")
    Call<String>Deletesanpham(@Field("idsanpham") String idsanpham);

}
