package com.example.appmusic.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appmusic.Adaptor.quangcaoAdaptor;
import com.example.appmusic.Service.APIsever;
import com.example.appmusic.Service.dataservice;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.appmusic.R;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.appmusic.model.quangcaoModel;
public class quangcao extends Fragment {
 private View view ;
 private ViewPager viewPager ;
 private CircleIndicator circleIndicator ;
  quangcaoAdaptor quangcaoAdaptor ;
  Runnable runnable ;
  Handler handler ;
private int vitri ;


    public quangcao() {

    }


    public View onCreateView( LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.quangcao,container,false) ;
        anhxa() ;
        getdata() ;

      return view ;
    }

    private void anhxa() {
        viewPager = view.findViewById(R.id.viepagequangcao) ;
        circleIndicator = view.findViewById(R.id.truotquangcao);
    }

    private void getdata() {
        APIsever apIsever = new APIsever() ;
      dataservice dataservice = apIsever.getsever() ;
        Call<List<quangcaoModel>> callback = dataservice.getdataquangcao() ;
        callback.enqueue(new Callback<List<quangcaoModel>>() {
            @Override
            public void onResponse(Call<List<quangcaoModel>> call, Response<List<quangcaoModel>> response) {
                ArrayList<quangcaoModel> baner = (ArrayList<quangcaoModel>) response.body();
                quangcaoAdaptor = new quangcaoAdaptor(getActivity(), baner) ;
                viewPager.setAdapter(quangcaoAdaptor);
                circleIndicator.setViewPager(viewPager);
              handler = new Handler();
              runnable = new Runnable() {
                  @Override
                  public void run() {
                  vitri = viewPager.getCurrentItem();
                  vitri++ ;
                  if(vitri >= viewPager.getAdapter().getCount()) {
                      vitri = 0 ;
                  }
                  viewPager.setCurrentItem(vitri,true);
                  handler.postDelayed(runnable,4500);
                  }
              };
              handler.postDelayed(runnable,4500);

            }

            @Override
            public void onFailure(Call<List<quangcaoModel>> call, Throwable t) {

            }
        });

    }
}
