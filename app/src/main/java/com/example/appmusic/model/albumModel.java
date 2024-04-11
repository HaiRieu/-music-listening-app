package com.example.appmusic.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class albumModel implements Serializable {

    @SerializedName("idalbum")
    @Expose
    private String idalbum;
    @SerializedName("tenalbum")
    @Expose
    private String tenalbum;
    @SerializedName("tencasialbum")
    @Expose
    private String tencasialbum;
    @SerializedName("hinhanhalbum")
    @Expose
    private String hinhanhalbum;

    public albumModel() {

    }

    public albumModel(String idalbum, String tenalbum, String tencasialbum, String hinhanhalbum) {
        this.idalbum = idalbum;
        this.tenalbum = tenalbum;
        this.tencasialbum = tencasialbum;
        this.hinhanhalbum = hinhanhalbum;
    }

    public String getIdalbum() {
        return idalbum;
    }

    public void setIdalbum(String idalbum) {
        this.idalbum = idalbum;
    }

    public String getTenalbum() {
        return tenalbum;
    }

    public void setTenalbum(String tenalbum) {
        this.tenalbum = tenalbum;
    }

    public String getTencasialbum() {
        return tencasialbum;
    }

    public void setTencasialbum(String tencasialbum) {
        this.tencasialbum = tencasialbum;
    }

    public String getHinhanhalbum() {
        return hinhanhalbum;
    }

    public void setHinhanhalbum(String hinhanhalbum) {
        this.hinhanhalbum = hinhanhalbum;
    }
}
