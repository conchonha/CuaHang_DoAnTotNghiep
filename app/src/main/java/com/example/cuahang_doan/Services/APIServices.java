package com.example.cuahang_doan.Services;

public class APIServices {
    private static String baseurl="http://192.168.1.55/cuahang/public/";

    public static DataService getService(){

        return APIRetrofitClient.getClient(baseurl).create(DataService.class);
    }
}