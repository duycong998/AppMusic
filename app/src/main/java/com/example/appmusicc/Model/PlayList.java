package com.example.appmusicc.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PlayList implements Serializable {

    @SerializedName("idPlayList")
    @Expose
    private String idPlayList;
    @SerializedName("mName")
    @Expose
    private String mName;
    @SerializedName("mPicture")
    @Expose
    private String mPicture;
    @SerializedName("iconPicture")
    @Expose
    private String iconPicture;

    public String getIdPlayList() {
        return idPlayList;
    }

    public void setIdPlayList(String idPlayList) {
        this.idPlayList = idPlayList;
    }

    public String getMName() {
        return mName;
    }

    public void setMName(String mName) {
        this.mName = mName;
    }

    public String getMPicture() {
        return mPicture;
    }

    public void setMPicture(String mPicture) {
        this.mPicture = mPicture;
    }

    public String getIconPicture() {
        return iconPicture;
    }

    public void setIconPicture(String iconPicture) {
        this.iconPicture = iconPicture;
    }

}