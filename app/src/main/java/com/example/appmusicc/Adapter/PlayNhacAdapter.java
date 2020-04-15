package com.example.appmusicc.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmusicc.Activity.PlayNhacActivity;
import com.example.appmusicc.Model.BaiHat;
import com.example.appmusicc.R;

import java.util.ArrayList;

public class PlayNhacAdapter extends RecyclerView.Adapter<PlayNhacAdapter.ViewHolder>{
    Context context;
    ArrayList<BaiHat> arrayListBaiHat;

    public PlayNhacAdapter(Context context, ArrayList<BaiHat> arrayListBaiHat) {
        this.context = context;
        this.arrayListBaiHat = arrayListBaiHat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_play_nhac,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHat baiHat = arrayListBaiHat.get(position);
        holder.txtTenBH.setText(baiHat.getTenBaiHat());
        holder.txtCasi.setText(baiHat.getCaSi());
        holder.txtSTT.setText(position + 1 + "");

    }

    @Override
    public int getItemCount() {
        return arrayListBaiHat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtSTT,txtTenBH,txtCasi;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSTT   = itemView.findViewById(R.id.txtPlayNhacSTT);
            txtTenBH = itemView.findViewById(R.id.txtPlayNhacTenBH);
            txtCasi  = itemView.findViewById(R.id.txtPlayNhacTenCaSi);
        }
    }
}
