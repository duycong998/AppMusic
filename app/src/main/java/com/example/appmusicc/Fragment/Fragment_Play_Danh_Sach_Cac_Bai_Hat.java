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

import com.example.appmusicc.Activity.PlayNhacActivity;
import com.example.appmusicc.Adapter.PlayNhacAdapter;
import com.example.appmusicc.R;

public class Fragment_Play_Danh_Sach_Cac_Bai_Hat extends Fragment {
    RecyclerView rcViewPlayNhac;
    View view;
    PlayNhacAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_play_danh_sach_cac_bai_hat,container,false);
        rcViewPlayNhac = view.findViewById(R.id.rcViewPlayNhac);
        if(PlayNhacActivity.mangBaiHatt.size()  > 0){
            adapter = new PlayNhacAdapter(getActivity(), PlayNhacActivity.mangBaiHatt);
            rcViewPlayNhac.setLayoutManager(new LinearLayoutManager(getActivity()));
            rcViewPlayNhac.setAdapter(adapter);
        }else {
            Toast.makeText(getContext(), "gắn lỗi", Toast.LENGTH_SHORT).show();
        }


        return view;
    }
}
