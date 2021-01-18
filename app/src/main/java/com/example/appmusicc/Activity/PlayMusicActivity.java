package com.example.appmusicc.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.appmusicc.Adapter.ViewPagerPlayListMusic;
import com.example.appmusicc.Fragment.AnimationFragment;
import com.example.appmusicc.Fragment.PlayListSongFragment;
import com.example.appmusicc.Model.Song;
import com.example.appmusicc.R;
import com.example.appmusicc.Service.PlayMusicService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class PlayMusicActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    boolean isCheck = false;
    Toolbar toolbarPlayMusic;
    static TextView txtTimeSong, txtTimeFull;
    static SeekBar skTime;
    static ImageButton imgPlay, imgRepeat, imgNext, imgPre, imgRandom;
    ViewPager viewPagerPlayMusic;
    public static ArrayList<Song> arraySongg = new ArrayList<>();
    public static ViewPagerPlayListMusic adapterMusic;
    AnimationFragment fragmentAnimation;
    PlayListSongFragment fragmentPlayListSong;
    static MediaPlayer mediaPlayer;
    static int position = 0;
    boolean repeat = false;
    boolean random = false;
    boolean next = false;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playmusic);
        try {
            getDataFromIntent();
            initView();
            setupPlayMusicService();
            init();
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);//kiemtra nhac
            eventClick();
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("######## error", e.getMessage());
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(PlayMusicActivity.this, PlayMusicService.class);
        PlayMusicActivity.this.stopService(intent);
        mediaPlayer.stop();
        arraySongg.clear();
        Log.d("AAAAAC", arraySongg.size() + "");
        finish();
    }

    private void eventClick() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (adapterMusic.getItem(1) != null) {
                    if (arraySongg.size() > 0) {
                        fragmentAnimation.getPictureSong(arraySongg.get(0).getPictureSong());
                        handler.removeCallbacks(this);
                    } else {
                        handler.postDelayed(this, 500);
                    }
                }
            }
        }, 500);
        imgPlay.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    imgPlay.setImageResource(R.drawable.iconplay);
                    if (AnimationFragment.objectAnimator != null) {
                        AnimationFragment.objectAnimator.pause();
                    }
                } else {
                    mediaPlayer.start();
                    imgPlay.setImageResource(R.drawable.iconpause);
                    if (AnimationFragment.objectAnimator != null) {
                        AnimationFragment.objectAnimator.resume();
                    }
                }
            }
        });
        imgRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!repeat) {
                    if (random) {
                        random = false;
                        imgRepeat.setImageResource(R.drawable.iconsyned);
                        imgRandom.setImageResource(R.drawable.iconsuffle);
                        Toast.makeText(PlayMusicActivity.this, " Tắt Chế Độ Ngẫu Nhiên", Toast.LENGTH_SHORT).show();
                    }
                    imgRepeat.setImageResource(R.drawable.iconsyned);
                    Toast.makeText(PlayMusicActivity.this, " Bật Chế Độ Lặp Lại", Toast.LENGTH_SHORT).show();
                    repeat = true;
                } else {
                    imgRepeat.setImageResource(R.drawable.iconrepeat);
                    Toast.makeText(PlayMusicActivity.this, " Tắt Chế Độ Lặp Lại", Toast.LENGTH_SHORT).show();
                    repeat = false;
                }
            }
        });
        imgRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!random) {
                    if (repeat) {
                        repeat = false;
                        imgRandom.setImageResource(R.drawable.iconshuffled);
                        imgRepeat.setImageResource(R.drawable.iconrepeat);
                        Toast.makeText(PlayMusicActivity.this, " Tắt Chế Độ Lặp Lại", Toast.LENGTH_SHORT).show();
                    }
                    imgRandom.setImageResource(R.drawable.iconshuffled);
                    Toast.makeText(PlayMusicActivity.this, " Bật Chế Độ Phát Ngẫu Nhiên", Toast.LENGTH_SHORT).show();
                    random = true;
                } else {
                    imgRandom.setImageResource(R.drawable.iconsuffle);
                    Toast.makeText(PlayMusicActivity.this, " Tắt Chế Độ Ngẫu Nhiên", Toast.LENGTH_SHORT).show();
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
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                if (arraySongg.size() > 0) {
                    imgPlay.setImageResource(R.drawable.iconpause);
                    position++;
                    if (random) {
                        Random random = new Random();
                        int i = random.nextInt(arraySongg.size());
                        if (i == position) {
                            position = i - 1;
                        }
                        position = i;
                    }
                    if (position < 0 || position >= arraySongg.size()) {
                        position = 0;
                    }
                    Log.d("######", "1155");
                    startMusic();
                    fragmentAnimation.getPictureSong(arraySongg.get(position).getPictureSong());
                    getSupportActionBar().setTitle(arraySongg.get(position).getNameSong());
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
                }, 3000);
            }
        });

        imgPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position--;
                if (random) {
                    Random random = new Random();
                    int i = random.nextInt(arraySongg.size());
                    if (i == position) {
                        position = i - 1;
                    }
                    position = i;
                }
                if (position < 0 || position >= arraySongg.size()) {
                    position = 0;
                }
                startMusic();
                fragmentAnimation.getPictureSong(arraySongg.get(position).getPictureSong());
                getSupportActionBar().setTitle(arraySongg.get(position).getNameSong());
                Log.d("######", "22");
                imgPre.setClickable(false);
                imgNext.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgPre.setClickable(true);
                        imgNext.setClickable(true);
                    }
                }, 3000);
            }
        });
    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("playmusic")) {
                Song song = intent.getParcelableExtra("playmusic");
                if (!arraySongg.contains(song))
                    arraySongg.add(song);
            }
            if (intent.hasExtra("playallsong")) {
                ArrayList<Song> arrayBH = intent.getParcelableArrayListExtra("playallsong");
                for (Song song : arrayBH) {
                    if (!arraySongg.contains(song))
                        arraySongg.add(song);
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                isCheck = true;
                SharedPreferences preferences = getApplication().getSharedPreferences("mys", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("key", isCheck);
                editor.apply();
                Log.d("AAAC", isCheck + "");
            }
        }
    }

    private void init() {
        setSupportActionBar(toolbarPlayMusic);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarPlayMusic.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlayMusicActivity.this, PlayMusicService.class);
                PlayMusicActivity.this.stopService(intent);
                mediaPlayer.stop();
                arraySongg.clear();
                Log.d("AAAAAC", arraySongg.size() + "");
                finish();
            }
        });
        toolbarPlayMusic.setTitleTextColor(Color.WHITE);
        fragmentAnimation = new AnimationFragment();
        fragmentPlayListSong = new PlayListSongFragment();
        adapterMusic = new ViewPagerPlayListMusic(getSupportFragmentManager());
        adapterMusic.addFragment(fragmentPlayListSong);
        adapterMusic.addFragment(fragmentAnimation);
        viewPagerPlayMusic.setAdapter(adapterMusic);
        if (arraySongg.size() > 0) {
            getSupportActionBar().setTitle(arraySongg.get(0).getNameSong());
            startMusicService();
            imgPlay.setImageResource(R.drawable.iconpause);
        }

//        MainActivity mainActivity = new MainActivity();
//        if (mainActivity.isMyServiceRunning(PlayMusicActivity.class)) {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                fragmentAnimation.getPictureSong(arraySongg.get(position).getPictureSong());
                getSupportActionBar().setTitle(arraySongg.get(position).getNameSong());
                timeSong();
                upDateTime();
            }
        }, 500);
        //     }
    }

    private void startMusicService() {
        Intent intent = new Intent(this, PlayMusicService.class);
        intent.putExtra("url", arraySongg.get(0).getLinkSong());
        startService(intent);
    }

    private void initView() {
        toolbarPlayMusic = findViewById(R.id.toolbarPlayNhac);
        txtTimeSong = findViewById(R.id.txtTimeSong);
        txtTimeFull = findViewById(R.id.txtTimeFull);
        skTime = findViewById(R.id.seekBarSong);
        imgPlay = findViewById(R.id.imgPlay);
        imgNext = findViewById(R.id.imgNext);
        imgPre = findViewById(R.id.imgPreview);
        imgRepeat = findViewById(R.id.imgRepeat);
        imgRandom = findViewById(R.id.imgShuffle);
        viewPagerPlayMusic = findViewById(R.id.viewpagerPlayNhac);
    }

    public void startMusic() {
        Log.d("######", "11");
        try {
            if (mediaPlayer != null) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                mediaPlayer.reset();
                mediaPlayer.setDataSource(arraySongg.get(position).getLinkSong());
                mediaPlayer.prepare();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setupPlayMusicService() {
        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mediaPlayer.start();
                    fragmentAnimation.getPictureSong(arraySongg.get(position).getPictureSong());
                    getSupportActionBar().setTitle(arraySongg.get(position).getNameSong());
                    timeSong();
                }
            });
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {//tranh' loi~
                @Override
                public void onCompletion(MediaPlayer mp) {
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Log.d("######", position + ">" + repeat + ">" + random);
                            position++;
                            if (position >= arraySongg.size()) {
                                position = 0;
                            }
                            imgPlay.setImageResource(R.drawable.iconpause);
                            if (repeat) {
                                if (position == 0) {
                                    position = 0;
                                } else {
                                    position--;
                                }
                            }
                            if (random) {
                                Random random = new Random();
                                int i = random.nextInt(arraySongg.size());
                                if (i == position) {
                                    position = i - 1;
                                }
                                position = i;
                            }
                            skTime.setProgress(0);
                            startMusic();
                            Log.d("######", "223");
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }, 200);
                }
            });

            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    mediaPlayer.start();
                    timeSong();
                    upDateTime();
                }
            }, 200);
        }
    }

    private void timeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        txtTimeFull.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        skTime.setMax(mediaPlayer.getDuration());
    }

    private void upDateTime() {
        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null) {
                    skTime.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    txtTimeSong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this, 1000);
                }
            }
        }, 300);
        imgPre.setClickable(false);
        imgNext.setClickable(false);
        Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                imgPre.setClickable(true);
                imgNext.setClickable(true);
            }
        }, 3000);
        next = false;
    }
}
