package com.example.appmusicc.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Random;

public class Song implements Parcelable {

    @SerializedName("idSong")
    @Expose
    private String idSong;
    @SerializedName("nameSong")
    @Expose
    private String nameSong;
    @SerializedName("pictureSong")
    @Expose
    private String pictureSong;
    @SerializedName("mSinger")
    @Expose
    private String mSinger;
    @SerializedName("linkSong")
    @Expose
    private String linkSong;
    @SerializedName("mLike")
    @Expose
    private String mLike;

    protected Song(Parcel in) {
        idSong = in.readString();
        nameSong = in.readString();
        pictureSong = in.readString();
        mSinger = in.readString();
        linkSong = in.readString();
        mLike = in.readString();
    }
    public Song() {
    }

    public static final Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };

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

    public String getMSinger() {
        return mSinger;
    }

    public void setMSinger(String mSinger) {
        this.mSinger = mSinger;
    }

    public String getLinkSong() {
        return linkSong;
    }

    public void setLinkSong(String linkSong) {
        this.linkSong = linkSong;
    }

    public String getMLike() {
        return mLike;
    }

    public void setMLike(String mLike) {
        this.mLike = mLike;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idSong);
        dest.writeString(nameSong);
        dest.writeString(pictureSong);
        dest.writeString(mSinger);
        dest.writeString(linkSong);
        dest.writeString(mLike);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return idSong.equals(song.idSong) && nameSong.equals(song.nameSong) && linkSong.equals(song.linkSong);
    }

    @Override
    public int hashCode() {
        return idSong.length() + nameSong.length() + pictureSong.length() + mSinger.length() + linkSong.length() + mLike.length()
                + new Random().nextInt();
    }
}