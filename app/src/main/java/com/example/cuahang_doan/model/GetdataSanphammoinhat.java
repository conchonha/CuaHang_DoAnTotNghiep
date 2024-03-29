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
    @SerializedName("ThongSoKyThuat")
    @Expose
    private String thongSoKyThuat;
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
    @SerializedName("id_danhmuc")
    @Expose
    private Integer idDanhmuc;

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

    public String getThongSoKyThuat() {
        return thongSoKyThuat;
    }

    public void setThongSoKyThuat(String thongSoKyThuat) {
        this.thongSoKyThuat = thongSoKyThuat;
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

    public Integer getIdDanhmuc() {
        return idDanhmuc;
    }

    public void setIdDanhmuc(Integer idDanhmuc) {
        this.idDanhmuc=idDanhmuc;
    }
    public GetdataSanphammoinhat(Integer id, String tenSanPham, String hinhAnhSanPham, String thongSoKyThuat, Integer gia, String ngayKhuyenMai, Integer giamGia, String danhGiaSao, String loai) {
        this.id = id;
        this.tenSanPham = tenSanPham;
        this.hinhAnhSanPham = hinhAnhSanPham;
        this.thongSoKyThuat = thongSoKyThuat;
        this.gia = gia;
        this.ngayKhuyenMai = ngayKhuyenMai;
        this.giamGia = giamGia;
        this.danhGiaSao = danhGiaSao;
        this.loai = loai;
    }


}