package com.example.appmusicc.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.appmusicc.Adapter.ListOfPlayListsAdapter;
import com.example.appmusicc.Model.PlayList;
import com.example.appmusicc.R;
import com.example.appmusicc.Service.APIService;
import com.example.appmusicc.Service.DataServiec;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListOfPlayListsActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView rcViewDSCacPlayList;
    ListOfPlayListsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listofplaylists);
        initView();
        inint();
        getData();


    }

    private void getData() {
        DataServiec data = APIService.getData();
        Call<List<PlayList>> callBack = data.getListAllPlayList();
        callBack.enqueue(new Callback<List<PlayList>>() {
            @Override
            public void onResponse(Call<List<PlayList>> call, Response<List<PlayList>> response) {
                ArrayList<PlayList> arrayDSCacPlayList = (ArrayList<PlayList>) response.body();
                adapter = new ListOfPlayListsAdapter(ListOfPlayListsActivity.this,arrayDSCacPlayList);
                rcViewDSCacPlayList.setLayoutManager(new GridLayoutManager(ListOfPlayListsActivity.this,2));
                rcViewDSCacPlayList.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<PlayList>> call, Throwable t) {

            }
        });
    }

    private void inint() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Danh Sách Các Play List");
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorToolbar));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbarDSCacPlayList);
        rcViewDSCacPlayList = findViewById(R.id.rcViewDSCacPlayList);
        
    }


}
