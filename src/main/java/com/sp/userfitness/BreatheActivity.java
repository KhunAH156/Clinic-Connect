package com.sp.userfitness;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.ImageButton;
import android.widget.TextView;

public class BreatheActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breathe);

        //ImageView imageView =findViewById(R.id.breathe_img);
        TextView textView = findViewById(R.id.breathe_text);
        ImageButton breathe_back_btn = findViewById(R.id.breathe_bacl_btn);
        breathe_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BreatheActivity.this, FitnessHome.class);
                startActivity(intent);
                finish();
            }
        });
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button nextpage = findViewById(R.id.breathe_video_btn);
        nextpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BreatheActivity.this, BreatheExercise.class );
                startActivity(intent);
            }
        });
    }
}