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
import com.example.appmusicc.Model.PlayList;
import com.example.appmusicc.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListOfPlayListsAdapter extends RecyclerView.Adapter<ListOfPlayListsAdapter.ViewHolder>{
    Context context;
    ArrayList<PlayList> arrayPlayList;

    public ListOfPlayListsAdapter(Context context, ArrayList<PlayList> arrayPlayList) {
        this.context = context;
        this.arrayPlayList = arrayPlayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_listofplaylists,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PlayList playList = arrayPlayList.get(position);
        holder.txtNameDSCPL.setText(playList.getMName());
        Picasso.with(context).load(playList.getMPicture()).into(holder.imgHinh);
      //  Picasso.with(context).load(playList.getHinhIcon()).into(holder.imgIcon);
    }

    @Override
    public int getItemCount() {
        return arrayPlayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgHinh,imgIcon;
        TextView txtNameDSCPL;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHinh     = itemView.findViewById(R.id.imgListOfPlayList);
            txtNameDSCPL = itemView.findViewById(R.id.txtNameOfPLayList);
            //imgIcon     = itemView.findViewById(R.id.imgIconDSCacPlayList);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ListSongActivity.class);
                    intent.putExtra("itemplaylist",arrayPlayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
