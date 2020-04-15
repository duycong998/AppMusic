package com.example.appmusicc.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appmusicc.Adapter.ViewPagerPlayListNhac;
import com.example.appmusicc.Fragment.Fragment_Dia_Nhac;
import com.example.appmusicc.Fragment.Fragment_Play_Danh_Sach_Cac_Bai_Hat;
import com.example.appmusicc.Model.BaiHat;
import com.example.appmusicc.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class PlayNhacActivity extends AppCompatActivity {
    Toolbar toolbarPlayNhac;
    TextView txtTimeSong,txtTimeFull;
    SeekBar skTime;
    ImageButton imgPlay,imgRepeat,imgNext,imgPre,imgRandom;
    ViewPager  viewPagerPlayNhac;
    public static ArrayList<BaiHat> mangBaiHatt = new ArrayList<>();
    public static ViewPagerPlayListNhac adapterNhac;
    Fragment_Dia_Nhac fragmentDiaNhac;
    Fragment_Play_Danh_Sach_Cac_Bai_Hat fragmentPlayDanhSachCacBaiHat;
    MediaPlayer mediaPlayer;
    int positon = 0;
    boolean repeat = false;
    boolean random = false;
    boolean next   = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_nhac);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);//kiemtra nhac
        getDataFromIntent();
        initView();
        init();
        eventClick();

    }

    private void eventClick() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(adapterNhac.getItem(1) != null){
                    if(mangBaiHatt.size() > 0){
                        fragmentDiaNhac.playNhac(mangBaiHatt.get(0).getHinhBaiHat());
                        handler.removeCallbacks(this);
                    }else {
                        handler.postDelayed(this,500);
                    }
                }
            }
        },500);
        imgPlay.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    imgPlay.setImageResource(R.drawable.iconplay);
                    if (Fragment_Dia_Nhac.objectAnimator !=null) {
                        Fragment_Dia_Nhac.objectAnimator.pause();

                    }
                }else {
                    mediaPlayer.start();
                    imgPlay.setImageResource(R.drawable.iconpause);
                    if (Fragment_Dia_Nhac.objectAnimator !=null){
                        Fragment_Dia_Nhac.objectAnimator.resume();
                    }
                }
            }
        });
        imgRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(repeat == false){
                    if(random == true){
                        random = false;
                        imgRepeat.setImageResource(R.drawable.iconsyned);
                        imgRandom.setImageResource(R.drawable.iconsuffle);
                        Toast.makeText(PlayNhacActivity.this, " Tắt Chế Độ Ngẫu Nhiên", Toast.LENGTH_SHORT).show();
                    }
                    imgRepeat.setImageResource(R.drawable.iconsyned);
                    Toast.makeText(PlayNhacActivity.this, " Bật Chế Độ Lặp Lại", Toast.LENGTH_SHORT).show();
                    repeat = true ;
                }else {
                    imgRepeat.setImageResource(R.drawable.iconrepeat);
                    Toast.makeText(PlayNhacActivity.this, " Tắt Chế Độ Lặp Lại", Toast.LENGTH_SHORT).show();
                    repeat = false;
                }
            }
        });
        imgRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(random == false){
                    if(repeat == true){
                        repeat = false;
                        imgRandom.setImageResource(R.drawable.iconshuffled);
                        imgRepeat.setImageResource(R.drawable.iconrepeat);
                        Toast.makeText(PlayNhacActivity.this, " Tắt Chế Độ Lặp Lại", Toast.LENGTH_SHORT).show();
                    }
                    imgRandom.setImageResource(R.drawable.iconshuffled);
                    Toast.makeText(PlayNhacActivity.this, " Bật Chế Độ Phát Ngẫu Nhiên", Toast.LENGTH_SHORT).show();
                    random = true ;
                }else {
                    imgRandom.setImageResource(R.drawable.iconsuffle);
                    Toast.makeText(PlayNhacActivity.this, " Tắt Chế Độ Ngẫu Nhiên", Toast.LENGTH_SHORT).show();
                    random = false;
                }
            }
        });
        skTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });

        imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mangBaiHatt.size() > 0){
                    if(mediaPlayer != null || mediaPlayer.isPlaying()  ){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if(positon < (mangBaiHatt.size())){
                        imgPlay.setImageResource(R.drawable.iconpause);
                        positon++;
                        if(repeat == true){
                            if(positon == 0){
                                positon = mangBaiHatt.size();
                            }
                            positon -= 1;
                        }
                        if(random == true){
                            Random random = new Random();
                            int i = random.nextInt(mangBaiHatt.size());
                            if (i == positon){
                                positon = i - 1 ;
                            }
                            positon = i ;
                        }
                        if(positon > (mangBaiHatt).size() - 1 ){
                            positon  = 0;
                        }
                        new playMp3().execute(mangBaiHatt.get(positon).getLinkBaiHat());
                        fragmentDiaNhac.playNhac(mangBaiHatt.get(positon).getHinhBaiHat());
                        getSupportActionBar().setTitle(mangBaiHatt.get(positon).getTenBaiHat());
                        upDateTime();
                    }
                }
                imgPre.setClickable(false);
                imgNext.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgPre.setClickable(true);
                        imgNext.setClickable(true);
                    }
                },3000);
            }
        });

        imgPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mangBaiHatt.size() > 0){
                    if(mediaPlayer != null || mediaPlayer.isPlaying()  ){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if(positon < (mangBaiHatt.size())){
                        imgPlay.setImageResource(R.drawable.iconpause);
                        positon--;
                        if(positon < 0){
                            positon = mangBaiHatt.size() - 1 ;
                        }

                        if(repeat == true){

                            positon += 1;
                        }
                        if(random == true){
                            Random random = new Random();
                            int i = random.nextInt(mangBaiHatt.size());
                            if (i == positon){
                                positon = i - 1 ;
                            }
                            positon = i ;
                        }
                        new playMp3().execute(mangBaiHatt.get(positon).getLinkBaiHat());
                        fragmentDiaNhac.playNhac(mangBaiHatt.get(positon).getHinhBaiHat());
                        getSupportActionBar().setTitle(mangBaiHatt.get(positon).getTenBaiHat());
                        upDateTime();
                    }
                }
                imgPre.setClickable(false);
                imgNext.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgPre.setClickable(true);
                        imgNext.setClickable(true);
                    }
                },3000);
            }
        });
    }


    private void getDataFromIntent() {
        Intent intent = getIntent();
        mangBaiHatt.clear();
        if(intent != null){
            if(intent.hasExtra("playnhac")){
                BaiHat baiHat = intent.getParcelableExtra("playnhac");
                mangBaiHatt.add(baiHat);
            }

            if(intent.hasExtra("playallbaihat")){
                ArrayList<BaiHat> arrayBH = intent.getParcelableArrayListExtra("playallbaihat");
                mangBaiHatt = arrayBH; //  2 này bằng nhau
            }
        }

    }

    private void init() {
        setSupportActionBar(toolbarPlayNhac);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarPlayNhac.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                mediaPlayer.stop();
                mangBaiHatt.clear();
            }
        });
        toolbarPlayNhac.setTitleTextColor(Color.WHITE);
        fragmentDiaNhac = new Fragment_Dia_Nhac();
        fragmentPlayDanhSachCacBaiHat = new Fragment_Play_Danh_Sach_Cac_Bai_Hat();
        adapterNhac = new ViewPagerPlayListNhac(getSupportFragmentManager());
        adapterNhac.addFragment(fragmentPlayDanhSachCacBaiHat);
        adapterNhac.addFragment(fragmentDiaNhac);
        viewPagerPlayNhac.setAdapter(adapterNhac);
        fragmentDiaNhac = (Fragment_Dia_Nhac) adapterNhac.getItem(1);
        if (mangBaiHatt.size() > 0 ){
            getSupportActionBar().setTitle(mangBaiHatt.get(0).getTenBaiHat());
            new playMp3().execute(mangBaiHatt.get(0).getLinkBaiHat());
            imgPlay.setImageResource(R.drawable.iconpause);
        }
    }

    private void initView() {
        toolbarPlayNhac   = findViewById(R.id.toolbarPlayNhac);
        txtTimeSong       = findViewById(R.id.txtTimeSong);
        txtTimeFull       = findViewById(R.id.txtTimeFull);
        skTime            = findViewById(R.id.seekBarSong);
        imgPlay           = findViewById(R.id.imgPlay);
        imgNext           = findViewById(R.id.imgNext);
        imgPre            = findViewById(R.id.imgPreview);
        imgRepeat         = findViewById(R.id.imgRepeat);
        imgRandom         = findViewById(R.id.imgShuffle);
        viewPagerPlayNhac = findViewById(R.id.viewpagerPlayNhac);
    }

    class playMp3 extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String baihat) {
            super.onPostExecute(baihat);
            try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {//tranh' loi~
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                }
            });
            mediaPlayer.setDataSource(baihat);
            mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            timeSong();
            upDateTime();
        }
    }

    private void timeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        txtTimeFull.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        skTime.setMax(mediaPlayer.getDuration());

    }
    private void upDateTime(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mediaPlayer != null){
                    skTime.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    txtTimeSong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this,300);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            next = true;
                            try {
                                Thread.sleep(800);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        },300);
       final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(next == true){
                    if(positon < (mangBaiHatt.size())){
                        imgPlay.setImageResource(R.drawable.iconpause);
                        positon++;
                        if(repeat == true){
                            if(positon == 0){
                                positon = mangBaiHatt.size();
                            }
                            positon -= 1;
                        }
                        if(random == true){
                            Random random = new Random();
                            int i = random.nextInt(mangBaiHatt.size());
                            if (i == positon){
                                positon = i - 1 ;
                            }
                            positon = i ;
                        }
                        if(positon > (mangBaiHatt).size() - 1 ){
                            positon  = 0;
                        }
                        new playMp3().execute(mangBaiHatt.get(positon).getLinkBaiHat());
                        fragmentDiaNhac.playNhac(mangBaiHatt.get(positon).getHinhBaiHat());
                        getSupportActionBar().setTitle(mangBaiHatt.get(positon).getTenBaiHat());
                    }
                    imgPre.setClickable(false);
                    imgNext.setClickable(false);
                    Handler handler1 = new Handler();
                    handler1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imgPre.setClickable(true);
                            imgNext.setClickable(true);
                        }
                    },3000);
                    next = false;
                    handler1.removeCallbacks(this);// xoa' cu~

                }else {
                    handler1.postDelayed(this,800);
                }
            }
        },800);
    }
}
