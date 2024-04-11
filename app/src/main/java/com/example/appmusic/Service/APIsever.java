package com.example.appmusic.Service;

public class APIsever {

    private static String  url = "https://mtphairieeu.000webhostapp.com/sever/" ;
    public dataservice getsever() {
        return APIclientRetrofit.getclient(url).create(dataservice.class);
    }
}
