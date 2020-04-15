package com.example.appmusicc.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.appmusicc.Activity.DanhSachBaiHatQCActivity;
import com.example.appmusicc.Activity.DanhSachCacChuDeActivity;
import com.example.appmusicc.Activity.DanhSachTheLoaiTheoCDActivity;
import com.example.appmusicc.Model.ChuDe;
import com.example.appmusicc.Model.TheLoai;
import com.example.appmusicc.Model.TheLoaiTrongNgay;
import com.example.appmusicc.R;
import com.example.appmusicc.Service.APIService;
import com.example.appmusicc.Service.DataServiec;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_ChuDe_TheLoai extends Fragment {
    View view;
    HorizontalScrollView horizontalScrollView;
    TextView txtXemThem;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frament_chude_theloai,container,false);
        horizontalScrollView = view.findViewById(R.id.horizontalScrollview);
        txtXemThem           = view.findViewById(R.id.txtXemThemChuDeTheLoai);

        txtXemThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DanhSachCacChuDeActivity.class);
                startActivity(intent);
            }
        });
        getData();
        return view ;
    }

    private void getData() {
        DataServiec data = APIService.getData();
        Call<TheLoaiTrongNgay> callBack = data.getChuDeTheLoai();
        callBack.enqueue(new Callback<TheLoaiTrongNgay>() {
            @Override
            public void onResponse(final Call<TheLoaiTrongNgay> call, Response<TheLoaiTrongNgay> response) {
                TheLoaiTrongNgay theLoaiTrongNgay = response.body();

                final ArrayList<ChuDe> arrayChuDe = new ArrayList<>();
                arrayChuDe.addAll(theLoaiTrongNgay.getChuDe());

                final ArrayList<TheLoai> arrayTheLoai = new ArrayList<>();
                arrayTheLoai.addAll(theLoaiTrongNgay.getTheLoai());

                LinearLayout linearLayout  = new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);

                //set lai size anh
                LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(600,250);
                layout.setMargins(10,20,10,20);

                for(int i = 0 ; i < (arrayChuDe.size()) ; i++){
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);

                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);

                    if(arrayChuDe.get(i).getTenChuDe() != null){
                        Picasso.with(getContext()).load(arrayChuDe.get(i).getHinhchude()).into(imageView);
                    }
                    cardView.setLayoutParams(layout);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);

                    final int finalI = i;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), DanhSachTheLoaiTheoCDActivity.class);
                            intent.putExtra("theloaitheochude",arrayChuDe.get(finalI));
                            startActivity(intent);
                        }
                    });
                }
                for(int j = 0 ; j < (arrayTheLoai.size()) ; j++){
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);

                    final ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);

                    if(arrayTheLoai.get(j).getTenTheLoai() != null){
                        Picasso.with(getContext()).load(arrayTheLoai.get(j).getHinhTheLoai()).into(imageView);
                    }
                    cardView.setLayoutParams(layout);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);

                    final int finalJ = j;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), DanhSachBaiHatQCActivity.class);
                            intent.putExtra("idtheloai",arrayTheLoai.get(finalJ));
                            startActivity(intent);
                        }
                    });
                }

                horizontalScrollView.addView(linearLayout);
            }

            @Override
            public void onFailure(Call<TheLoaiTrongNgay> call, Throwable t) {
                Log.d("bbb", t.getMessage());
            }
        });
    }
}
