package com.example.appmusicc.Retrofit;

import com.example.appmusicc.Model.Album;
import com.example.appmusicc.Model.Song;
import com.example.appmusicc.Model.Topic;
import com.example.appmusicc.Model.PlayList;
import com.example.appmusicc.Model.Advertisement;
import com.example.appmusicc.Model.Genre;
import com.example.appmusicc.Model.DailyGenre;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataServiec {
    @GET("songbanner.php")
    Call<List<Advertisement>> getDataBanner();

    @GET("playlistforcurrentday.php")
    Call<List<PlayList>> getDataPlayListDay();

    @GET("chudevatheloaingay.php")
    Call<DailyGenre> getTopicGenre();

    @GET("album.php")
    Call<List<Album>> getAlbum();

    @GET("baihatyeuthich.php")
    Call<List<Song>> getLoveSong();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Song>> getListSongAdvertisement(@Field("idAdvertisement") String idAdvertisement);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Song>> getListSongPlayList(@Field("idPlayList") String idPlayList);

    @GET("danhsachcacplaylist.php")
    Call<List<PlayList>>  getListAllPlayList();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Song>> getListSongOfGenre(@Field("idGenre") String idGenre);

    @GET("allchude.php")
    Call<List<Topic>> getAllTopic();

    @FormUrlEncoded
    @POST("danhsachtheloaitheochude.php")
    Call<List<Genre>> getGenreOfTopic(@Field("idTopic") String idTopic);

    @GET("allalbum.php")
    Call<List<Album>>  getAllAlbum();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Song>> getListSongAlbum(@Field("idAlbum") String idAlbum);

    @FormUrlEncoded
    @POST("updateluotthich.php")
    Call<String> updateLike(@Field("mLike") String mLike
                                ,@Field("idSong") String idSong);

    @FormUrlEncoded
    @POST("searchbaihat.php")
    Call<List<Song>> getSearchSong(@Field("mKey") String mKey);

}
