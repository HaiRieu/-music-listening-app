package com.example.appmusic.Adaptor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmusic.R;
import com.example.appmusic.Service.APIsever;
import com.example.appmusic.Service.dataservice;
import com.example.appmusic.activity.paynhac;
import com.example.appmusic.model.baihatModel;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class danhsachbaihatAdaptor extends RecyclerView.Adapter<danhsachbaihatAdaptor.viewhoel> {

    private Context context ;
   private  ArrayList<baihatModel> baihatModels ;

    public danhsachbaihatAdaptor(Context context, ArrayList<baihatModel> baihatModels) {
        this.context = context;
        this.baihatModels = baihatModels;
    }

    @NonNull
    @Override
    public viewhoel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater =  LayoutInflater.from(context) ;
        View view = inflater.inflate(R.layout.dongdanhsach_bh, parent , false) ;
        return new viewhoel(view) ;

    }

    @Override
    public void onBindViewHolder(@NonNull viewhoel holder, int position) {
        baihatModel model = baihatModels.get(position);
        holder.tenbaihat.setText(model.getTenbaihat());
        holder.tencasi.setText(model.getCasi());
        holder.sott.setText(position + 1 + "");


    }

    @Override
    public int getItemCount() {
        return baihatModels.size();
    }

    public class viewhoel extends RecyclerView.ViewHolder {

        private TextView tenbaihat , tencasi , sott;
        private ImageView luotthich ;


        public viewhoel(@NonNull View itemView) {
            super(itemView);

            tenbaihat = itemView.findViewById(R.id.tenbaihatds);
            tencasi = itemView.findViewById(R.id.tencsds);
            luotthich = itemView.findViewById(R.id.imagetim);
            sott = itemView.findViewById(R.id.testviuds) ;
            luotthich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    luotthich.setImageResource(R.drawable.timdo);
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
                    luotthich.setEnabled(false);

                }
            });


        }
    }

}
