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
import androidx.viewpager.widget.PagerAdapter;

import com.example.appmusicc.Activity.DanhSachBaiHatQCActivity;
import com.example.appmusicc.Model.QuangCao;
import com.example.appmusicc.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BannerAdapter extends PagerAdapter {
    Context context;
    ArrayList<QuangCao> arrayBanner;

    public BannerAdapter(Context context, ArrayList<QuangCao> arrayBanner) {
        this.context = context;
        this.arrayBanner = arrayBanner;
    }

    @Override
    public int getCount() {
        return arrayBanner.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_banner,null);

        ImageView imgBgBanner = view.findViewById(R.id.imgBGBanner);
        ImageView imgSongBanner = view.findViewById(R.id.imgBanner);
        TextView txtTitleSongBanner = view.findViewById(R.id.txtBannerSong);
        TextView txtNoiDungBanner = view.findViewById(R.id.txtNoiDungBanner);

        Picasso.with(context).load(arrayBanner.get(position).getHinhanh()).into(imgBgBanner);
        Picasso.with(context).load(arrayBanner.get(position).getHinhBaiHat()).into(imgSongBanner);
        txtTitleSongBanner.setText(arrayBanner.get(position).getTenBaiHat());
        txtNoiDungBanner.setText(arrayBanner.get(position).getNoidung());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DanhSachBaiHatQCActivity.class);
                intent.putExtra("banner",arrayBanner.get(position));
                context.startActivity(intent);
            }
        });
        container.addView(view);
        return view;
    }

    //xóa view đã used
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
