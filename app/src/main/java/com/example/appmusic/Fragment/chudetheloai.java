package com.example.appmusic.Fragment;

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

import com.example.appmusic.R;
import com.example.appmusic.Service.APIsever;
import com.example.appmusic.Service.dataservice;
import com.example.appmusic.activity.danhsachbaihat;
import com.example.appmusic.model.chudeModel;
import com.example.appmusic.model.chudevatheloai;
import com.example.appmusic.model.theloaiModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class chudetheloai extends Fragment {
     private  View view ;
      private HorizontalScrollView horizontalScrollView ;
      private TextView textView ;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.chudevatheloai,container,false) ;
        anhxa() ;
        getdata() ;

        return view ;
    }
    private void anhxa() {
       horizontalScrollView = view.findViewById(R.id.honticalview) ;
       textView = view.findViewById(R.id.textviuxemthem) ;
    }
    private void getdata() {
        APIsever apIsever = new APIsever() ;
        dataservice dataservice = apIsever.getsever() ;
        Call<chudevatheloai> callback = dataservice.getchudevatheloai() ;
        callback.enqueue(new Callback<chudevatheloai>() {
            @Override
            public void onResponse(Call<chudevatheloai> call, Response<chudevatheloai> response) {
           chudevatheloai chudevatheloai = response.body();
             final  ArrayList<chudeModel> chudeModels = new ArrayList<>() ;
             chudeModels.addAll(chudevatheloai.getChude()) ;
             final ArrayList<theloaiModel> theloaiModels = new ArrayList<>() ;
             theloaiModels.addAll(chudevatheloai.getTheloai());

                LinearLayout linearLayout = new LinearLayout(getActivity()) ;
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(600 ,300);
                layoutParams.setMargins(10,20,10,30);
                  for(int i = 0 ; i< chudeModels.size() ; i++ ) {
                      CardView cardView = new CardView(getActivity());
                      cardView.setRadius(10);
                      ImageView imageView = new ImageView(getActivity()) ;
                      imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                      if (chudeModels.get(i).getHinhchude() != null ) {
                          Picasso.with(getActivity()).load(chudeModels.get(i).getHinhchude()).into(imageView);
                      }
                      cardView.setLayoutParams(layoutParams);
                      cardView.addView(imageView);
                      linearLayout.addView(cardView);


                  }

                for(int j = 0 ; j< theloaiModels.size() ; j++ ) {
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity()) ;
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if (theloaiModels.get(j).getHinhtheloai() != null ) {
                        Picasso.with(getActivity()).load(theloaiModels.get(j).getHinhtheloai()).into(imageView);
                    }
                    cardView.setLayoutParams(layoutParams);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);
                    final int theloai = j ;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getActivity() , danhsachbaihat.class) ;
                            intent.putExtra("idtheloai" , theloaiModels.get(theloai) ) ;
                            startActivity(intent);
                        }
                    });
                }
                horizontalScrollView.addView(linearLayout);

            }

            @Override
            public void onFailure(Call<chudevatheloai> call, Throwable t) {

            }
        });

    }


}
