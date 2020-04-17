package com.example.appmusicc.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Topic implements Serializable {

    @SerializedName("idTopic")
    @Expose
    private String idTopic;
    @SerializedName("nameTopic")
    @Expose
    private String nameTopic;
    @SerializedName("pictureTopic")
    @Expose
    private String pictureTopic;

    public String getIdTopic() {
        return idTopic;
    }

    public void setIdTopic(String idTopic) {
        this.idTopic = idTopic;
    }

    public String getNameTopic() {
        return nameTopic;
    }

    public void setNameTopic(String nameTopic) {
        this.nameTopic = nameTopic;
    }

    public String getPictureTopic() {
        return pictureTopic;
    }

    public void setPictureTopic(String pictureTopic) {
        this.pictureTopic = pictureTopic;
    }

}