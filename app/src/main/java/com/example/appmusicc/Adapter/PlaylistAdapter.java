package com.example.appmusicc.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appmusicc.Model.PlayList;
import com.example.appmusicc.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PlaylistAdapter extends ArrayAdapter<PlayList> {
    public PlaylistAdapter(@NonNull Context context, int resource, @NonNull List<PlayList> objects) {
        super(context, resource, objects);
    }
    class ViewHolder{
        TextView txtNamePlaylist;
        ImageView imgBG,imgView;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_playlist,null);

            viewHolder = new ViewHolder();
            viewHolder.txtNamePlaylist = convertView.findViewById(R.id.txtPlaylist);
            viewHolder.imgBG          = convertView.findViewById(R.id.imgBGPlaylist);
            viewHolder.imgView        = convertView.findViewById(R.id.imgViewPlaylist);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        PlayList playList = getItem(position);
        Picasso.with(getContext()).load(playList.getMPicture()).into(viewHolder.imgBG);
        Picasso.with(getContext()).load(playList.getIconPicture()).into(viewHolder.imgView);
        viewHolder.txtNamePlaylist.setText(playList.getMName());
        return convertView;
    }
}
