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
import com.example.appmusicc.Model.TheLoai;
import com.example.appmusicc.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhSachTheLoaiTheoCDAdapter extends RecyclerView.Adapter<DanhSachTheLoaiTheoCDAdapter.ViewHolder> {
    Context context;
    ArrayList<TheLoai> arrayTheLoaiTheoCD;

    public DanhSachTheLoaiTheoCDAdapter(Context context, ArrayList<TheLoai> arrayTheLoaiTheoCD) {
        this.context = context;
        this.arrayTheLoaiTheoCD = arrayTheLoaiTheoCD;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_the_loai_theo_cd,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TheLoai theLoai = arrayTheLoaiTheoCD.get(position);
        holder.txtTheLoai.setText(theLoai.getTenTheLoai());
        Picasso.with(context).load(theLoai.getHinhTheLoai()).into(holder.imgHinh);
    }

    @Override
    public int getItemCount() {
        return arrayTheLoaiTheoCD.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgHinh;
        TextView txtTheLoai;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHinh       = itemView.findViewById(R.id.imgTheLoaiTheoCD);
            txtTheLoai    = itemView.findViewById(R.id.txtTenTheLoaiTheoCD);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhSachBaiHatQCActivity.class);
                    intent.putExtra("idtheloai",arrayTheLoaiTheoCD.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
