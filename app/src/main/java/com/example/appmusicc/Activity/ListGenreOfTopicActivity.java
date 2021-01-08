package com.example.appmusicc.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.appmusicc.Adapter.ListGenreOfTopicAdapter;
import com.example.appmusicc.Model.Topic;
import com.example.appmusicc.Model.Genre;
import com.example.appmusicc.R;
import com.example.appmusicc.Retrofit.APIService;
import com.example.appmusicc.Retrofit.DataServiec;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListGenreOfTopicActivity extends AppCompatActivity {
    Topic topic;
    RecyclerView rcViewGenreOfTopic;
    Toolbar toolbar;
    ListGenreOfTopicAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listgenreoftopic);
        getDataIntent(); // lấy dữ liệu rồi mới gán data
        initView();
        init();
        getData();
    }

    private void getData() {
        DataServiec data = APIService.getData();
        Call<List<Genre>> callBack = data.getGenreOfTopic(topic.getIdTopic());
        callBack.enqueue(new Callback<List<Genre>>() {
            @Override
            public void onResponse(Call<List<Genre>> call, Response<List<Genre>> response) {
                ArrayList<Genre> arrayTheLoaiTheCD  = (ArrayList<Genre>) response.body();
                adapter = new ListGenreOfTopicAdapter(ListGenreOfTopicActivity.this,arrayTheLoaiTheCD);
                rcViewGenreOfTopic.setLayoutManager(new GridLayoutManager(ListGenreOfTopicActivity.this,2));
                rcViewGenreOfTopic.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Genre>> call, Throwable t) {
                Log.d("bbb",t.getMessage());

            }
        });
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Chủ Đề " + topic.getNameTopic());
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorToolbar));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        rcViewGenreOfTopic = findViewById(R.id.rcViewTheLoaiTheoCD);
        toolbar             = findViewById(R.id.toolbarTheLoaiTheoCD);
    }

    private void getDataIntent() {
        Intent intent = getIntent();
        if(intent.hasExtra("genretopic")){
            topic = (Topic) intent.getSerializableExtra("genretopic");

        }
    }
}
