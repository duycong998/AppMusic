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
import com.example.appmusicc.Model.Genre;
import com.example.appmusicc.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListGenreOfTopicAdapter extends RecyclerView.Adapter<ListGenreOfTopicAdapter.ViewHolder> {
    Context context;
    ArrayList<Genre> arrayGenreOfTopic;

    public ListGenreOfTopicAdapter(Context context, ArrayList<Genre> arrayGenreOfTopic) {
        this.context = context;
        this.arrayGenreOfTopic = arrayGenreOfTopic;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_listgenreoftopic,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Genre genre = arrayGenreOfTopic.get(position);
        holder.txtGenre.setText(genre.getNameGenre());
        Picasso.with(context).load(genre.getPictureGenre()).into(holder.imgPicture);
    }

    @Override
    public int getItemCount() {
        return arrayGenreOfTopic.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgPicture;
        TextView txtGenre;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPicture = itemView.findViewById(R.id.imgGenreOfTopic);
            txtGenre = itemView.findViewById(R.id.txtNameGenreOfTopic);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ListSongActivity.class);
                    intent.putExtra("idgenre", arrayGenreOfTopic.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
