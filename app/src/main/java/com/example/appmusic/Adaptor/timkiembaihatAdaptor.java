package com.example.appmusic.Adaptor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmusic.R;
import com.example.appmusic.activity.paynhac;

import com.example.appmusic.model.baihatModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class timkiembaihatAdaptor extends RecyclerView.Adapter<timkiembaihatAdaptor.viewhodel> {
         private Context context ;
         private ArrayList<baihatModel> baihatModels ;

    public timkiembaihatAdaptor(Context context, ArrayList<baihatModel> baihatModels) {
        this.context = context;
        this.baihatModels = baihatModels;
    }

    @NonNull
    @Override
    public viewhodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.dongtimkiem_baihat,parent,false ) ;

        return new viewhodel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewhodel holder, int position) {
     baihatModel model = baihatModels.get(position);
      holder.tenbaihat.setText(model.getTenbaihat());
      holder.tencasi.setText(model.getCasi());
      Picasso.with(context).load(model.getHinhbaihat()).into(holder.hinhbai);

    }

    @Override
    public int getItemCount() {
        if(baihatModels != null) {
            return baihatModels.size();
        }else {
            return  0 ;
        }

    }

    public class  viewhodel extends RecyclerView.ViewHolder {
        private TextView tenbaihat , tencasi ;
        private ImageView hinhbai ;

        public  viewhodel(@NonNull View itemView) {
            super(itemView);
            tenbaihat = itemView.findViewById(R.id.tenbhtk);
            tencasi = itemView.findViewById(R.id.tencstk);
            hinhbai = itemView.findViewById(R.id.hinhbhtk);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context , paynhac.class) ;
                    intent.putExtra("baihathot",baihatModels.get(getPosition()));
                    context.startActivity(intent);
                }
            });



        }
    }

}
