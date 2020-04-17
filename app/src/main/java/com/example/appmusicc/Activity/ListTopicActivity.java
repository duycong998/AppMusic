package com.example.appmusicc.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.appmusicc.Adapter.ListAllTopicAdaper;
import com.example.appmusicc.Model.Topic;
import com.example.appmusicc.R;
import com.example.appmusicc.Service.APIService;
import com.example.appmusicc.Service.DataServiec;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListTopicActivity extends AppCompatActivity {
    RecyclerView rcViewAllChuDe;
    Toolbar toolbar;
    ListAllTopicAdaper adaper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listtopic);
        initView();
        init();
        getData();
    }

    private void getData() {
        DataServiec data = APIService.getData();
        Call<List<Topic>> callBack = data.getAllTopic();
        callBack.enqueue(new Callback<List<Topic>>() {
            @Override
            public void onResponse(Call<List<Topic>> call, Response<List<Topic>> response) {
                ArrayList<Topic> arrayAllTopic = (ArrayList<Topic>) response.body();
                adaper = new ListAllTopicAdaper(ListTopicActivity.this, arrayAllTopic);
                rcViewAllChuDe.setLayoutManager(new GridLayoutManager(ListTopicActivity.this,1));
                rcViewAllChuDe.setAdapter(adaper);

            }

            @Override
            public void onFailure(Call<List<Topic>> call, Throwable t) {

            }
        });
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Danh Sách Các Chủ Đề");
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorToolbar));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbarAllChuDe);
        rcViewAllChuDe = findViewById(R.id.rcViewAllChuDe);
    }
}
