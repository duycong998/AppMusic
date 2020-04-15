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

public class SearchBaiHatAdapter extends RecyclerView.Adapter<SearchBaiHatAdapter.ViewHolder>{
    Context context;
    ArrayList<BaiHat> arrayBHSearch;

    public SearchBaiHatAdapter(Context context, ArrayList<BaiHat> arrayBHSearch) {
        this.context = context;
        this.arrayBHSearch = arrayBHSearch;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_search_bai_hat,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHat baiHat = arrayBHSearch.get(position);
        holder.txtTen.setText(baiHat.getTenBaiHat());
        holder.txtCaSi.setText(baiHat.getCaSi());
        Picasso.with(context).load(baiHat.getHinhBaiHat()).into(holder.imgHinh);

    }

    @Override
    public int getItemCount() {
        return arrayBHSearch.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHinh,imgIcon;
        TextView txtTen,txtCaSi;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgHinh = itemView.findViewById(R.id.imgSearchBH);
            imgIcon = itemView.findViewById(R.id.imgIconLoveSearch);
            txtCaSi = itemView.findViewById(R.id.txtTenCaSiBHSearch);
            txtTen  = itemView.findViewById(R.id.txtTenBHSearch);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("playnhac",arrayBHSearch.get(getPosition()));
                    context.startActivity(intent);
                }
            });

            imgIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imgIcon.setImageResource(R.drawable.iconloved);
                    DataServiec data = APIService.getData();
                    Call<String> callBack = data.updateLuotThich("1",arrayBHSearch.get(getPosition()).getIdBaiHat());
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
                    imgIcon.setEnabled(false);
                }
            });
        }
    }
}
