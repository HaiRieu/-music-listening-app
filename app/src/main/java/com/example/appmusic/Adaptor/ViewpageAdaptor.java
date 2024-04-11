package com.example.appmusic.Adaptor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.appmusic.Fragment.timkiem;
import com.example.appmusic.Fragment.trangchu;

public class ViewpageAdaptor  extends FragmentStatePagerAdapter {

    public ViewpageAdaptor(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
      switch (position){
          case 0 :
              return new trangchu() ;
          case 1 :
              return new timkiem() ;
          default:
              return  new trangchu() ;
      }

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String tiltle = "" ;
        switch (position){
            case 0 :
                tiltle = "Trang Chủ" ;
                break;
            case 1 :
                tiltle = "Tìm Kiếm" ;
                break ;
        }
        return tiltle ;
    }
}
