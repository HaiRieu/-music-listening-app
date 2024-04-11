package com.example.appmusic.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class quangcaoModel implements Serializable {

    @SerializedName("idquangcao")
    @Expose
    private String idquangcao;
    @SerializedName("hinhanh")
    @Expose
    private String hinhanh;
    @SerializedName("noidung")
    @Expose
    private String noidung;
    @SerializedName("hinhbaihat")
    @Expose
    private String hinhbaihat;
    @SerializedName("tenbaihat")
    @Expose
    private String tenbaihat;

    public quangcaoModel(String idquangcao, String hinhanh, String noidung, String hinhbaihat, String tenbaihat) {
        this.idquangcao = idquangcao;
        this.hinhanh = hinhanh;
        this.noidung = noidung;
        this.hinhbaihat = hinhbaihat;
        this.tenbaihat = tenbaihat;
    }

    public quangcaoModel() {

    }

    public String getIdquangcao() {
        return idquangcao;
    }

    public void setIdquangcao(String idquangcao) {
        this.idquangcao = idquangcao;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getHinhbaihat() {
        return hinhbaihat;
    }

    public void setHinhbaihat(String hinhbaihat) {
        this.hinhbaihat = hinhbaihat;
    }

    public String getTenbaihat() {
        return tenbaihat;
    }

    public void setTenbaihat(String tenbaihat) {
        this.tenbaihat = tenbaihat;
    }


}
