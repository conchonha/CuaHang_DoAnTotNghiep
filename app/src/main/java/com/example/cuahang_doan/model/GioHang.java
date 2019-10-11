package com.example.cuahang_doan.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GioHang {

@SerializedName("Id")
@Expose
private Integer id;
@SerializedName("Id_User")
@Expose
private Integer idUser;
@SerializedName("Id_Sanpham")
@Expose
private String idSanpham;
@SerializedName("Ten_Sanpham")
@Expose
private String tenSanpham;
@SerializedName("SoLuong")
@Expose
private Integer soLuong;
@SerializedName("ThanhTien")
@Expose
private Integer thanhTien;
@SerializedName("Hinh")
@Expose
private String hinh;

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public Integer getIdUser() {
return idUser;
}

public void setIdUser(Integer idUser) {
this.idUser = idUser;
}

public String getIdSanpham() {
return idSanpham;
}

public void setIdSanpham(String idSanpham) {
this.idSanpham = idSanpham;
}

public String getTenSanpham() {
return tenSanpham;
}

public void setTenSanpham(String tenSanpham) {
this.tenSanpham = tenSanpham;
}

public Integer getSoLuong() {
return soLuong;
}

public void setSoLuong(Integer soLuong) {
this.soLuong = soLuong;
}

public Integer getThanhTien() {
return thanhTien;
}

public void setThanhTien(Integer thanhTien) {
this.thanhTien = thanhTien;
}

public String getHinh() {
return hinh;
}

public void setHinh(String hinh) {
this.hinh = hinh;
}

}