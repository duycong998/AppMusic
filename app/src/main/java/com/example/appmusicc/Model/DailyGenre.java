package com.example.appmusicc.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DailyGenre {

    @SerializedName("Genre")
    @Expose
    private List<Genre> genre = null;
    @SerializedName("Topic")
    @Expose
    private List<Topic> topic = null;

    public List<Genre> getGenre() {
        return genre;
    }

    public void setGenre(List<Genre> genre) {
        this.genre = genre;
    }

    public List<Topic> getTopic() {
        return topic;
    }

    public void setTopic(List<Topic> topic) {
        this.topic = topic;
    }

}