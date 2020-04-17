package com.example.appmusicc.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.appmusicc.Adapter.BannerAdapter;
import com.example.appmusicc.Model.Advertisement;
import com.example.appmusicc.R;
import com.example.appmusicc.Service.APIService;
import com.example.appmusicc.Service.DataServiec;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BannerFragment extends Fragment {
    View view;
    ViewPager viewPager;
    CircleIndicator circleIndicator;
    BannerAdapter bannerAdapter;
    Runnable runnable;
    Handler handler;
    int currentItem;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_banner,container,false);
        initView();
        getData();
        return view;
    }

    private void initView() {
        viewPager = view.findViewById(R.id.myViewPagerBanner);
        circleIndicator = view.findViewById(R.id.myCircleBanner);
    }

    private void getData() {
        DataServiec data = APIService.getData();
        Call<List<Advertisement>> callBack  = data.getDataBanner();
        callBack.enqueue(new Callback<List<Advertisement>>() {
            @Override
            public void onResponse(Call<List<Advertisement>> call, Response<List<Advertisement>> response) {
                ArrayList<Advertisement> arrayBanner = (ArrayList<Advertisement>) response.body();
                bannerAdapter = new BannerAdapter(getActivity(),arrayBanner);
                viewPager.setAdapter(bannerAdapter);
                circleIndicator.setViewPager(viewPager);
                // chuyen dong
                handler = new Handler();
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        currentItem = viewPager.getCurrentItem();
                        currentItem++;
                        if(currentItem >= viewPager.getAdapter().getCount()){
                            currentItem = 0;
                        }
                        viewPager.setCurrentItem(currentItem,true);
                        handler.postDelayed(runnable,4000);
                    }
                };
                handler.postDelayed(runnable,4000);
            }

            @Override
            public void onFailure(Call<List<Advertisement>> call, Throwable t) {

            }
        });
    }
}
