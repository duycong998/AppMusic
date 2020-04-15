package com.example.appmusicc.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmusicc.Activity.DanhSachBaiHatQCActivity;
import com.example.appmusicc.Activity.PlayNhacActivity;
import com.example.appmusicc.Adapter.BaiHatYTAdapter;
import com.example.appmusicc.Model.BaiHat;
import com.example.appmusicc.R;
import com.example.appmusicc.Service.APIService;
import com.example.appmusicc.Service.DataServiec;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_BaiHat_YeuThich extends Fragment {
    View view;
    RecyclerView rcViewBH;
    BaiHatYTAdapter adapter;
    TextView txt;
    ArrayList<BaiHat> arrayBaiHat;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.fragment_bai_hat_yeu_thich,container,false);
       rcViewBH = view.findViewById(R.id.rcViewBaiHat);
       txt      = view.findViewById(R.id.txtCamoncacban);
       getData();

       txt.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(getActivity(), DanhSachBaiHatQCActivity.class);
               intent.putExtra("playalbumyt",arrayBaiHat);
               startActivity(intent);
           }
       });
        return view;
    }

    private void getData() {
        DataServiec data = APIService.getData();
        Call<List<BaiHat>> callBack = data.getBaiHatYeuThich();
        callBack.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                arrayBaiHat = (ArrayList<BaiHat>) response.body();
                adapter = new BaiHatYTAdapter(getActivity(),arrayBaiHat);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                rcViewBH.setLayoutManager(linearLayoutManager);
                rcViewBH.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }
}
