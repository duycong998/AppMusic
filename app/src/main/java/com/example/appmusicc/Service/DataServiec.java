package com.example.appmusicc.Service;

import com.example.appmusicc.Model.Album;
import com.example.appmusicc.Model.BaiHat;
import com.example.appmusicc.Model.ChuDe;
import com.example.appmusicc.Model.PlayList;
import com.example.appmusicc.Model.QuangCao;
import com.example.appmusicc.Model.TheLoai;
import com.example.appmusicc.Model.TheLoaiTrongNgay;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataServiec {
    @GET("songbanner.php")
    Call<List<QuangCao>> getDataBanner();

    @GET("playlistforcurrentday.php")
    Call<List<PlayList>> getDataPlayListDay();

    @GET("chudevatheloaingay.php")
    Call<TheLoaiTrongNgay> getChuDeTheLoai();

    @GET("album.php")
    Call<List<Album>> getAlbum();

    @GET("baihatyeuthich.php")
    Call<List<BaiHat>> getBaiHatYeuThich();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> getDanhSachBHQuangCao(@Field("idQuangCao") String idQuangCao);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> getDanhSachBHPlayList(@Field("idPlayList") String idPlayList);

    @GET("danhsachcacplaylist.php")
    Call<List<PlayList>>  getDanhSachCacPlayList();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> getDanhSachBHTheLoai(@Field("idTheLoai") String idTheLoai);

    @GET("allchude.php")
    Call<List<ChuDe>> getAllChuDe();

    @FormUrlEncoded
    @POST("danhsachtheloaitheochude.php")
    Call<List<TheLoai>> getTheLoaiTheoChuDe(@Field("idChuDe") String idChuDe);

    @GET("allalbum.php")
    Call<List<Album>>  getAllAlbum();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> getDanhSachBHTheoAlbum(@Field("idAlbum") String idAlbum);

    @FormUrlEncoded
    @POST("updateluotthich.php")
    Call<String> updateLuotThich(@Field("luotThich") String luotThich
                                ,@Field("idBaiHat") String idBaiHat);

    @FormUrlEncoded
    @POST("searchbaihat.php")
    Call<List<BaiHat>> getSearchBaiHat(@Field("tukhoa") String tukhoa);

}
