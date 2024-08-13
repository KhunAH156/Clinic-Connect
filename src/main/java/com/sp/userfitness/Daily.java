package com.sp.userfitness;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Daily extends AppCompatActivity {

    BottomNavigationView nav;
    ImageButton GoMeditate, GoBreathe, GoExercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily);
        nav = findViewById(R.id.navi);
        GoMeditate=findViewById(R.id.imageButton69);
        GoExercise=findViewById(R.id.imageButton376);
        GoBreathe=findViewById(R.id.imageButton969);
        nav.setSelectedItemId(R.id.daily);

        GoMeditate.setOnClickListener(v -> startActivity(new Intent(this,MeditateActivity.class)));
        GoExercise.setOnClickListener(v -> startActivity(new Intent(this,WorkOutList.class)));
        GoBreathe.setOnClickListener(v -> startActivity(new Intent(this,BreatheActivity.class)));

        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                if (item.getItemId() == R.id.home) {
                    intent = new Intent(getApplicationContext(), FitnessHome.class);
                    startActivity(intent);
                }
                if (item.getItemId() == R.id.history) {
                    intent = new Intent(getApplicationContext(), WorkoutHistoryActivity.class);
                    startActivity(intent);
                }
                return false;
            }
        });
    }

}