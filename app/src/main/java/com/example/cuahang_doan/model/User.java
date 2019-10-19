package com.example.cuahang_doan.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("UserName")
    @Expose
    private String userName;
    @SerializedName("PassWord")
    @Expose
    private String passWord;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("PhoneNumBer")
    @Expose
    private String phoneNumBer;
    @SerializedName("Adress")
    @Expose
    private String adress;
    @SerializedName("Hinh")
    @Expose
    private String hinh;

    public String getHinh() {
        return hinh;
    }

    public void setId(String hinh) {
        this.hinh = hinh;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumBer() {
        return phoneNumBer;
    }

    public void setPhoneNumBer(String phoneNumBer) {
        this.phoneNumBer = phoneNumBer;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

}