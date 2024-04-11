package com.example.appmusic.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class baihatModel implements Parcelable {

    @SerializedName("idbaihat")
    @Expose
    private String idbaihat;
    @SerializedName("tenbaihat")
    @Expose
    private String tenbaihat;
    @SerializedName("hinhbaihat")
    @Expose
    private String hinhbaihat;
    @SerializedName("casi")
    @Expose
    private String casi;
    @SerializedName("linkbaihat")
    @Expose
    private String linkbaihat;
    @SerializedName("luotthich")
    @Expose
    private int luotthich ;

    public baihatModel() {

    }

    public baihatModel(String idbaihat, String tenbaihat, String hinhbaihat, String casi, String linkbaihat, int luotthich) {
        this.idbaihat = idbaihat;
        this.tenbaihat = tenbaihat;
        this.hinhbaihat = hinhbaihat;
        this.casi = casi;
        this.linkbaihat = linkbaihat;
        this.luotthich = luotthich;
    }

    protected baihatModel(Parcel in) {
        idbaihat = in.readString();
        tenbaihat = in.readString();
        hinhbaihat = in.readString();
        casi = in.readString();
        linkbaihat = in.readString();
        luotthich = in.readInt();
    }

    public static final Creator<baihatModel> CREATOR = new Creator<baihatModel>() {
        @Override
        public baihatModel createFromParcel(Parcel in) {
            return new baihatModel(in);
        }

        @Override
        public baihatModel[] newArray(int size) {
            return new baihatModel[size];
        }
    };

    public String getIdbaihat() {
        return idbaihat;
    }

    public void setIdbaihat(String idbaihat) {
        this.idbaihat = idbaihat;
    }

    public String getTenbaihat() {
        return tenbaihat;
    }

    public void setTenbaihat(String tenbaihat) {
        this.tenbaihat = tenbaihat;
    }

    public String getHinhbaihat() {
        return hinhbaihat;
    }

    public void setHinhbaihat(String hinhbaihat) {
        this.hinhbaihat = hinhbaihat;
    }

    public String getCasi() {
        return casi;
    }

    public void setCasi(String casi) {
        this.casi = casi;
    }

    public String getLinkbaihat() {
        return linkbaihat;
    }

    public void setLinkbaihat(String linkbaihat) {
        this.linkbaihat = linkbaihat;
    }

    public int getLuotthich() {
        return luotthich;
    }

    public void setLuotthich(int luotthich) {
        this.luotthich = luotthich;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(idbaihat);
        parcel.writeString(tenbaihat);
        parcel.writeString(hinhbaihat);
        parcel.writeString(casi);
        parcel.writeString(linkbaihat);
        parcel.writeInt(luotthich);
    }
}
