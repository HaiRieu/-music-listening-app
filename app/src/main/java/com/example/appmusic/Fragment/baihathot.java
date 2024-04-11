package com.example.appmusic.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmusic.Adaptor.baihathotAdaptor;
import com.example.appmusic.R;
import com.example.appmusic.Service.APIsever;
import com.example.appmusic.Service.dataservice;
import com.example.appmusic.model.baihatModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class baihathot extends Fragment {

    private View view ;
    private RecyclerView recyclerView ;

    private baihathotAdaptor baihathotAdaptor ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.baihathot,container,false) ;
       anhxa() ;
       getdata() ;
       return view ;
    }

    private void anhxa() {
        recyclerView = view.findViewById(R.id.reybaihathot);

    }
    private void getdata() {
        APIsever apIsever = new APIsever() ;
        dataservice dataservice = apIsever.getsever() ;
        Call<List<baihatModel>> callback = dataservice.getbaihat();
        callback.enqueue(new Callback<List<baihatModel>>() {
            @Override
            public void onResponse(Call<List<baihatModel>> call, Response<List<baihatModel>> response) {
                ArrayList<baihatModel> baihatModels = (ArrayList<baihatModel>)  response.body() ;
                  baihathotAdaptor = new baihathotAdaptor( baihatModels , getActivity() ) ;
                LinearLayoutManager linearLayoutManager =  new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(baihathotAdaptor);
            }

            @Override
            public void onFailure(Call<List<baihatModel>> call, Throwable t) {

            }
        });

    }


}
