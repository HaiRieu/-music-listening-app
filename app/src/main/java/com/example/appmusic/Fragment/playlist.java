package com.example.appmusic.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.appmusic.Adaptor.playlistAdaptor ;
import com.example.appmusic.Service.APIsever;
import com.example.appmusic.Service.dataservice ;
import com.example.appmusic.R;
import com.example.appmusic.activity.danhsachbaihat;
import com.example.appmusic.model.playlistModel;
import com.example.appmusic.util.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class playlist extends Fragment {
    private View view ;
    private ArrayList<playlist> playlists ;
    private ListView listView ;
    private TextView textView ;

    private playlistAdaptor playlistAdaptor ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.playlist , container,false) ;
        anhxa() ;
        getda() ;
        return  view ;
    }
    private void anhxa() {
        listView = view.findViewById(R.id.listvewplaylist) ;
        textView = view.findViewById(R.id.textviewplaylist);
        textView = view.findViewById(R.id.xemthemplaylist) ;

    }
    private void getda() {
           APIsever apIsever = new APIsever() ;
        dataservice dataservice =  apIsever.getsever() ;
        Call<List<playlistModel>> callback = dataservice.getdataplaylist() ;
      callback.enqueue(new Callback<List<playlistModel>>() {

          @Override
          public void onResponse(Call<List<playlistModel>> call, Response<List<playlistModel>> response) {
                    ArrayList<playlistModel> playlistModels = (ArrayList<playlistModel>)  response.body() ;
              playlistAdaptor = new playlistAdaptor(getActivity(), android.R.layout.simple_list_item_1,playlistModels) ;
              listView.setAdapter(playlistAdaptor);
              Utils.setListViewHeightBasedOnChildren(listView);

              listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                  @Override
                  public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                      Intent intent = new Intent(getActivity() , danhsachbaihat.class) ;
                      intent.putExtra("idplaylist" , playlistModels.get(i)) ;
                      startActivity(intent);
                  }
              });


          }

          @Override
          public void onFailure(Call<List<playlistModel>> call, Throwable t) {

          }
      });


    }


}
