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

import com.example.appmusic.Adaptor.paynhacAdaptor;
import com.example.appmusic.R;
import com.example.appmusic.activity.danhsachbaihat;

public class danhsachpaynhac extends Fragment {

    private View view ;
    private RecyclerView recyclerView ;

    private paynhacAdaptor paynhacAdaptor ;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.danhsachpay_nhac ,container ,false) ;
         anhxa() ;

         if(danhsachbaihat.baihatModels.size() > 0  ) {
             paynhacAdaptor = new paynhacAdaptor(getActivity() , danhsachbaihat.baihatModels) ;
             recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
             recyclerView.setAdapter(paynhacAdaptor);
         }
      return view ;
    }

    private void anhxa() {
        recyclerView = view.findViewById(R.id.ricai) ;
    }
}
