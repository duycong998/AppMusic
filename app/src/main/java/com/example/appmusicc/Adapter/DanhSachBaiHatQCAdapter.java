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
import com.example.appmusicc.Model.PlayList;
import com.example.appmusicc.R;
import com.example.appmusicc.Service.APIService;
import com.example.appmusicc.Service.DataServiec;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachBaiHatQCAdapter extends RecyclerView.Adapter<DanhSachBaiHatQCAdapter.ViewHolder> {
    Context context;
    ArrayList<BaiHat> mangBH;

    public DanhSachBaiHatQCAdapter(Context context, ArrayList<BaiHat> mangBH) {
        this.context = context;
        this.mangBH = mangBH;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_danh_sach_bai_hat_qc,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHat baiHat = mangBH.get(position);
        holder.txtSTT.setText(position + 1 + "");
        holder.txtTenBH.setText(baiHat.getTenBaiHat());
        holder.txtCaSi.setText(baiHat.getCaSi());

    }

    @Override
    public int getItemCount() {
        return mangBH.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtSTT,txtTenBH,txtCaSi;
        ImageView imgLThich;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSTT = itemView.findViewById(R.id.txtSTTDanhSachBH);
            txtCaSi = itemView.findViewById(R.id.txtSTTTenCaSiDSBH);
            txtTenBH = itemView.findViewById(R.id.txtSTTTenDSBH);
            imgLThich = itemView.findViewById(R.id.imgSTTLuotthichDSBHYT);
            imgLThich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imgLThich.setImageResource(R.drawable.iconloved);
                    DataServiec data = APIService.getData();
                    Call<String> callBack = data.updateLuotThich("1",mangBH.get(getPosition()).getIdBaiHat());
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
                    imgLThich.setEnabled(false);
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("playnhac",mangBH.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
