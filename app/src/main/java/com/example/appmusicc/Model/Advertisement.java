package com.example.appmusicc.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Advertisement implements Serializable {

    @SerializedName("idAdvertisement")
    @Expose
    private String idAdvertisement;
    @SerializedName("mPicture")
    @Expose
    private String mPicture;
    @SerializedName("mTitle")
    @Expose
    private String mTitle;
    @SerializedName("idSong")
    @Expose
    private String idSong;
    @SerializedName("nameSong")
    @Expose
    private String nameSong;
    @SerializedName("pictureSong")
    @Expose
    private String pictureSong;

    public String getIdAdvertisement() {
        return idAdvertisement;
    }

    public void setIdAdvertisement(String idAdvertisement) {
        this.idAdvertisement = idAdvertisement;
    }

    public String getMPicture() {
        return mPicture;
    }

    public void setMPicture(String mPicture) {
        this.mPicture = mPicture;
    }

    public String getMTitle() {
        return mTitle;
    }

    public void setMTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getIdSong() {
        return idSong;
    }

    public void setIdSong(String idSong) {
        this.idSong = idSong;
    }

    public String getNameSong() {
        return nameSong;
    }

    public void setNameSong(String nameSong) {
        this.nameSong = nameSong;
    }

    public String getPictureSong() {
        return pictureSong;
    }

    public void setPictureSong(String pictureSong) {
        this.pictureSong = pictureSong;
    }
}
