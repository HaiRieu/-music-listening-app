package com.example.appmusic.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appmusic.Fragment.danhsachpaynhac;
import com.example.appmusic.Fragment.dianhac;
import  com.example.appmusic.model.baihatModel ;
import com.example.appmusic.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import com.example.appmusic.Adaptor.viupagePaynhac ;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class paynhac extends AppCompatActivity {

     MediaPlayer mediaPlayer ;
    private ViewPager viewPager ;
     private  Toolbar toolbar;
     private SeekBar seekBar ;
    private TextView giaychay ;

     viupagePaynhac paynhac1 ;
    private ImageButton chuyenbai , paynhac , luibai , lapbaihat ;

    public static danhsachpaynhac danhsachpaynhac  = new danhsachpaynhac();

    private dianhac dianhac ;

    private int postion = 0 ;
    private boolean laplai = false ;
    private boolean chuyenbaihat = false ;
    private boolean luibaihat = false ;

    public static ArrayList<baihatModel> baihatModels = new ArrayList<>() ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paynhac);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        getIntentData() ;
        anhxa() ;
        inintt() ;
       evenclick() ;

        }

    private void inintt() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WindowManager.LayoutParams params = getWindow().getAttributes();
                params.width = (int) (300*getResources().getDisplayMetrics().density) ;
                params.height = (int) (100*getResources().getDisplayMetrics().density) ;
                params.gravity = Gravity.BOTTOM | Gravity.END ;
                getWindow().setAttributes(params);
               // finish();
             //   mediaPlayer.stop();
                baihatModels.clear();
            }
        });
        toolbar.setTitleTextColor(Color.WHITE);
        danhsachpaynhac = new danhsachpaynhac() ;
        dianhac = new dianhac() ;
        paynhac1 = new viupagePaynhac(getSupportFragmentManager()) ;
        paynhac1.addFragment(danhsachpaynhac);
        paynhac1.addFragment(dianhac);
        viewPager.setAdapter(paynhac1);
        dianhac = (dianhac) paynhac1.getItem(1);
        if(  baihatModels.size() > 0 ){
            getSupportActionBar().setTitle(baihatModels.get(0).getTenbaihat());
            new playMp3().execute(baihatModels.get(0).getLinkbaihat()) ;
            paynhac.setImageResource(R.drawable.pause2);
        }
    }


    private void getIntentData() {

        Intent intent = getIntent() ;
        baihatModels.clear();
         if ( intent != null) {
             if(intent.hasExtra("baihathot")) {
                baihatModel baihatModel = intent.getParcelableExtra("baihathot");
                baihatModels.add(baihatModel);
             }
             if(intent.hasExtra("tatcacakhuc")) {
               ArrayList<baihatModel> models = intent.getParcelableArrayListExtra("tatcacakhuc");
                 baihatModels.addAll(models);
             }
         }

    }

    private void evenclick() {
      Handler handler = new Handler() ;
      handler.postDelayed(new Runnable() {
          @Override
          public void run() {
              if(paynhac1.getItem(1) != null )   {
               if(baihatModels.size() > 0 ) {
                   dianhac.playMusic(baihatModels.get(0).getHinhbaihat());
                   handler.removeCallbacks(this);
               }else {
                   handler.postDelayed(this , 300) ;
               }
              }
          }
      },500);
      paynhac.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              if(mediaPlayer.isPlaying()) {
                  mediaPlayer.pause();
                  paynhac.setImageResource(R.drawable.play);
              }else {
                  mediaPlayer.start();
                  paynhac.setImageResource(R.drawable.pause2);
              }
          }
      });

      lapbaihat.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            if(laplai == false) {
                 lapbaihat.setImageResource(R.drawable.lapbai);
                 laplai = true ;

            }else {
                lapbaihat.setImageResource(R.drawable.repeat);
                laplai = false ;

            }
          }
      });

      seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
          @Override
          public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

          }

          @Override
          public void onStartTrackingTouch(SeekBar seekBar) {

          }

          @Override
          public void onStopTrackingTouch(SeekBar seekBar) {
          mediaPlayer.seekTo(seekBar.getProgress());

          }
      });

      chuyenbai.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              if(baihatModels.size() > 0 || baihatModels != null ){
                     if(mediaPlayer.isPlaying()) {
                         mediaPlayer.stop();
                         mediaPlayer.release();

                         if(postion <= baihatModels.size()) {
                             paynhac.setImageResource(R.drawable.pause2);
                             postion ++ ;
                             if(laplai == true ) {
                                 if(postion == 0 ) {
                                     postion = baihatModels.size() ;

                                 }
                                 postion -= 1 ;
                             }


                         }

                         new playMp3().execute(baihatModels.get(postion).getLinkbaihat());
                         toolbar.setTitle(baihatModels.get(postion).getTenbaihat());
                         dianhac.playMusic(baihatModels.get(postion).getHinhbaihat());
                         update ();
                     }



              }
              luibai.setClickable(false);
              chuyenbai.setClickable(false);
              Handler handler1 = new Handler() ;
              handler1.postDelayed(new Runnable() {
                  @Override
                  public void run() {
                      luibai.setClickable(true);
                      chuyenbai.setClickable(true);
                  }
              },500) ;
          }
      });

      luibai.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              if(baihatModels.size() > 0 || baihatModels != null ){
                  if(mediaPlayer.isPlaying()) {
                       mediaPlayer.stop();
                       mediaPlayer.release();
                      if(postion <=  baihatModels.size()) {
                          paynhac.setImageResource(R.drawable.pause2);
                          postion-- ;
                      }
                      if(postion < 0 ) {
                          postion = baihatModels.size() - 1  ;
                      }
                      if(laplai == true) {
                          postion += 1 ;
                      }
                   }
                  new playMp3().execute(baihatModels.get(postion).getLinkbaihat());
                  toolbar.setTitle(baihatModels.get(postion).getTenbaihat());
                  dianhac.playMusic(baihatModels.get(postion).getHinhbaihat());
                  update ();
              }
          }


      });


    }


    private void anhxa() {
        lapbaihat = findViewById(R.id.lapbai);
      giaychay = findViewById(R.id.giaychay) ;
      chuyenbai = findViewById(R.id.chyenbai);
      luibai = findViewById(R.id.luibai);
      paynhac = findViewById(R.id.phatnhac) ;
       seekBar = findViewById(R.id.thanhnhac) ;
       viewPager = findViewById(R.id.viupage);
       toolbar = findViewById(R.id.totbapaynhac) ;

    }

     class playMp3 extends AsyncTask<String , Void , String > {


        @Override
        protected String doInBackground(String... strings) {
            return strings[0];

        }

        @Override
        protected void onPostExecute(String baihat) {
            super.onPostExecute(baihat);
            try {
                 mediaPlayer = new MediaPlayer() ;
                 mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                 mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                     @Override
                     public void onCompletion(MediaPlayer mediaPlayer) {
                         mediaPlayer.stop();
                         mediaPlayer.reset();


                     }
                 });

                mediaPlayer.setDataSource(baihat);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
        mediaPlayer.start();
           timesong();
            update();

        }
    }

    private void timesong () {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        seekBar.setMax(mediaPlayer.getDuration());
    }

    private void update () {
        Handler handler = new Handler() ;
        handler.postDelayed(new Runnable() {
            @Override
            public void run(){
                if(mediaPlayer != null ) {
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss") ;
                    giaychay.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this , 300) ;
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            chuyenbaihat = true ;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }

                        }


                    });
                }
            }
        },300) ;

        Handler handler1 = new Handler() ;
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                 if(chuyenbaihat == true ) {
                     if(postion <= baihatModels.size()) {
                         paynhac.setImageResource(R.drawable.pause2);
                         postion ++ ;
                         if(laplai == true ) {
                             if(postion == 0 ) {
                                 postion = baihatModels.size() ;
                             }
                             postion -= 1 ;
                         }
                     }

                     new playMp3().execute(baihatModels.get(postion).getLinkbaihat());
                     toolbar.setTitle(baihatModels.get(postion).getTenbaihat());
                     dianhac.playMusic(baihatModels.get(postion).getHinhbaihat());
                     chuyenbaihat = false ;
                     handler1.removeCallbacks(this);
                 }else {
                     handler1.postDelayed(this,1000) ;
                 }
            }
        },1000);

    }




}