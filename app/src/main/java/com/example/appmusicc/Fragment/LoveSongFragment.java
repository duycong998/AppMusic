package com.example.appmusicc.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmusicc.Activity.ListSongActivity;
import com.example.appmusicc.Adapter.LoveSongAdapter;
import com.example.appmusicc.Model.Song;
import com.example.appmusicc.R;
import com.example.appmusicc.Service.APIService;
import com.example.appmusicc.Service.DataServiec;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoveSongFragment extends Fragment {
    View view;
    RecyclerView rcViewBH;
    LoveSongAdapter adapter;
    TextView txt;
    ArrayList<Song> arraySong;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.fragment_lovesong,container,false);
       rcViewBH = view.findViewById(R.id.rcViewLoveSongg);
       txt      = view.findViewById(R.id.txtListenAll);
       getData();

       txt.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(getActivity(), ListSongActivity.class);
               intent.putExtra("playalbumlove", arraySong);
               startActivity(intent);
           }
       });
        return view;
    }

    private void getData() {
        DataServiec data = APIService.getData();
        Call<List<Song>> callBack = data.getLoveSong();
        callBack.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                arraySong = (ArrayList<Song>) response.body();
                adapter = new LoveSongAdapter(getActivity(), arraySong);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                rcViewBH.setLayoutManager(linearLayoutManager);
                rcViewBH.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });
    }
}
