package com.example.cuahang_doan.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetdataSanphammoinhat {

@SerializedName("Id")
@Expose
private Integer id;
@SerializedName("TenSanPham")
@Expose
private String tenSanPham;
@SerializedName("HinhAnhSanPham")
@Expose
private String hinhAnhSanPham;
@SerializedName("Gia")
@Expose
private Integer gia;
@SerializedName("NgayKhuyenMai")
@Expose
private String ngayKhuyenMai;
@SerializedName("GiamGia")
@Expose
private Integer giamGia;
@SerializedName("DanhGiaSao")
@Expose
private String danhGiaSao;
@SerializedName("Loai")
@Expose
private String loai;

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public String getTenSanPham() {
return tenSanPham;
}

public void setTenSanPham(String tenSanPham) {
this.tenSanPham = tenSanPham;
}

public String getHinhAnhSanPham() {
return hinhAnhSanPham;
}

public void setHinhAnhSanPham(String hinhAnhSanPham) {
this.hinhAnhSanPham = hinhAnhSanPham;
}

public Integer getGia() {
return gia;
}

public void setGia(Integer gia) {
this.gia = gia;
}

public String getNgayKhuyenMai() {
return ngayKhuyenMai;
}

public void setNgayKhuyenMai(String ngayKhuyenMai) {
this.ngayKhuyenMai = ngayKhuyenMai;
}

public Integer getGiamGia() {
return giamGia;
}

public void setGiamGia(Integer giamGia) {
this.giamGia = giamGia;
}

public String getDanhGiaSao() {
return danhGiaSao;
}

public void setDanhGiaSao(String danhGiaSao) {
this.danhGiaSao = danhGiaSao;
}

public String getLoai() {
return loai;
}

public void setLoai(String loai) {
this.loai = loai;
}

}