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

import com.example.appmusicc.Activity.DanhSachBaiHatQCActivity;
import com.example.appmusicc.Model.Album;
import com.example.appmusicc.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhSachCacAlbumAdapter extends RecyclerView.Adapter<DanhSachCacAlbumAdapter.ViewHolder> {
    Context context;
    ArrayList<Album> arrayAllAlbum;

    public DanhSachCacAlbumAdapter(Context context, ArrayList<Album> arrayAllAlbum) {
        this.context = context;
        this.arrayAllAlbum = arrayAllAlbum;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_danh_sach_cac_album,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Album album = arrayAllAlbum.get(position);
        holder.txtTenAlbum.setText(album.getTenAlbum());
        holder.txtCasiAlbum.setText(album.getTenCaSi());
        Picasso.with(context).load(album.getHinhAlbum()).into(holder.imgHinh);
    }

    @Override
    public int getItemCount() {
        return arrayAllAlbum.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgHinh;
        TextView txtTenAlbum,txtCasiAlbum;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCasiAlbum = itemView.findViewById(R.id.txtTenDSCaSiAlbumall);
            txtTenAlbum  = itemView.findViewById(R.id.txtTenDSCacAlbumall);
            imgHinh      = itemView.findViewById(R.id.imgDSCacAlbumall);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhSachBaiHatQCActivity.class);
                    intent.putExtra("album",arrayAllAlbum.get(getPosition()));
                    context.startActivity(intent);
                }
            });

        }
    }
}
