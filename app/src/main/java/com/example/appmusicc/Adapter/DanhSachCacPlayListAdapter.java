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

import com.example.appmusicc.Activity.DanhSachBaiHatQCActivity;
import com.example.appmusicc.Model.PlayList;
import com.example.appmusicc.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhSachCacPlayListAdapter extends RecyclerView.Adapter<DanhSachCacPlayListAdapter.ViewHolder>{
    Context context;
    ArrayList<PlayList> arrayPlayList;

    public DanhSachCacPlayListAdapter(Context context, ArrayList<PlayList> arrayPlayList) {
        this.context = context;
        this.arrayPlayList = arrayPlayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_danh_sach_cac_playlist,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PlayList playList = arrayPlayList.get(position);
        holder.txtTenDSCPL.setText(playList.getTen());
        Picasso.with(context).load(playList.getHinhNen()).into(holder.imgHinh);
      //  Picasso.with(context).load(playList.getHinhIcon()).into(holder.imgIcon);
    }

    @Override
    public int getItemCount() {
        return arrayPlayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgHinh,imgIcon;
        TextView txtTenDSCPL;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHinh     = itemView.findViewById(R.id.imgDSCacPlayList);
            txtTenDSCPL = itemView.findViewById(R.id.txtTenDSCacPLayList);
            //imgIcon     = itemView.findViewById(R.id.imgIconDSCacPlayList);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhSachBaiHatQCActivity.class);
                    intent.putExtra("itemplaylist",arrayPlayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
