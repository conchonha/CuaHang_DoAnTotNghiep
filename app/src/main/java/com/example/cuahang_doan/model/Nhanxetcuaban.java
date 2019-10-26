package com.example.cuahang_doan.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Nhanxetcuaban {

@SerializedName("Id_DanGgia")
@Expose
private Integer idDanGgia;
@SerializedName("namsao")
@Expose
private Integer namsao;
@SerializedName("bonsao")
@Expose
private Integer bonsao;
@SerializedName("basao")
@Expose
private Integer basao;
@SerializedName("haisao")
@Expose
private Integer haisao;
@SerializedName("motsao")
@Expose
private Integer motsao;
@SerializedName("ComMent")
@Expose
private String comMent;
@SerializedName("Id_SanPham")
@Expose
private Integer idSanPham;
@SerializedName("username")
@Expose
private String username;
@SerializedName("NgayDanhGia")
@Expose
private String ngayDanhGia;
@SerializedName("Id")
@Expose
private Integer id;
@SerializedName("Dia_Chi")
@Expose
private String diaChi;
@SerializedName("Email")
@Expose
private String email;
@SerializedName("SoDienThoai")
@Expose
private String soDienThoai;
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

public Integer getIdDanGgia() {
return idDanGgia;
}

public void setIdDanGgia(Integer idDanGgia) {
this.idDanGgia = idDanGgia;
}

public Integer getNamsao() {
return namsao;
}

public void setNamsao(Integer namsao) {
this.namsao = namsao;
}

public Integer getBonsao() {
return bonsao;
}

public void setBonsao(Integer bonsao) {
this.bonsao = bonsao;
}

public Integer getBasao() {
return basao;
}

public void setBasao(Integer basao) {
this.basao = basao;
}

public Integer getHaisao() {
return haisao;
}

public void setHaisao(Integer haisao) {
this.haisao = haisao;
}

public Integer getMotsao() {
return motsao;
}

public void setMotsao(Integer motsao) {
this.motsao = motsao;
}

public String getComMent() {
return comMent;
}

public void setComMent(String comMent) {
this.comMent = comMent;
}

public Integer getIdSanPham() {
return idSanPham;
}

public void setIdSanPham(Integer idSanPham) {
this.idSanPham = idSanPham;
}

public String getUsername() {
return username;
}

public void setUsername(String username) {
this.username = username;
}

public String getNgayDanhGia() {
return ngayDanhGia;
}

public void setNgayDanhGia(String ngayDanhGia) {
this.ngayDanhGia = ngayDanhGia;
}

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
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

}