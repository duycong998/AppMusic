package com.example.appmusicc.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.appmusicc.Adapter.ListSongAdapter;
import com.example.appmusicc.Model.Album;
import com.example.appmusicc.Model.Song;
import com.example.appmusicc.Model.PlayList;
import com.example.appmusicc.Model.Advertisement;
import com.example.appmusicc.Model.Genre;
import com.example.appmusicc.R;
import com.example.appmusicc.Service.APIService;
import com.example.appmusicc.Service.DataServiec;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListSongActivity extends AppCompatActivity {
    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RecyclerView recyclerViewListSongBanner;
    ImageView imgListSongBanner;
    FloatingActionButton floatingActionButton;
    Advertisement advertisement;
    PlayList playList;
    Genre genre;
    Album album;
    ArrayList<Song> arraySong;
    ListSongAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listsong);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);// kiem tra mang
        dataIntent();
        initView();
        init();
        if(advertisement != null && !advertisement.getNameSong().equals("")){
            setValueInView(advertisement.getNameSong(), advertisement.getPictureSong());
            getDataAdvertisement(advertisement.getIdAdvertisement());

        }

        if(playList != null && !playList.getMName().equals("")){
            setValueInView(playList.getMName(),playList.getMPicture());
            getDataPlayList(playList.getIdPlayList());
        }

        if(genre != null && !genre.getNameGenre().equals("")){
            setValueInView(genre.getNameGenre(), genre.getPictureGenre());
            getDataGenre(genre.getIdGenre());
        }
        
        if(album != null && !album.getNameAlbum().equals("")){
            setValueInView(album.getNameAlbum(),album.getPictureAlbum());
            getDataAlbum(album.getIdAlbum());
        }

        if(arraySong != null ){
            setValueInView(arraySong.get(0).getNameSong(), arraySong.get(0).getPictureSong());
            adapter = new ListSongAdapter(ListSongActivity.this, arraySong);
            recyclerViewListSongBanner.setLayoutManager(new LinearLayoutManager(ListSongActivity.this));
            recyclerViewListSongBanner.setAdapter(adapter);
            eventClick();
        }
    }

    private void getDataAlbum(String idAlbum) {
        DataServiec data = APIService.getData();
        Call<List<Song>> callBack = data.getListSongAlbum(idAlbum);
        callBack.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                arraySong = (ArrayList<Song>) response.body();
                Log.d("aaa", arraySong.get(0).getNameSong());
                adapter = new ListSongAdapter(ListSongActivity.this, arraySong);
                recyclerViewListSongBanner.setLayoutManager(new LinearLayoutManager(ListSongActivity.this));
                recyclerViewListSongBanner.setAdapter(adapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });
    }

    private void getDataGenre(String idGenre) {
        DataServiec data = APIService.getData();
        Call<List<Song>> callBack = data.getListSongOfGenre(idGenre);
        callBack.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                arraySong = (ArrayList<Song>) response.body();
                adapter = new ListSongAdapter(ListSongActivity.this, arraySong);
                recyclerViewListSongBanner.setLayoutManager(new LinearLayoutManager(ListSongActivity.this));
                recyclerViewListSongBanner.setAdapter(adapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });
    }

    private void getDataPlayList(String idPlayList) {
        DataServiec data = APIService.getData();
        Call<List<Song>> callBack = data.getListSongPlayList(idPlayList);
        callBack.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                arraySong = (ArrayList<Song>) response.body();
                adapter = new ListSongAdapter(ListSongActivity.this, arraySong);
                recyclerViewListSongBanner.setLayoutManager(new LinearLayoutManager(ListSongActivity.this));
                recyclerViewListSongBanner.setAdapter(adapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {
                Log.d("aaa",t.getMessage());

            }
        });
    }

    private void getDataAdvertisement(String idAdvertisement) {
        DataServiec data = APIService.getData();
        Call<List<Song>> callBack = data.getListSongAdvertisement(idAdvertisement);
        callBack.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                 arraySong = (ArrayList<Song>) response.body();
                 adapter = new ListSongAdapter(ListSongActivity.this, arraySong);
                 recyclerViewListSongBanner.setLayoutManager(new LinearLayoutManager(ListSongActivity.this));
                 recyclerViewListSongBanner.setAdapter(adapter);
                eventClick();

            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {
                Log.d("bbb",t.getMessage());
            }
        });
    }

    private void setValueInView(String mName , String mPicture) {
        collapsingToolbarLayout.setTitle(mName);
        try {
            URL url = new URL(mPicture);
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(),bitmap);
         //   collapsingToolbarLayout.setBackground(bitmapDrawable);
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
                collapsingToolbarLayout.setBackground(bitmapDrawable);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Picasso.with(this).load(mPicture).into(imgListSongBanner);
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//tao nut back
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.RED);
        floatingActionButton.setEnabled(false);
    }

    private void initView() {
        coordinatorLayout            = findViewById(R.id.coordinatorlayout);
        collapsingToolbarLayout      = findViewById(R.id.collapsingtoolbar);
        toolbar                      = findViewById(R.id.toolbarListsBanner);
        recyclerViewListSongBanner = findViewById(R.id.rcViewListSongBHBanner);
        floatingActionButton         = findViewById(R.id.floatingActionButton);
        imgListSongBanner = findViewById(R.id.imgListsBanner);
    }

    private void dataIntent() {
        Intent intent = getIntent();
        if(intent != null){
            if(intent.hasExtra("banner")){
                advertisement = (Advertisement) intent.getSerializableExtra("banner");
                //Toast.makeText(this, quangCao.getTenBaiHat()+"", Toast.LENGTH_SHORT).show();
            }
            if(intent.hasExtra("itemplaylist")){
                playList = (PlayList) intent.getSerializableExtra("itemplaylist");
              //  Toast.makeText(this, playList.getTen()+"", Toast.LENGTH_SHORT).show();
            }
            if(intent.hasExtra("idgenre")){
                genre = (Genre) intent.getSerializableExtra("idgenre");
            }
            if(intent.hasExtra("album")){
                album    = (Album) intent.getSerializableExtra("album");
            }
            if(intent.hasExtra("playalbumlove")){
                arraySong = intent.getParcelableArrayListExtra("playalbumlove");
            }
        }
    }
    private void eventClick(){
        floatingActionButton.setEnabled(true);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListSongActivity.this, PlayMusicActivity.class);
                intent.putExtra("playallsong", arraySong);
                startActivity(intent);
            }
        });
    }
}
