package com.example.appmusicc.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Album implements Serializable {

    @SerializedName("idAlbum")
    @Expose
    private String idAlbum;
    @SerializedName("nameAlbum")
    @Expose
    private String nameAlbum;
    @SerializedName("nameSinger")
    @Expose
    private String nameSinger;
    @SerializedName("pictureAlbum")
    @Expose
    private String pictureAlbum;

    public String getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(String idAlbum) {
        this.idAlbum = idAlbum;
    }

    public String getNameAlbum() {
        return nameAlbum;
    }

    public void setNameAlbum(String nameAlbum) {
        this.nameAlbum = nameAlbum;
    }

    public String getNameSinger() {
        return nameSinger;
    }

    public void setNameSinger(String nameSinger) {
        this.nameSinger = nameSinger;
    }

    public String getPictureAlbum() {
        return pictureAlbum;
    }

    public void setPictureAlbum(String pictureAlbum) {
        this.pictureAlbum = pictureAlbum;
    }
}