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

import com.example.appmusicc.Activity.PlayNhacActivity;
import com.example.appmusicc.Model.BaiHat;
import com.example.appmusicc.R;
import com.example.appmusicc.Service.APIService;
import com.example.appmusicc.Service.DataServiec;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaiHatYTAdapter extends RecyclerView.Adapter<BaiHatYTAdapter.ViewHolder> {
    Context context;
    ArrayList<BaiHat> arrayBaiHat;

    public BaiHatYTAdapter(Context context, ArrayList<BaiHat> arrayBaiHat) {
        this.context = context;
        this.arrayBaiHat = arrayBaiHat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_bai_hat_yt,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHat baiHat = arrayBaiHat.get(position);
        holder.txtTen.setText(baiHat.getTenBaiHat());
        holder.txtCasi.setText(baiHat.getCaSi());
        Picasso.with(context).load(baiHat.getHinhBaiHat()).into(holder.imgHinh);
    }

    @Override
    public int getItemCount() {
        return arrayBaiHat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTen,txtCasi;
        ImageView imgHinh,imgLuotThich;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCasi      = itemView.findViewById(R.id.txtTenCaSiBHYT);
            txtTen       = itemView.findViewById(R.id.txtTenBaiHatYT);
            imgHinh      = itemView.findViewById(R.id.imgBaiHatYT);
            imgLuotThich = itemView.findViewById(R.id.imgLuotLike);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("playnhac",arrayBaiHat.get(getPosition()));
                    context.startActivity(intent);
                }
            });

            imgLuotThich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imgLuotThich.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            imgLuotThich.setImageResource(R.drawable.iconloved);
                            DataServiec data = APIService.getData();
                            Call<String> callBack = data.updateLuotThich("1",arrayBaiHat.get(getPosition()).getIdBaiHat());
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
                            imgLuotThich.setEnabled(false);
                        }
                    });
                   // Toast.makeText(context, arrayBaiHat.get(getPosition()).getTenBaiHat() +"", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
