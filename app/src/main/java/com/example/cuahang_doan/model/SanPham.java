package com.example.cuahang_doan.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SanPham {

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
    @SerializedName("HinhMoTa")
    @Expose
    private String hinhMoTa;
    @SerializedName("Mota")
    @Expose
    private String mota;
    @SerializedName("SoLuong")
    @Expose
    private Integer soLuong;
    @SerializedName("NgayDang")
    @Expose
    private String ngayDang;
    @SerializedName("ThongSoKyThuat")
    @Expose
    private String thongSoKyThuat;

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

    public String getHinhMoTa() {
        return hinhMoTa;
    }

    public void setHinhMoTa(String hinhMoTa) {
        this.hinhMoTa = hinhMoTa;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public String getNgayDang() {
        return ngayDang;
    }

    public void setNgayDang(String ngayDang) {
        this.ngayDang = ngayDang;
    }

    public String getThongSoKyThuat() {
        return thongSoKyThuat;
    }

    public void setThongSoKyThuat(String thongSoKyThuat) {
        this.thongSoKyThuat = thongSoKyThuat;
    }

}