package com.example.appmusic.Fragment;

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

import com.example.appmusic.R;
import com.example.appmusic.activity.paynhac;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class dianhac  extends Fragment {
    private View view ;
    private CircleImageView cicle ;
    private ObjectAnimator objectAnimator ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dainhac , container , false) ;
        anhxa() ;
        objectAnimator =  ObjectAnimator.ofFloat(cicle , "rotation",0 ,360) ;
        objectAnimator.setDuration(15000);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.setRepeatMode(ValueAnimator.RESTART);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.start();



        return view;

    }

    private void anhxa() {
        cicle = view.findViewById(R.id.ciradia) ;

    }

    public void playMusic(String hinhbaihat) {
        Picasso.with(getActivity()).load(hinhbaihat).into(cicle);
    }
}
