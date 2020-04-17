package com.example.appmusicc.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmusicc.Activity.PlayMusicActivity;
import com.example.appmusicc.Adapter.PlayMusicAdapter;
import com.example.appmusicc.R;

public class PlayListSongFragment extends Fragment {
    RecyclerView rcViewPlayNhac;
    View view;
    PlayMusicAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_playlistsong,container,false);
        rcViewPlayNhac = view.findViewById(R.id.rcViewPlayNhac);
        if(PlayMusicActivity.arraySongg.size()  > 0){
            adapter = new PlayMusicAdapter(getActivity(), PlayMusicActivity.arraySongg);
            rcViewPlayNhac.setLayoutManager(new LinearLayoutManager(getActivity()));
            rcViewPlayNhac.setAdapter(adapter);
        }else {
            Toast.makeText(getContext(), "gắn lỗi", Toast.LENGTH_SHORT).show();
        }


        return view;
    }
}
