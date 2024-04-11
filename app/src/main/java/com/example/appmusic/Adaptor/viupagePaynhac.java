package com.example.appmusic.Adaptor;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.appmusic.Fragment.danhsachpaynhac;
import com.example.appmusic.Fragment.dianhac;

import java.util.ArrayList;


public class viupagePaynhac extends FragmentStatePagerAdapter {
    private final ArrayList<Fragment> fragments = new ArrayList<>() ;

    public viupagePaynhac(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return  fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
    public void addFragment(Fragment fragment){
        fragments.add(fragment);
    }

}
