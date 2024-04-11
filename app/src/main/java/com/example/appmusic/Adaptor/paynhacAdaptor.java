package com.example.appmusic.Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmusic.R;
import com.example.appmusic.model.baihatModel;

import java.util.ArrayList;

public class paynhacAdaptor extends RecyclerView.Adapter<paynhacAdaptor.viewhodel> {

    private Context context ;
    private ArrayList<baihatModel> baihatModels ;


    public paynhacAdaptor(Context context, ArrayList<baihatModel> baihatModels) {
        this.context = context;
        this.baihatModels = baihatModels;
    }

    @NonNull
    @Override
    public viewhodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_pay_nhac , parent,false);
        return new viewhodel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewhodel holder, int position) {
           baihatModel model = baihatModels.get(position);
           holder.tencasi.setText(model.getCasi());
           holder.tenbaihat.setText(model.getTenbaihat());
           holder.sott.setText(position + 1 + "");

    }

    @Override
    public int getItemCount() {
        return baihatModels.size();
    }

    public class viewhodel extends RecyclerView.ViewHolder{

        private TextView tenbaihat , tencasi  ,sott;

        public viewhodel(@NonNull View itemView) {
            super(itemView);
           tenbaihat = itemView.findViewById(R.id.texttbh) ;
           tencasi = itemView.findViewById(R.id.tencsds) ;
           sott = itemView.findViewById(R.id.textstt);



        }
    }
}
