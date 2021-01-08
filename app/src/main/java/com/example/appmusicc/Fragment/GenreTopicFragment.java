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

import com.example.appmusicc.Activity.ListSongActivity;
import com.example.appmusicc.Activity.ListTopicActivity;
import com.example.appmusicc.Activity.ListGenreOfTopicActivity;
import com.example.appmusicc.Model.Topic;
import com.example.appmusicc.Model.Genre;
import com.example.appmusicc.Model.DailyGenre;
import com.example.appmusicc.R;
import com.example.appmusicc.Retrofit.APIService;
import com.example.appmusicc.Retrofit.DataServiec;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GenreTopicFragment extends Fragment {
    View view;
    HorizontalScrollView horizontalScrollView;
    TextView txtXemThem;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frament_genre_topic,container,false);
        horizontalScrollView = view.findViewById(R.id.horizontalScrollview);
        txtXemThem           = view.findViewById(R.id.txtXemThemChuDeTheLoai);

        txtXemThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListTopicActivity.class);
                startActivity(intent);
            }
        });
        getData();
        return view ;
    }

    private void getData() {
        DataServiec data = APIService.getData();
        Call<DailyGenre> callBack = data.getTopicGenre();
        callBack.enqueue(new Callback<DailyGenre>() {
            @Override
            public void onResponse(final Call<DailyGenre> call, Response<DailyGenre> response) {
                DailyGenre dailyGenre = response.body();

                final ArrayList<Topic> arrayTopic = new ArrayList<>();
                arrayTopic.addAll(dailyGenre.getTopic());

                final ArrayList<Genre> arrayGenre = new ArrayList<>();
                arrayGenre.addAll(dailyGenre.getGenre());

                LinearLayout linearLayout  = new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);

                //set lai size anh
                LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(600,250);
                layout.setMargins(10,20,10,20);

                for(int i = 0; i < (arrayTopic.size()) ; i++){
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);

                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);

                    if(arrayTopic.get(i).getNameTopic() != null){
                        Picasso.with(getContext()).load(arrayTopic.get(i).getPictureTopic()).into(imageView);
                    }
                    cardView.setLayoutParams(layout);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);

                    final int finalI = i;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), ListGenreOfTopicActivity.class);
                            intent.putExtra("genretopic", arrayTopic.get(finalI));
                            startActivity(intent);
                        }
                    });
                }
                for(int j = 0; j < (arrayGenre.size()) ; j++){
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);

                    final ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);

                    if(arrayGenre.get(j).getNameGenre()!= null){
                        Picasso.with(getContext()).load(arrayGenre.get(j).getPictureGenre()).into(imageView);
                    }
                    cardView.setLayoutParams(layout);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);

                    final int finalJ = j;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), ListSongActivity.class);
                            intent.putExtra("idgenre", arrayGenre.get(finalJ));
                            startActivity(intent);
                        }
                    });
                }

                horizontalScrollView.addView(linearLayout);
            }

            @Override
            public void onFailure(Call<DailyGenre> call, Throwable t) {
                Log.d("bbb", t.getMessage());
            }
        });
    }
}
