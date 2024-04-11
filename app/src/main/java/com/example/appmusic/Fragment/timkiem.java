package com.example.appmusic.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmusic.Adaptor.timkiembaihatAdaptor;
import com.example.appmusic.R;
import com.example.appmusic.Service.APIsever;
import com.example.appmusic.Service.dataservice;
import com.example.appmusic.model.baihatModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class timkiem extends Fragment {
   private View view ;
   private RecyclerView recyclerView ;
   private Toolbar toolbar ;

   private  baihatModel baihatModel ;

   private ArrayList<baihatModel> baihatModels ;
   private timkiembaihatAdaptor timkiembaihatAdaptor ;


    public View onCreateView( LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.timkiem,container,false) ;
        recyclerView = view.findViewById(R.id.recaitimkiem);
        toolbar = view.findViewById(R.id.tootbatimkiem);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle("");
        setHasOptionsMenu(true);
        return view ;

    }

    private void getdata(String tukhoa) {
        APIsever apIsever = new APIsever() ;
        dataservice dataservice = apIsever.getsever() ;
        Call<List<baihatModel>> callback = dataservice.gettimkiembaihat(tukhoa) ;
        callback.enqueue(new Callback<List<com.example.appmusic.model.baihatModel>>() {
            @Override
            public void onResponse(Call<List<com.example.appmusic.model.baihatModel>> call, Response<List<com.example.appmusic.model.baihatModel>> response) {
                ArrayList<baihatModel> models = (ArrayList<baihatModel>) response.body();
               if(models.size() > 0 ) {
                   timkiembaihatAdaptor = new timkiembaihatAdaptor(getActivity(),models) ;
                   LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                   recyclerView.setLayoutManager(linearLayoutManager);
                   recyclerView.setAdapter(timkiembaihatAdaptor);
                   recyclerView.setVisibility(View.VISIBLE);
               }
            }

            @Override
            public void onFailure(Call<List<com.example.appmusic.model.baihatModel>> call, Throwable t) {

            }
        });

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menutimkiem , menu);
        MenuItem menuItem = menu.findItem(R.id.menutimk);
        SearchView searchView =  (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String tukhoa) {
                getdata(tukhoa);
                return true;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }
}
