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
import androidx.viewpager.widget.PagerAdapter;

import com.example.appmusic.R;
import com.example.appmusic.activity.danhsachbaihat;
import com.example.appmusic.model.quangcaoModel;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

public class quangcaoAdaptor extends PagerAdapter {

   private  Context context ;
  private   ArrayList<quangcaoModel> quangcaoModels ;

    public quangcaoAdaptor(Context context, ArrayList<quangcaoModel> quangcaoModels) {
        this.context = context;
        this.quangcaoModels = quangcaoModels;
    }

    @Override
    public int getCount() {
        return quangcaoModels.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_baner,null) ;

        ImageView imgebackground = view.findViewById(R.id.imagebanner);
        ImageView imgaebaihat = view.findViewById(R.id.image_baihatbanner);
        TextView tenbaihat = view.findViewById(R.id.title_bannerbaihat);
        TextView noidung = view.findViewById(R.id.textviewnoidung) ;

        Picasso.with(context).load(quangcaoModels.get(position).getHinhanh()).into(imgebackground);
        Picasso.with(context).load(quangcaoModels.get(position).getHinhbaihat()).into(imgaebaihat);
        tenbaihat.setText(quangcaoModels.get(position).getTenbaihat());
        noidung.setText(quangcaoModels.get(position).getNoidung());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context , danhsachbaihat.class) ;
                intent.putExtra("qc" , quangcaoModels.get(position));
                context.startActivity(intent);

            }
        });

       container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((View) object);
    }
}
