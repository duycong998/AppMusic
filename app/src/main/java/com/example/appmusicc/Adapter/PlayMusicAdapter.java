package com.example.appmusicc.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmusicc.Model.Song;
import com.example.appmusicc.R;

import java.util.ArrayList;

public class PlayMusicAdapter extends RecyclerView.Adapter<PlayMusicAdapter.ViewHolder>{
    Context context;
    ArrayList<Song> arrayListSong;

    public PlayMusicAdapter(Context context, ArrayList<Song> arrayListSong) {
        this.context = context;
        this.arrayListSong = arrayListSong;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_playmusic,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Song song = arrayListSong.get(position);
        holder.txtNameSong.setText(song.getNameSong());
        holder.txtSinger.setText(song.getMSinger());
        holder.txtIndex.setText(position + 1 + "");

    }

    @Override
    public int getItemCount() {
        return arrayListSong.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtIndex, txtNameSong, txtSinger;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtIndex = itemView.findViewById(R.id.txtPlayMusicIndex);
            txtNameSong = itemView.findViewById(R.id.txtPlayMusicNameSong);
            txtSinger = itemView.findViewById(R.id.txtPlayMusicNameSinger);
        }
    }
}
