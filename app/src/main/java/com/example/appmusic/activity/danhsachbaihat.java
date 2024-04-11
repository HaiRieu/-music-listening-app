package com.example.appmusic.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.appmusic.Adaptor.danhsachbaihatAdaptor;
import com.example.appmusic.R;
import com.example.appmusic.Service.APIsever;
import com.example.appmusic.Service.dataservice;
import com.example.appmusic.model.albumModel;
import com.example.appmusic.model.baihatModel;
import com.example.appmusic.model.playlistModel;
import com.example.appmusic.model.quangcaoModel;
import com.example.appmusic.model.theloaiModel;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
;import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class danhsachbaihat extends AppCompatActivity {

    private quangcaoModel model ;

    private albumModel albumModel ;
    private theloaiModel theloaiModel ;
    private playlistModel playlistModel ;
    private Toolbar toolbar ;
    private RecyclerView recyclerView ;
    private FloatingActionButton floatingActionButton ;

    private CoordinatorLayout coordinatorLayout ;
    private CollapsingToolbarLayout collapsingToolbarLayout ;
     public static ArrayList<baihatModel> baihatModels = new ArrayList<>()   ;
    private ImageView imagehinhanh ;
    public static danhsachbaihatAdaptor danhsachbaihatAdaptor ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachbaihat);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
         anhxa() ;
         datainter();
         luitrang() ;
         if(   model != null && !model.getTenbaihat().equals("")) {
             setValueiviu(model.getTenbaihat(),model.getHinhanh()) ;
             getdataquangcao( model.getIdquangcao()) ;
         }
         if( playlistModel != null && !playlistModel.getTen().equals("")) {
            setvaluplaylis(playlistModel.getTen() , playlistModel.getHinhicon()) ;
            getdataplaylist(playlistModel.getIdplaylist()) ;
         }

         if( theloaiModel != null && !theloaiModel.getTentheloai().equals("")) {
             settheloai(theloaiModel.getTentheloai() , theloaiModel.getHinhtheloai()) ;
             getdatatheloai(theloaiModel.getIdtheloai()) ;
         }
         if( albumModel != null && !albumModel.getTenalbum().equals("")) {
             setalbum(albumModel.getTenalbum() , albumModel.getHinhanhalbum()) ;
             getdataalbum(albumModel.getIdalbum());

         }
    }

    private void getdataalbum(String idalbum) {
        APIsever apIsever = new APIsever() ;
        dataservice dataservice = apIsever.getsever() ;
        Call<List<baihatModel>> callback = dataservice.getbaihattheoalbum(idalbum) ;
        callback.enqueue(new Callback<List<baihatModel>>() {
            @Override
            public void onResponse(Call<List<baihatModel>> call, Response<List<baihatModel>> response) {
                baihatModels = (ArrayList<baihatModel>) response.body() ;
                danhsachbaihatAdaptor = new danhsachbaihatAdaptor(danhsachbaihat.this , baihatModels);
                recyclerView.setLayoutManager(new LinearLayoutManager(danhsachbaihat.this));
                recyclerView.setAdapter(danhsachbaihatAdaptor);
                evenclick();
            }

            @Override
            public void onFailure(Call<List<baihatModel>> call, Throwable t) {

            }
        });

    }

    private void setalbum(String tenalbum, String hinhanhalbum) {
        collapsingToolbarLayout.setTitle(tenalbum);
        try {
            URL url = new URL (hinhanhalbum) ;
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream()) ;
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(),bitmap) ;
            collapsingToolbarLayout.setBackground(bitmapDrawable);
        }catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Picasso.with(this).load(hinhanhalbum).into(imagehinhanh);
    }


    private void getdatatheloai(String idtheloai) {
        APIsever apIsever = new APIsever() ;
        dataservice dataservice = apIsever.getsever() ;
        Call<List<baihatModel>> callback = dataservice.getbaihattheotheloai(idtheloai) ;
        callback.enqueue(new Callback<List<baihatModel>>() {
            @Override
            public void onResponse(Call<List<baihatModel>> call, Response<List<baihatModel>> response) {
                 baihatModels = (ArrayList<baihatModel>)  response.body() ;
                 danhsachbaihatAdaptor = new danhsachbaihatAdaptor(danhsachbaihat.this , baihatModels) ;
                 recyclerView.setLayoutManager(new LinearLayoutManager(danhsachbaihat.this ));
                 recyclerView.setAdapter(danhsachbaihatAdaptor);
                 evenclick();
            }

            @Override
            public void onFailure(Call<List<baihatModel>> call, Throwable t) {

            }
        });
    }

    private void settheloai(String tentheloai, String hinhtheloai) {
        collapsingToolbarLayout.setTitle(tentheloai);
        try {
            URL url = new URL (hinhtheloai) ;
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream()) ;
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(),bitmap) ;
            collapsingToolbarLayout.setBackground(bitmapDrawable);
        }catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Picasso.with(this).load(hinhtheloai).into(imagehinhanh);
    }


    private void getdataplaylist(String idplaylist) {
        APIsever apIsever = new APIsever() ;
        dataservice dataservice = apIsever.getsever() ;
        Call<List<baihatModel>> callback = dataservice.getplaylisttheobaihat(idplaylist);
        callback.enqueue(new Callback<List<baihatModel>>() {
          @Override
          public void onResponse(Call<List<baihatModel>> call, Response<List<baihatModel>> response) {
              baihatModels = (ArrayList<baihatModel>)  response.body() ;
              danhsachbaihatAdaptor = new danhsachbaihatAdaptor(danhsachbaihat.this , baihatModels);
              recyclerView.setLayoutManager(new LinearLayoutManager(danhsachbaihat.this));
              recyclerView.setAdapter(danhsachbaihatAdaptor);
              evenclick();
          }

          @Override
          public void onFailure(Call<List<baihatModel>> call, Throwable t) {

          }
      });



    }

    private void setvaluplaylis(String ten , String hinh) {
        collapsingToolbarLayout.setTitle(ten);
        try {
            URL url = new URL (hinh) ;
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream()) ;
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(),bitmap) ;
            collapsingToolbarLayout.setBackground(bitmapDrawable);
        }catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Picasso.with(this).load(hinh).into(imagehinhanh);
    }

    private void getdataquangcao( String idquangcao) {
        APIsever apIsever = new APIsever() ;
        dataservice dataservice = apIsever.getsever() ;
        Call<List<baihatModel>> callback =   dataservice.getbaihattheoquangcao(idquangcao) ;
        callback.enqueue(new Callback<List<baihatModel>>() {
            @Override
            public void onResponse(Call<List<baihatModel>> call, Response<List<baihatModel>> response) {
                baihatModels = (ArrayList<baihatModel>) response.body() ;
                danhsachbaihatAdaptor = new danhsachbaihatAdaptor(danhsachbaihat.this , baihatModels);
                recyclerView.setLayoutManager(new LinearLayoutManager(danhsachbaihat.this));
                recyclerView.setAdapter(danhsachbaihatAdaptor);
                evenclick();


            }

            @Override
            public void onFailure(Call<List<baihatModel>> call, Throwable t) {

            }
        });

    }

    private void setValueiviu( String ten , String hinhanh ) {
          collapsingToolbarLayout.setTitle(ten);
          try {
              URL url = new URL (hinhanh) ;
              Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream()) ;
              BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(),bitmap) ;

                  collapsingToolbarLayout.setBackground(bitmapDrawable);
          }catch (MalformedURLException e){
              e.printStackTrace();
          } catch (IOException e) {
             e.printStackTrace();
          }
        Picasso.with(this).load(hinhanh).into(imagehinhanh);


    }

    private void luitrang() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
       collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
       collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
       floatingActionButton.setEnabled(false);



    }

    private void anhxa() {
     toolbar = findViewById(R.id.tot) ;
     recyclerView = findViewById(R.id.reyybaihat);
     floatingActionButton = findViewById(R.id.loat_bh);
     coordinatorLayout = findViewById(R.id.coordinator) ;
     collapsingToolbarLayout = findViewById(R.id.coll) ;
     imagehinhanh = findViewById(R.id.image_bannhac) ;
    }

    private void datainter() {
        Intent intent = getIntent() ;
        if(intent != null ) {
            if(intent.hasExtra("qc" ) ){
                model = (quangcaoModel) intent.getSerializableExtra("qc");

            }
            if(intent.hasExtra("idplaylist")) {
                playlistModel = (playlistModel) intent.getSerializableExtra("idplaylist");

            }
            if(intent.hasExtra("idtheloai")) {
                theloaiModel = (theloaiModel) intent.getSerializableExtra("idtheloai");

            }
            if(intent.hasExtra("idalbum")) {
                albumModel = (albumModel) intent.getSerializableExtra("idalbum");

            }
        }
    }

    private void evenclick() {
        floatingActionButton.setEnabled(true);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(danhsachbaihat.this , paynhac.class) ;
                intent.putExtra("tatcacakhuc" , baihatModels) ;
                 startActivity(intent);
            }
        });
    }

}