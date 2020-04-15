package com.example.appmusicc.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.appmusicc.Adapter.DanhSachBaiHatQCAdapter;
import com.example.appmusicc.Adapter.DanhSachTheLoaiTheoCDAdapter;
import com.example.appmusicc.Model.ChuDe;
import com.example.appmusicc.Model.TheLoai;
import com.example.appmusicc.R;
import com.example.appmusicc.Service.APIService;
import com.example.appmusicc.Service.DataServiec;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachTheLoaiTheoCDActivity extends AppCompatActivity {
    ChuDe chuDe;
    RecyclerView rcViewTheLoaiTheoCD;
    Toolbar toolbar;
    DanhSachTheLoaiTheoCDAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_the_loai_theo_c_d);
        getDataIntent(); // lấy dữ liệu rồi mới gán data
        initView();
        init();
        getData();
    }

    private void getData() {
        DataServiec data = APIService.getData();
        Call<List<TheLoai>> callBack = data.getTheLoaiTheoChuDe(chuDe.getIdChuDe());
        callBack.enqueue(new Callback<List<TheLoai>>() {
            @Override
            public void onResponse(Call<List<TheLoai>> call, Response<List<TheLoai>> response) {
                ArrayList<TheLoai> arrayTheLoaiTheCD  = (ArrayList<TheLoai>) response.body();
                adapter = new DanhSachTheLoaiTheoCDAdapter(DanhSachTheLoaiTheoCDActivity.this,arrayTheLoaiTheCD);
                rcViewTheLoaiTheoCD.setLayoutManager(new GridLayoutManager(DanhSachTheLoaiTheoCDActivity.this,2));
                rcViewTheLoaiTheoCD.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<TheLoai>> call, Throwable t) {
                Log.d("bbb",t.getMessage());

            }
        });
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Chủ Đề " + chuDe.getTenChuDe());
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorToolbar));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        rcViewTheLoaiTheoCD = findViewById(R.id.rcViewTheLoaiTheoCD);
        toolbar             = findViewById(R.id.toolbarTheLoaiTheoCD);
    }

    private void getDataIntent() {
        Intent intent = getIntent();
        if(intent.hasExtra("theloaitheochude")){
            chuDe = (ChuDe) intent.getSerializableExtra("theloaitheochude");

        }
    }
}
