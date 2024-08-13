package com.sp.userfitness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;
//import com.google.firebase.auth.FirebaseAuth;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;;


public class StateOfMindActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_of_mind);
        ImageButton som_back = findViewById(R.id.som_back_btn);
        Button SOM_questions = findViewById(R.id.State_of_mind_questions);


        som_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FitnessHome.class);
                startActivity(intent);
                finish();
            }
        });

        SOM_questions.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StateOfMindActivity.this, StateOfMindQuestions.class);
                startActivity(intent);
            }
        });

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }




}