package com.example.cuahang_doan.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DanhMucCon {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("name")
@Expose
private String name;
@SerializedName("hinh")
@Expose
private String hinh;
@SerializedName("id_danhmuc")
@Expose
private Integer idDanhmuc;

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getHinh() {
return hinh;
}

public void setHinh(String hinh) {
this.hinh = hinh;
}

public Integer getIdDanhmuc() {
return idDanhmuc;
}

public void setIdDanhmuc(Integer idDanhmuc) {
this.idDanhmuc = idDanhmuc;
}

}