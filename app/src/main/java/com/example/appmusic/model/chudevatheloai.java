package com.example.appmusic.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class chudevatheloai {

    @SerializedName("theloai")
    @Expose
    private List<theloaiModel> theloai = null  ;
    @SerializedName("chude")
    @Expose
    private List<chudeModel> chude  = null ;

     public chudevatheloai() {

     }

    public chudevatheloai(List<theloaiModel> theloai, List<chudeModel> chude) {
        this.theloai = theloai;
        this.chude = chude;
    }

    public List<theloaiModel> getTheloai() {
        return theloai;
    }

    public void setTheloai(List<theloaiModel> theloai) {
        this.theloai = theloai;
    }

    public List<chudeModel> getChude() {
        return chude;
    }

    public void setChude(List<chudeModel> chude) {
        this.chude = chude;
    }
}
