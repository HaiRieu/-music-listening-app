package com.example.appmusic.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmusic.Adaptor.albumDaptor;
import com.example.appmusic.R;
import com.example.appmusic.Service.APIsever;
import com.example.appmusic.Service.dataservice;
import com.example.appmusic.activity.danhsachbaihat;
import com.example.appmusic.model.albumModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class album extends Fragment {

    private View view ;

    private RecyclerView recyclerView ;
    private TextView textView ;

    private albumDaptor  albumDaptor ;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.album,container ,false);
        anhxa() ;
        getdata() ;
        return view;
    }
    private void anhxa() {
       recyclerView = view.findViewById(R.id.recyview);
       textView = view.findViewById(R.id.textviuxemthemalbum);

    }
    private void getdata() {
        APIsever apIsever = new APIsever() ;
        dataservice dataservice = apIsever.getsever() ;
        Call<List<albumModel>> callback = dataservice.getalbum() ;
        callback.enqueue(new Callback<List<albumModel>>() {
            @Override
            public void onResponse(Call<List<albumModel>> call, Response<List<albumModel>> response) {
                ArrayList<albumModel> albumModels =  (ArrayList<albumModel>) response.body();
                 albumDaptor = new albumDaptor(getActivity() , albumModels) ;
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(albumDaptor);


            }

            @Override
            public void onFailure(Call<List<albumModel>> call, Throwable t) {

            }
        });
    }


}
