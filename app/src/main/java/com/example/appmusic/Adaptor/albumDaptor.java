package com.example.appmusic.Adaptor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmusic.R;
import com.example.appmusic.activity.danhsachbaihat;
import com.example.appmusic.model.albumModel;
import com.example.appmusic.model.quangcaoModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class albumDaptor  extends RecyclerView.Adapter<albumDaptor.viewhodel>  {


    private Context context ;
    private ArrayList<albumModel> albumModels ;

    public albumDaptor(Context context, ArrayList<albumModel> albumModels) {
        this.context = context;
        this.albumModels = albumModels;
    }

    @NonNull
    @Override
    public viewhodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_album,parent ,false) ;
        return new viewhodel(view);

    }

    @Override
    public void onBindViewHolder(@NonNull viewhodel holder, int position) {
       albumModel model = albumModels.get(position);
       holder.textViewcs.setText(model.getTencasialbum());
       holder.textView.setText(model.getTenalbum());
        Picasso.with(context).load(model.getHinhanhalbum()).into(holder.imageButton);

    }

    @Override
    public int getItemCount() {
        return albumModels.size();
    }

    public class viewhodel extends RecyclerView.ViewHolder {
        private ImageView imageButton ;
        private TextView textView, textViewcs ;
    public viewhodel(@NonNull View itemView) {
        super(itemView);
        imageButton = itemView.findViewById(R.id.imagebuton_album) ;
        textView = itemView.findViewById(R.id.text_tenALbum);
        textViewcs = itemView.findViewById(R.id.txt_tencsalbum) ;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context , danhsachbaihat.class) ;
                intent.putExtra("idalbum" , albumModels.get(getPosition())) ;
                context.startActivity(intent);
            }
        });
    }
}
        }