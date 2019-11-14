package com.example.cuahang_doan.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GioithieuShop implements Serializable {

@SerializedName("Id")
@Expose
private Integer id;
@SerializedName("TenCuaHang")
@Expose
private String tenCuaHang;
@SerializedName("TruSoChinh")
@Expose
private String truSoChinh;
@SerializedName("DienThoai")
@Expose
private String dienThoai;
@SerializedName("Email")
@Expose
private String email;
@SerializedName("Website")
@Expose
private String website;
@SerializedName("Fanpage")
@Expose
private String fanpage;

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public String getTenCuaHang() {
return tenCuaHang;
}

public void setTenCuaHang(String tenCuaHang) {
this.tenCuaHang = tenCuaHang;
}

public String getTruSoChinh() {
return truSoChinh;
}

public void setTruSoChinh(String truSoChinh) {
this.truSoChinh = truSoChinh;
}

public String getDienThoai() {
return dienThoai;
}

public void setDienThoai(String dienThoai) {
this.dienThoai = dienThoai;
}

public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
}

public String getWebsite() {
return website;
}

public void setWebsite(String website) {
this.website = website;
}

public String getFanpage() {
return fanpage;
}

public void setFanpage(String fanpage) {
this.fanpage = fanpage;
}

}