package com.example.appmusicc.Fragment;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appmusicc.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class AnimationFragment extends Fragment {
    View view;
    CircleImageView circleImageView;
   public static ObjectAnimator objectAnimator;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_animation,container,false);
        circleImageView = view.findViewById(R.id.imgCircleDiaNhac);
        objectAnimator  = ObjectAnimator.ofFloat(circleImageView,"rotation",0f,360f);
        objectAnimator.setDuration(10000);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);//lặp lại
        objectAnimator.setRepeatMode(ValueAnimator.RESTART);
        objectAnimator.setInterpolator(new LinearInterpolator());// no delay khi chạy lại
        objectAnimator.start();
        return view;
    }
    public void playNhac(String hinhanh) {
        Picasso.with(getActivity()).load(hinhanh).into(circleImageView);
    }
}
