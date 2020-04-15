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

import com.example.appmusicc.Activity.DanhSachTheLoaiTheoCDActivity;
import com.example.appmusicc.Model.ChuDe;
import com.example.appmusicc.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhSachTatCaChuDeAdaper extends RecyclerView.Adapter<DanhSachTatCaChuDeAdaper.ViewHolder> {
    Context context;
    ArrayList<ChuDe> arrayCacChuDe;

    public DanhSachTatCaChuDeAdaper(Context context, ArrayList<ChuDe> arrayCacChuDe) {
        this.context = context;
        this.arrayCacChuDe = arrayCacChuDe;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater  = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_cac_chu_de,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChuDe chuDe = arrayCacChuDe.get(position);
        holder.txtTen.setText(chuDe.getTenChuDe());
        Picasso.with(context).load(chuDe.getHinhchude()).into(holder.imgHinh);

    }

    @Override
    public int getItemCount() {
        return arrayCacChuDe.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgHinh;
        TextView txtTen;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHinh = itemView.findViewById(R.id.imgDSCacChuDe);
            txtTen  = itemView.findViewById(R.id.txtTenDSCacChuDe);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhSachTheLoaiTheoCDActivity.class);
                    intent.putExtra("theloaitheochude",arrayCacChuDe.get(getPosition()));
                    context.startActivity(intent);

                }
            });
        }
    }
}
