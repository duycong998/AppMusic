package com.example.appmusicc.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.appmusicc.Adapter.ListAlbumAdapter;
import com.example.appmusicc.Model.Album;
import com.example.appmusicc.R;
import com.example.appmusicc.Service.APIService;
import com.example.appmusicc.Service.DataServiec;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListAlbumActivity extends AppCompatActivity {
    Toolbar toolbarAlbum;
    RecyclerView rcViewAllAlbum;
    ListAlbumAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listalbum);
        initView();
        init();
        getData();
    }

    private void getData() {
        DataServiec data = APIService.getData();
        Call<List<Album>> callBack = data.getAllAlbum();
        callBack.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                ArrayList<Album> arrayAllAlbum = (ArrayList<Album>) response.body();
                //Toast.makeText(DanhSachCacAlbumActivity.this, arrayAllAlbum.get(2).getTenAlbum()+ "", Toast.LENGTH_SHORT).show();
                adapter = new ListAlbumAdapter(ListAlbumActivity.this,arrayAllAlbum);
                rcViewAllAlbum.setLayoutManager(new GridLayoutManager(ListAlbumActivity.this,2));
                rcViewAllAlbum.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }

    private void init() {
        setSupportActionBar(toolbarAlbum);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất Cả Các Album");
        toolbarAlbum.setTitleTextColor(getResources().getColor(R.color.colorToolbar));
        toolbarAlbum.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
    }

    private void initView() {
        toolbarAlbum    = findViewById(R.id.toolbarAllAlbum);
        rcViewAllAlbum  = findViewById(R.id.rcviewAllAlbum);
    }
}
