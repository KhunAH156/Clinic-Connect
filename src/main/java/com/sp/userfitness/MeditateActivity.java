package com.sp.userfitness;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.ImageButton;


public class MeditateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meditate);

        ImageButton back_btn = findViewById(R.id.meditate_back_btn);
        back_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MeditateActivity.this, FitnessHome.class );
                startActivity(intent);
                finish();
            }
        });
        Button nextpage = findViewById(R.id.go);
        nextpage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MeditateActivity.this, MeditateExercise.class );
                startActivity(intent);
                finish();
            }
        });
    }
}