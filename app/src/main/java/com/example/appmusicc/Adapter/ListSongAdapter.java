package com.example.appmusicc.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmusicc.Activity.PlayMusicActivity;
import com.example.appmusicc.Model.Song;
import com.example.appmusicc.R;
import com.example.appmusicc.Service.APIService;
import com.example.appmusicc.Service.DataServiec;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListSongAdapter extends RecyclerView.Adapter<ListSongAdapter.ViewHolder> {
    Context context;
    ArrayList<Song> arraySong;

    public ListSongAdapter(Context context, ArrayList<Song> arraySong) {
        this.context = context;
        this.arraySong = arraySong;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_listsong,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Song song = arraySong.get(position);
        holder.txtIndex.setText(position + 1 + "");
        holder.txtNameSong.setText(song.getNameSong());
        holder.txtSinger.setText(song.getMSinger());

    }

    @Override
    public int getItemCount() {
        return arraySong.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtIndex, txtNameSong, txtSinger;
        ImageView imgLike;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtIndex = itemView.findViewById(R.id.txtIndexListSong);
            txtSinger = itemView.findViewById(R.id.txtNameSingerListSong);
            txtNameSong = itemView.findViewById(R.id.txtNameListSong);
            imgLike = itemView.findViewById(R.id.imgLikeListSong);
            imgLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imgLike.setImageResource(R.drawable.iconloved);
                    DataServiec data = APIService.getData();
                    Call<String> callBack = data.updateLike("1", arraySong.get(getPosition()).getIdSong());
                    callBack.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua = response.body();
                            if(ketqua.equals("success")){
                                Toast.makeText(context, "Đã Thích", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(context, "Lỗi", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                    imgLike.setEnabled(false);
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayMusicActivity.class);
                    intent.putExtra("playmusic", arraySong.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
