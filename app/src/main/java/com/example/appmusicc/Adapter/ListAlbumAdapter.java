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

public class ListAlbumAdapter extends RecyclerView.Adapter<ListAlbumAdapter.ViewHolder> {
    Context context;
    ArrayList<Album> arrayAllAlbum;

    public ListAlbumAdapter(Context context, ArrayList<Album> arrayAllAlbum) {
        this.context = context;
        this.arrayAllAlbum = arrayAllAlbum;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_listalbum,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Album album = arrayAllAlbum.get(position);
        holder.txtNameAlbum.setText(album.getNameAlbum());
        holder.txtSingerAlbum.setText(album.getNameSinger());
        Picasso.with(context).load(album.getPictureAlbum()).into(holder.imgPicture);
    }

    @Override
    public int getItemCount() {
        return arrayAllAlbum.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgPicture;
        TextView txtNameAlbum, txtSingerAlbum;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSingerAlbum = itemView.findViewById(R.id.txtNameSingerAlbumAll);
            txtNameAlbum = itemView.findViewById(R.id.txtNameListAlbumAll);
            imgPicture = itemView.findViewById(R.id.imgListAlbumAll);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ListSongActivity.class);
                    intent.putExtra("album",arrayAllAlbum.get(getPosition()));
                    context.startActivity(intent);
                }
            });

        }
    }
}
