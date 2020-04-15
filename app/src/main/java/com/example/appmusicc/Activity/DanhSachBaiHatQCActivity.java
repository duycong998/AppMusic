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
import android.widget.Toast;

import com.example.appmusicc.Adapter.DanhSachBaiHatQCAdapter;
import com.example.appmusicc.Model.Album;
import com.example.appmusicc.Model.BaiHat;
import com.example.appmusicc.Model.PlayList;
import com.example.appmusicc.Model.QuangCao;
import com.example.appmusicc.Model.TheLoai;
import com.example.appmusicc.R;
import com.example.appmusicc.Service.APIService;
import com.example.appmusicc.Service.DataServiec;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachBaiHatQCActivity extends AppCompatActivity {
    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RecyclerView recyclerViewDanhSachBHBanner;
    ImageView imgDSBaiHatBanner;
    FloatingActionButton floatingActionButton;
    QuangCao quangCao;
    PlayList playList;
    TheLoai theLoai;
    Album album;
    ArrayList<BaiHat> arrayBaiHat;
    DanhSachBaiHatQCAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_bai_hat_q_c);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);// kiem tra mang
        dataIntent();
        initView();
        init();
        if(quangCao != null && !quangCao.getTenBaiHat().equals("")){
            setValueInView(quangCao.getTenBaiHat(),quangCao.getHinhBaiHat());
            getDataQuangCao(quangCao.getIdQuangCao());

        }

        if(playList != null && !playList.getTen().equals("")){
            setValueInView(playList.getTen(),playList.getHinhNen());
            getDataPlayList(playList.getIdPlayList());
        }

        if(theLoai != null && !theLoai.getTenTheLoai().equals("")){
            setValueInView(theLoai.getTenTheLoai(),theLoai.getHinhTheLoai());
            getDataTheLoai(theLoai.getIdTheLoai());
        }
        
        if(album != null && !album.getTenAlbum().equals("")){
            setValueInView(album.getTenAlbum(),album.getHinhAlbum());
            getDataAlbum(album.getIdAlbum());
        }

        if(arrayBaiHat != null ){
            setValueInView(arrayBaiHat.get(0).getTenBaiHat(),arrayBaiHat.get(0).getHinhBaiHat());
            adapter = new DanhSachBaiHatQCAdapter(DanhSachBaiHatQCActivity.this,arrayBaiHat);
            recyclerViewDanhSachBHBanner.setLayoutManager(new LinearLayoutManager(DanhSachBaiHatQCActivity.this));
            recyclerViewDanhSachBHBanner.setAdapter(adapter);
            eventClick();
        }
    }

    private void getDataAlbum(String idAlbum) {
        DataServiec data = APIService.getData();
        Call<List<BaiHat>> callBack = data.getDanhSachBHTheoAlbum(idAlbum);
        callBack.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                arrayBaiHat = (ArrayList<BaiHat>) response.body();
                Log.d("aaa",arrayBaiHat.get(0).getTenBaiHat());
                adapter = new DanhSachBaiHatQCAdapter(DanhSachBaiHatQCActivity.this,arrayBaiHat);
                recyclerViewDanhSachBHBanner.setLayoutManager(new LinearLayoutManager(DanhSachBaiHatQCActivity.this));
                recyclerViewDanhSachBHBanner.setAdapter(adapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void getDataTheLoai(String idTheLoai) {
        DataServiec data = APIService.getData();
        Call<List<BaiHat>> callBack = data.getDanhSachBHTheLoai(idTheLoai);
        callBack.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                arrayBaiHat = (ArrayList<BaiHat>) response.body();
                adapter = new DanhSachBaiHatQCAdapter(DanhSachBaiHatQCActivity.this,arrayBaiHat);
                recyclerViewDanhSachBHBanner.setLayoutManager(new LinearLayoutManager(DanhSachBaiHatQCActivity.this));
                recyclerViewDanhSachBHBanner.setAdapter(adapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void getDataPlayList(String idPlayList) {
        DataServiec data = APIService.getData();
        Call<List<BaiHat>> callBack = data.getDanhSachBHPlayList(idPlayList);
        callBack.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                arrayBaiHat = (ArrayList<BaiHat>) response.body();
                adapter = new DanhSachBaiHatQCAdapter(DanhSachBaiHatQCActivity.this,arrayBaiHat);
                recyclerViewDanhSachBHBanner.setLayoutManager(new LinearLayoutManager(DanhSachBaiHatQCActivity.this));
                recyclerViewDanhSachBHBanner.setAdapter(adapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {
                Log.d("aaa",t.getMessage());

            }
        });
    }

    private void getDataQuangCao(String idQuangCao) {
        DataServiec data = APIService.getData();
        Call<List<BaiHat>> callBack = data.getDanhSachBHQuangCao(idQuangCao);
        callBack.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                 arrayBaiHat = (ArrayList<BaiHat>) response.body();
                 adapter = new DanhSachBaiHatQCAdapter(DanhSachBaiHatQCActivity.this,arrayBaiHat);
                 recyclerViewDanhSachBHBanner.setLayoutManager(new LinearLayoutManager(DanhSachBaiHatQCActivity.this));
                 recyclerViewDanhSachBHBanner.setAdapter(adapter);
                eventClick();

            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {
                Log.d("bbb",t.getMessage());
            }
        });
    }

    private void setValueInView(String ten , String hinh) {
        collapsingToolbarLayout.setTitle(ten);
        try {
            URL url = new URL(hinh);
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
        Picasso.with(this).load(hinh).into(imgDSBaiHatBanner);
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
        toolbar                      = findViewById(R.id.toolbardanhsachBanner);
        recyclerViewDanhSachBHBanner = findViewById(R.id.rcViewDanhSachBHBanner);
        floatingActionButton         = findViewById(R.id.floatingActionButton);
        imgDSBaiHatBanner            = findViewById(R.id.imgDanhSachCaKhucBanner);
    }

    private void dataIntent() {
        Intent intent = getIntent();
        if(intent != null){
            if(intent.hasExtra("banner")){
                quangCao = (QuangCao) intent.getSerializableExtra("banner");
                //Toast.makeText(this, quangCao.getTenBaiHat()+"", Toast.LENGTH_SHORT).show();
            }
            if(intent.hasExtra("itemplaylist")){
                playList = (PlayList) intent.getSerializableExtra("itemplaylist");
              //  Toast.makeText(this, playList.getTen()+"", Toast.LENGTH_SHORT).show();
            }
            if(intent.hasExtra("idtheloai")){
                theLoai  = (TheLoai) intent.getSerializableExtra("idtheloai");
            }
            if(intent.hasExtra("album")){
                album    = (Album) intent.getSerializableExtra("album");
            }
            if(intent.hasExtra("playalbumyt")){
                arrayBaiHat = intent.getParcelableArrayListExtra("playalbumyt");
            }
        }
    }
    private void eventClick(){
        floatingActionButton.setEnabled(true);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DanhSachBaiHatQCActivity.this,PlayNhacActivity.class);
                intent.putExtra("playallbaihat",arrayBaiHat);
                startActivity(intent);
            }
        });
    }
}
