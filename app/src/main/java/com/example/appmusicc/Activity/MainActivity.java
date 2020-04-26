package com.example.appmusicc.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.appmusicc.Adapter.MainViewPagerAdapter;
import com.example.appmusicc.Fragment.SearchFragment;
import com.example.appmusicc.Fragment.HomeFragment;
import com.example.appmusicc.R;
import com.example.appmusicc.Service.DataServiec;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        init();

    }

    private void init() {
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mainViewPagerAdapter.addFragment(HomeFragment.getInstance(),"Trang Chủ");
        mainViewPagerAdapter.addFragment(SearchFragment.getInstance(),"Tìm Kiếm");

//        mainViewPagerAdapter.addFragment(new HomeFragment(),"Trang Chủ");
//        mainViewPagerAdapter.addFragment(new SearchFragment(),"Tìm Kiếm");
        viewPager.setAdapter(mainViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.icontrangchu);
        tabLayout.getTabAt(1).setIcon(R.drawable.iconsearch);

    }

    private void initView() {
        tabLayout = findViewById(R.id.myTabLayout);
        viewPager = findViewById(R.id.myViewPager);
        
    }


}
