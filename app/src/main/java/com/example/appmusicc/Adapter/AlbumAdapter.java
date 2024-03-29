package com.example.appmusicc.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmusicc.Activity.ListSongActivity;
import com.example.appmusicc.Model.Album;
import com.example.appmusicc.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {
    Context context;
    ArrayList<Album> arrayAlbum;

    public AlbumAdapter(Context context, ArrayList<Album> arrayAlbum) {
        this.context = context;
        this.arrayAlbum = arrayAlbum;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_album,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Album  album = arrayAlbum.get(position);
        holder.txtNameSinger.setText(album.getNameSinger());
        holder.txtNameAlbum.setText(album.getNameAlbum());
        Picasso.with(context).load(album.getPictureAlbum()).into(holder.imgPictureAlbum);

    }

    @Override
    public int getItemCount() {
        return arrayAlbum.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgPictureAlbum;
        TextView txtNameAlbum, txtNameSinger;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPictureAlbum = itemView.findViewById(R.id.imgPictureAlbum);
            txtNameAlbum = itemView.findViewById(R.id.txtNameAlbum);
            txtNameSinger = itemView.findViewById(R.id.txtNameAlbumSinger);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ListSongActivity.class);
                    intent.putExtra("album",arrayAlbum.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
