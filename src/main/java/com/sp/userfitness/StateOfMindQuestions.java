package com.sp.userfitness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;
//import com.google.firebase.auth.FirebaseAuth;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

import android.view.MenuItem;
import android.widget.Button;
import android.widget.SeekBar;
import android.view.View;
import android.widget.TextView;
import android.widget.*;

public class StateOfMindQuestions extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private Cursor model = null;
    SeekBar seekbar;
    TextView mood;
    ImageView mood_pic;
    public String mood_str, custom_str;
    EditText editText;
    private Database_Helper helper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_of_mind_questions);



        Date currenDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String formattedDate = dateFormat.format(currenDate);

        seekbar = findViewById(R.id.seekBar);
        mood = findViewById(R.id.mood);
        mood_pic = findViewById(R.id.mood_pic);
        ImageButton som_q_back_btn = findViewById(R.id.som_q_back_btn);
        mood_pic.setVisibility(View.VISIBLE);
        mood_pic.setImageResource(R.drawable.neutral);
        mood.setVisibility(View.VISIBLE);
        mood.setText("Neutral");

        helper = new Database_Helper(this);
        //model = helper.getAll();

        som_q_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StateOfMindQuestions.this, StateOfMindActivity.class);
                startActivity(intent);
            }
        });
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {

                if(progress == 0){
                    mood_pic.setVisibility(View.VISIBLE);
                    mood_pic.setImageResource(R.drawable.very_sad);
                    mood.setText("Very unpleasant");
                    mood_str = "Very unpleasant";
                } else if (progress == 1) {
                    mood_pic.setVisibility(View.VISIBLE);
                    mood_pic.setImageResource(R.drawable.sad);
                    mood.setText("Unpleasant");
                    mood_str = "Unpleasant";
                } else if (progress == 2) {
                    mood_pic.setVisibility(View.VISIBLE);
                    mood_pic.setImageResource(R.drawable.slight_sad);
                    mood.setText("Slightly unpleasant");
                    mood_str = "Slightly unpleasant";
                } else if (progress == 3) {
                    mood_pic.setVisibility(View.VISIBLE);
                    mood_pic.setImageResource(R.drawable.neutral);
                    mood.setText("Neutral");
                    mood_str = "Neutral";
                } else if (progress == 4) {
                    mood_pic.setVisibility(View.VISIBLE);
                    mood_pic.setImageResource(R.drawable.slight_happy);
                    mood.setText("Slightly pleasant");
                    mood_str = "Slightly plesant";
                } else if (progress == 5) {
                    mood_pic.setVisibility(View.VISIBLE);
                    mood_pic.setImageResource(R.drawable.happy);
                    mood.setText("Pleasant");
                    mood_str = "Pleasant";
                } else if(progress ==  6){
                    mood_pic.setVisibility(View.VISIBLE);
                    mood_pic.setImageResource(R.drawable.very_happy);
                    mood.setText("Very Pleasant");
                    mood_str = "Very Pleasant";
                } else {
                    mood_pic.setVisibility(View.INVISIBLE);
                    mood.setText("Move the slider");

                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        Button SOM_summary = findViewById(R.id.summary);
        SOM_summary.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {

                editText = findViewById(R.id.custom_text);
                String edit_text = editText.getText().toString();
                custom_str = edit_text;
                helper.insert(mood_str, custom_str, formattedDate);
                //model = helper.getAll();
                Toast.makeText(getApplicationContext(), "data added successfully", Toast.LENGTH_LONG).show();
                //adapter.swapCursor(model);
                //helper.getDataByDate(formattedDate);


                Intent intent = new Intent(StateOfMindQuestions.this, StateOfMindSummary.class);
                startActivity(intent);
                finish();
            }
        });
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    protected void onDestroy(){
        helper.close();
        super.onDestroy();
    }

}