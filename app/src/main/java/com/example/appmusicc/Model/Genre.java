package com.example.appmusicc.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Genre implements Serializable {

    @SerializedName("idGenre")
    @Expose
    private String idGenre;
    @SerializedName("idTopic")
    @Expose
    private String idTopic;
    @SerializedName("nameGenre")
    @Expose
    private String nameGenre;
    @SerializedName("pictureGenre")
    @Expose
    private String pictureGenre;

    public String getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(String idGenre) {
        this.idGenre = idGenre;
    }

    public String getIdTopic() {
        return idTopic;
    }

    public void setIdTopic(String idTopic) {
        this.idTopic = idTopic;
    }

    public String getNameGenre() {
        return nameGenre;
    }

    public void setNameGenre(String nameGenre) {
        this.nameGenre = nameGenre;
    }

    public String getPictureGenre() {
        return pictureGenre;
    }

    public void setPictureGenre(String pictureGenre) {
        this.pictureGenre = pictureGenre;
    }


}