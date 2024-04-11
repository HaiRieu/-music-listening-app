package com.example.appmusic.Adaptor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmusic.R;
import com.example.appmusic.Service.APIsever;
import com.example.appmusic.Service.dataservice;
import com.example.appmusic.activity.danhsachbaihat;
import com.example.appmusic.activity.paynhac;
import com.example.appmusic.model.baihatModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class baihathotAdaptor extends RecyclerView.Adapter<baihathotAdaptor.viewhodel>{
    private ArrayList<baihatModel> baihatModels ;
    private Context context ;



    public baihathotAdaptor(ArrayList<baihatModel> baihatModels, Context context) {
        this.baihatModels = baihatModels;
        this.context = context;
    }


    @NonNull
    @Override
    public viewhodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
     View view = inflater.inflate(R.layout.dong_baihat , parent , false );
        return new viewhodel(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull viewhodel holder, int position) {
         baihatModel model = baihatModels.get(position);
         holder.tencasi.setText(model.getCasi());
         holder.tenbaihat.setText(model.getTenbaihat());
        Picasso.with(context).load(model.getHinhbaihat()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return baihatModels.size();
    }

    public class viewhodel extends RecyclerView.ViewHolder {
        private TextView tenbaihat , tencasi ;
        private ImageView imageView , imagethich ;


        public viewhodel(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgae_baihathot);
            tenbaihat = itemView.findViewById(R.id.tenbaihat);
            tencasi = itemView.findViewById(R.id.tencasi);
            imagethich = itemView.findViewById(R.id.imgedethich) ;

           itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context , paynhac.class) ;
                    intent.putExtra("baihathot" , baihatModels.get(getPosition())) ;
                   context.startActivity(intent);
                }
            });

            imagethich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imagethich.setImageResource(R.drawable.timdo);
                    APIsever apIsever = new APIsever() ;
                    dataservice dataservice = apIsever.getsever() ;
                    Call<String> callback = dataservice.getlthemluotthich("1" , baihatModels.get(getPosition()).getIdbaihat() ) ;
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua = response.body() ;
                            if(ketqua.equals("ok")){
                                Toast.makeText(context,"Đã Thích" , Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(context,"Chưa Thích" , Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                    imagethich.setEnabled(false);
                }
            });

        }
    }
}
