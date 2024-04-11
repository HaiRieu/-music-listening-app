package com.example.appmusic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import com.example.appmusic.Adaptor.ViewpageAdaptor;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
 private TabLayout tabLayout ;
 private ViewPager viewPager ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa() ;
         inint() ;

    }

    private void inint() {
        ViewpageAdaptor adapto1r = new ViewpageAdaptor(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) ;
         viewPager.setAdapter(adapto1r);
         tabLayout.setupWithViewPager(viewPager);
    }

    private void anhxa() {
        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpage);
    }


}