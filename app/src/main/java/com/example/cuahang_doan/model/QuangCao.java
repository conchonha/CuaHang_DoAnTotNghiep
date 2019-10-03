package com.example.cuahang_doan.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuangCao {

    @SerializedName("HinhAnh")
    @Expose
    private String hinhAnh;
    @SerializedName("NoiDung")
    @Expose
    private String noiDung;
    @SerializedName("Id_Sanpham")
    @Expose
    private Integer idSanpham;
    @SerializedName("TenSanPham")
    @Expose
    private String tenSanPham;
    @SerializedName("HinhAnhSanPham")
    @Expose
    private String hinhAnhSanPham;
    @SerializedName("Id")
    @Expose
    private Integer id;

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public Integer getIdSanpham() {
        return idSanpham;
    }

    public void setIdSanpham(Integer idSanpham) {
        this.idSanpham = idSanpham;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}