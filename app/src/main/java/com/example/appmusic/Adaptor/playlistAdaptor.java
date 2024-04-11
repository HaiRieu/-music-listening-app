package com.example.appmusic.Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appmusic.R;
import com.example.appmusic.model.playlistModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class playlistAdaptor extends ArrayAdapter<playlistModel> {

    public class viewhodel {
        ImageView imagebackgroud , imgahinhanh;
        TextView textView ;

    }

    public playlistAdaptor(@NonNull Context context, int resource, @NonNull List<playlistModel> objects) {


        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        viewhodel viewhodel = null ;
        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext()) ;
            convertView = inflater.inflate(R.layout.dong_playlist , null) ;
             viewhodel = new viewhodel() ;
        viewhodel.imagebackgroud =  convertView.findViewById(R.id.imaghebuton_playlist) ;
        viewhodel.imgahinhanh = convertView.findViewById(R.id.imgae_playlist) ;
        viewhodel.textView = convertView.findViewById(R.id.textviewdong) ;

        convertView.setTag(viewhodel);

        }else {
            viewhodel = (viewhodel) convertView.getTag();

        }
        playlistModel model = getItem(position);
        Picasso.with(getContext()).load(model.getHinhnen()).into(viewhodel.imagebackgroud);
        Picasso.with(getContext()).load(model.getHinhicon()).into(viewhodel.imgahinhanh);
        viewhodel.textView.setText(model.getTen());

        return  convertView ;
    }
}
