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
import com.example.appmusicc.Retrofit.APIService;
import com.example.appmusicc.Retrofit.DataServiec;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchSongAdapter extends RecyclerView.Adapter<SearchSongAdapter.ViewHolder>{
    Context context;
    ArrayList<Song> arrayBHSearch;

    public SearchSongAdapter(Context context, ArrayList<Song> arrayBHSearch) {
        this.context = context;
        this.arrayBHSearch = arrayBHSearch;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_searchsong,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Song song = arrayBHSearch.get(position);
        holder.txtName.setText(song.getNameSong());
        holder.txtSinger.setText(song.getMSinger());
        Picasso.with(context).load(song.getPictureSong()).into(holder.imgPicture);

    }

    @Override
    public int getItemCount() {
        return arrayBHSearch.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPicture,imgIcon;
        TextView txtName, txtSinger;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgPicture = itemView.findViewById(R.id.imgSearchBH);
            imgIcon = itemView.findViewById(R.id.imgIconLoveSearch);
            txtSinger = itemView.findViewById(R.id.txtNameSingerBHSearch);
            txtName = itemView.findViewById(R.id.txtNameSongSearch);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayMusicActivity.class);
                    intent.putExtra("playmusic",arrayBHSearch.get(getPosition()));
                    context.startActivity(intent);
                }
            });

            imgIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imgIcon.setImageResource(R.drawable.iconloved);
                    DataServiec data = APIService.getData();
                    Call<String> callBack = data.updateLike("1",arrayBHSearch.get(getPosition()).getIdSong());
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
                    imgIcon.setEnabled(false);
                }
            });
        }
    }
}
