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

import com.example.appmusicc.Activity.ListAlbumActivity;
import com.example.appmusicc.Adapter.AlbumAdapter;
import com.example.appmusicc.Model.Album;
import com.example.appmusicc.R;
import com.example.appmusicc.Service.APIService;
import com.example.appmusicc.Service.DataServiec;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumFragment extends Fragment{
    View view;
    RecyclerView rcvAlbum;
    TextView txtSeeAll;
    AlbumAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_album,container,false);
        rcvAlbum = view.findViewById(R.id.recyclerviewAlbum);
        txtSeeAll = view.findViewById(R.id.txtAlbumSeeAll);
        txtSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListAlbumActivity.class);
                startActivity(intent);
            }
        });
        getData();
        return view;
    }

    private void getData() {
        DataServiec data = APIService.getData();
        Call<List<Album>> callBack = data.getAlbum();
        callBack.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                ArrayList<Album> arrayAlbum = (ArrayList<Album>) response.body();
                adapter = new AlbumAdapter(getActivity(),arrayAlbum);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
                rcvAlbum.setLayoutManager(linearLayoutManager);
                rcvAlbum.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }
}
