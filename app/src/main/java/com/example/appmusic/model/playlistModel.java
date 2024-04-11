package com.example.appmusic.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class playlistModel  implements Serializable {

    @SerializedName("idplaylist")
    @Expose
    private String idplaylist;
    @SerializedName("ten")
    @Expose
    private String ten;
    @SerializedName("hinhnen")
    @Expose
    private String hinhnen;
    @SerializedName("hinhicon")
    @Expose
    private String hinhicon;

    public playlistModel() {

    }

    public playlistModel(String idplaylist, String ten, String hinhnen, String hinhicon) {
        this.idplaylist = idplaylist;
        this.ten = ten;
        this.hinhnen = hinhnen;
        this.hinhicon = hinhicon;
    }

    public String getIdplaylist() {
        return idplaylist;
    }

    public void setIdplaylist(String idplaylist) {
        this.idplaylist = idplaylist;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getHinhnen() {
        return hinhnen;
    }

    public void setHinhnen(String hinhnen) {
        this.hinhnen = hinhnen;
    }

    public String getHinhicon() {
        return hinhicon;
    }

    public void setHinhicon(String hinhicon) {
        this.hinhicon = hinhicon;
    }
}
