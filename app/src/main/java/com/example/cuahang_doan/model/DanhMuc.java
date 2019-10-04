package com.example.cuahang_doan.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DanhMuc {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("ten")
@Expose
private String ten;
@SerializedName("hinhicon")
@Expose
private String hinhicon;

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public String getTen() {
return ten;
}

public void setTen(String ten) {
this.ten = ten;
}

public String getHinhicon() {
return hinhicon;
}

public void setHinhicon(String hinhicon) {
this.hinhicon = hinhicon;
}

}