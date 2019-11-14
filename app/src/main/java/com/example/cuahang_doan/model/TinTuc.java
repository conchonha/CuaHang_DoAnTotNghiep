package com.example.cuahang_doan.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TinTuc implements Serializable {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("hinhbaiviet")
@Expose
private String hinhbaiviet;
@SerializedName("tenbaiviet")
@Expose
private String tenbaiviet;
@SerializedName("noidung")
@Expose
private String noidung;
@SerializedName("thoigian")
@Expose
private String thoigian;

    public TinTuc(Integer id, String hinhbaiviet, String tenbaiviet, String noidung, String thoigian) {
        this.id = id;
        this.hinhbaiviet = hinhbaiviet;
        this.tenbaiviet = tenbaiviet;
        this.noidung = noidung;
        this.thoigian = thoigian;
    }

    public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public String getHinhbaiviet() {
return hinhbaiviet;
}

public void setHinhbaiviet(String hinhbaiviet) {
this.hinhbaiviet = hinhbaiviet;
}

public String getTenbaiviet() {
return tenbaiviet;
}

public void setTenbaiviet(String tenbaiviet) {
this.tenbaiviet = tenbaiviet;
}

public String getNoidung() {
return noidung;
}

public void setNoidung(String noidung) {
this.noidung = noidung;
}

public String getThoigian() {
return thoigian;
}

public void setThoigian(String thoigian) {
this.thoigian = thoigian;
}

}