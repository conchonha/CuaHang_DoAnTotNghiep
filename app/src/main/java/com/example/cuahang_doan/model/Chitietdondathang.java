package com.example.cuahang_doan.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Chitietdondathang {

@SerializedName("Id")
@Expose
private Integer id;
@SerializedName("Id_Taikhoan")
@Expose
private Integer idTaikhoan;
@SerializedName("Username")
@Expose
private String username;
@SerializedName("Dia_Chi")
@Expose
private String diaChi;
@SerializedName("Email")
@Expose
private String email;
@SerializedName("SoDienThoai")
@Expose
private String soDienThoai;
@SerializedName("Id_SanPham")
@Expose
private Integer idSanPham;
@SerializedName("GiaSanPham")
@Expose
private Integer giaSanPham;
@SerializedName("HinhSanPham")
@Expose
private String hinhSanPham;
@SerializedName("SoLuongSanPham")
@Expose
private Integer soLuongSanPham;
@SerializedName("TenSanPham")
@Expose
private String tenSanPham;
@SerializedName("Id_DonDatHang")
@Expose
private String idDonDatHang;

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public Integer getIdTaikhoan() {
return idTaikhoan;
}

public void setIdTaikhoan(Integer idTaikhoan) {
this.idTaikhoan = idTaikhoan;
}

public String getUsername() {
return username;
}

public void setUsername(String username) {
this.username = username;
}

public String getDiaChi() {
return diaChi;
}

public void setDiaChi(String diaChi) {
this.diaChi = diaChi;
}

public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
}

public String getSoDienThoai() {
return soDienThoai;
}

public void setSoDienThoai(String soDienThoai) {
this.soDienThoai = soDienThoai;
}

public Integer getIdSanPham() {
return idSanPham;
}

public void setIdSanPham(Integer idSanPham) {
this.idSanPham = idSanPham;
}

public Integer getGiaSanPham() {
return giaSanPham;
}

public void setGiaSanPham(Integer giaSanPham) {
this.giaSanPham = giaSanPham;
}

public String getHinhSanPham() {
return hinhSanPham;
}

public void setHinhSanPham(String hinhSanPham) {
this.hinhSanPham = hinhSanPham;
}

public Integer getSoLuongSanPham() {
return soLuongSanPham;
}

public void setSoLuongSanPham(Integer soLuongSanPham) {
this.soLuongSanPham = soLuongSanPham;
}

public String getTenSanPham() {
return tenSanPham;
}

public void setTenSanPham(String tenSanPham) {
this.tenSanPham = tenSanPham;
}

public String getIdDonDatHang() {
return idDonDatHang;
}

public void setIdDonDatHang(String idDonDatHang) {
this.idDonDatHang = idDonDatHang;
}

}