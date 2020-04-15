package com.example.appmusicc.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.appmusicc.Adapter.DanhSachTatCaChuDeAdaper;
import com.example.appmusicc.Model.ChuDe;
import com.example.appmusicc.R;
import com.example.appmusicc.Service.APIService;
import com.example.appmusicc.Service.DataServiec;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachCacChuDeActivity extends AppCompatActivity {
    RecyclerView rcViewAllChuDe;
    Toolbar toolbar;
    DanhSachTatCaChuDeAdaper adaper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_cac_chu_de);
        initView();
        init();
        getData();
    }

    private void getData() {
        DataServiec data = APIService.getData();
        Call<List<ChuDe>> callBack = data.getAllChuDe();
        callBack.enqueue(new Callback<List<ChuDe>>() {
            @Override
            public void onResponse(Call<List<ChuDe>> call, Response<List<ChuDe>> response) {
                ArrayList<ChuDe> arrayAllChuDe = (ArrayList<ChuDe>) response.body();
                adaper = new DanhSachTatCaChuDeAdaper(DanhSachCacChuDeActivity.this,arrayAllChuDe);
                rcViewAllChuDe.setLayoutManager(new GridLayoutManager(DanhSachCacChuDeActivity.this,1));
                rcViewAllChuDe.setAdapter(adaper);

            }

            @Override
            public void onFailure(Call<List<ChuDe>> call, Throwable t) {

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
