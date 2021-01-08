package com.example.appmusicc.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.appmusicc.Adapter.ListViewMainAdapter;
import com.example.appmusicc.Adapter.MainViewPagerAdapter;
import com.example.appmusicc.Fragment.SearchFragment;
import com.example.appmusicc.Fragment.HomeFragment;
import com.example.appmusicc.Model.Menu;
import com.example.appmusicc.R;
import com.example.appmusicc.Service.PlayMusicService;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    NavigationView navigationView;
    ListView lsvMain;
    DrawerLayout drawerLayout;
    ListViewMainAdapter adapter;
    ArrayList<Menu> arrayMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        init();
        actionBar();
        catchOnItemListView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (checkShare()) {
            Intent intent = new Intent(this, PlayMusicActivity.class);
            startActivity(intent);
        }
    }

    private boolean checkShare() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean a = preferences.getBoolean("key", false);
        Log.d("AAAB", a + "");
        return a;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void catchOnItemListView() {
        lsvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        drawerLayout.closeDrawers();
                        break;
                    case 1:
                        Intent intent = new Intent(getApplicationContext(), ListAlbumActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawers();
                        break;
                }
            }
        });
    }

    private void init() {
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mainViewPagerAdapter.addFragment(HomeFragment.getInstance(), "Trang Chủ");
        mainViewPagerAdapter.addFragment(SearchFragment.getInstance(), "Tìm Kiếm");

//        mainViewPagerAdapter.addFragment(new HomeFragment(),"Trang Chủ");
//        mainViewPagerAdapter.addFragment(new SearchFragment(),"Tìm Kiếm");
        viewPager.setAdapter(mainViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.icontrangchu);
        tabLayout.getTabAt(1).setIcon(R.drawable.iconsearch);
    }

    private void actionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void initView() {
        tabLayout = findViewById(R.id.myTabLayout);
        viewPager = findViewById(R.id.myViewPager);
        navigationView = findViewById(R.id.navigationViewMain);
        lsvMain = findViewById(R.id.lsViewMain);
        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolbarMain);

        arrayMenu = new ArrayList<>();
        arrayMenu.add(new Menu("Trang Chủ", "https://laptopgiasi.vn/wp-content/uploads/2017/09/icon-trang-chu-laptopgiasi.vn_.png"));
        arrayMenu.add(new Menu("Tìm Kiếm", "https://img.icons8.com/pastel-glyph/2x/search--v2.png"));
        arrayMenu.add(new Menu("Liên Hệ", "https://buigiastore.com/wp-content/uploads/2020/03/unnamed.png"));
        arrayMenu.add(new Menu("Thông Tin", "https://cdn1.iconfinder.com/data/icons/Pretty_office_icon_part_2/128/personal-information.png"));

        adapter = new ListViewMainAdapter(this, arrayMenu);

        lsvMain.setAdapter(adapter);
    }
}
