package com.sp.userfitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class BreatheExercise extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_breathe_exercise);
        //supportRequestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        //

        Button breathe_exit = findViewById(R.id.breathe_exist_btn);
        breathe_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BreatheExercise.this, FitnessHome.class);
                startActivity(intent);
                finish();
            }
        });
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        VideoView breathe_vid = findViewById(R.id.breathe_video);
        String video_path = "android.resource://" + getPackageName() + "/" + R.raw.breathing ;
        breathe_vid.setVideoURI(Uri.parse(video_path));

        //breathe_vid.setVideoURI(Uri.parse("https://youtu.be/dQw4w9WgXcQ?si=xiV2M3s_inOQQ32V"));

        MediaController mediaController = new MediaController(BreatheExercise.this);
        mediaController.setAnchorView(breathe_vid);

        breathe_vid.setMediaController(mediaController);
        breathe_vid.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });
        breathe_vid.start();
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN |
                            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            );
        }
    }
}