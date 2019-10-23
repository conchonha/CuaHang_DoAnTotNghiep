package com.example.cuahang_doan.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DonDatHang {

@SerializedName("Id_DonHang")
@Expose
private Integer idDonHang;
@SerializedName("NgayDat")
@Expose
private String ngayDat;
@SerializedName("TrinhTrang")
@Expose
private String trinhTrang;
@SerializedName("Id_TaiKhoan")
@Expose
private Integer idTaiKhoan;

public Integer getIdDonHang() {
return idDonHang;
}

public void setIdDonHang(Integer idDonHang) {
this.idDonHang = idDonHang;
}

public String getNgayDat() {
return ngayDat;
}

public void setNgayDat(String ngayDat) {
this.ngayDat = ngayDat;
}

public String getTrinhTrang() {
return trinhTrang;
}

public void setTrinhTrang(String trinhTrang) {
this.trinhTrang = trinhTrang;
}

public Integer getIdTaiKhoan() {
return idTaiKhoan;
}

public void setIdTaiKhoan(Integer idTaiKhoan) {
this.idTaiKhoan = idTaiKhoan;
}

}