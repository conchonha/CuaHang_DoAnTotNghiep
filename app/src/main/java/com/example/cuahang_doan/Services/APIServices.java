package com.example.cuahang_doan.Services;

public class APIServices {
    private static String baseurl="http://192.168.1.70/cuahang/public/";
    public static String urlhinh="http://192.168.1.70/cuahang/public/img/";

    public static DataService getService(){

        return APIRetrofitClient.getClient(baseurl).create(DataService.class);
    }
}
