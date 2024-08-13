package com.sp.userfitness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;
//import com.google.firebase.auth.FirebaseAuth;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class StateOfMindSummary extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private  ImageButton imageButton;
    public TextView summary1, summary2, summary3;
    private CalendarView calendarView;

    Button button;
    Calendar calendar;

    private boolean isCalenderVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_of_mind_summary);




        summary1 = findViewById(R.id.summary1);
        summary2 = findViewById(R.id.summary2);
        summary3 = findViewById(R.id.summary3);
        //DatabaseHelper dbHelper = new DatabaseHelper(this);
        /*StringBuilder dataText = new StringBuilder();

        for(String[] entry: data){
            String string1 = entry[0];
            String string2 = entry[1];
            String date = entry[2];
            dataText.append("Mood: ").append(string1)
                    .append("\nCustom: ").append(string2)
                    .append("\nDate: ").append(date)
                    .append("\n\n");
        }
        summary1.setText(dataText.toString());*/
        Database_Helper dbHelper = new Database_Helper(this);
        Cursor cursor = dbHelper.getLatestData();
        String string1 = "";
        String string2 = "";
        String string3 = "";
        if(cursor.moveToFirst()){
            string1 = cursor.getString(cursor.getColumnIndex("mood_str"));
            string2 = cursor.getString(cursor.getColumnIndex("custom_str"));
            string3 = cursor.getString(cursor.getColumnIndex("date"));
        }
        cursor.close();
        summary1.setText("Mood : " + string1);
        summary2.setText("Reason : " + string2);
        summary3.setText(string3);

        calendarView = findViewById(R.id.som_calender);
        calendarView.setVisibility(View.GONE);
        imageButton = findViewById(R.id.som_calender_btn);
        //button = findViewById(R.id.show);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isCalenderVisible){
                    calendarView.setVisibility(View.GONE);
                } else{
                    calendarView.setVisibility(View.VISIBLE);
                }
                isCalenderVisible =  !isCalenderVisible;
            }
        });

        calendar = Calendar.getInstance();
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override

            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int day) {
                month = month + 1;
                Toast.makeText(StateOfMindSummary.this, day + "/" + month +"/"+year, Toast.LENGTH_LONG).show();
                String selectedDate = String.format(Locale.getDefault(), "%04d-%02d-%02d", year, month, day);
                loadDataForDate(selectedDate);
                //List<String[]> data = helper.getDataByDate(selectedDate);

                //displayDataForDate(selectedDate);
            }
        });




        Button SOM_Home = findViewById(R.id.home1);
        SOM_Home.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StateOfMindSummary.this, FitnessHome.class);
                startActivity(intent);
                finish();
            }
        });


        getDate();

    }




    public void getDate(){
        long date = calendarView.getDate();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy", Locale.getDefault());
        calendar.setTimeInMillis(date);
        String selected_date = simpleDateFormat.format(calendar.getTime());
        //Toast.makeText(this, selected_date, Toast.LENGTH_SHORT).show();
        //return selected_date;
    }

    private void loadDataForDate(String date){
        Database_Helper helper = new Database_Helper(this);
        Cursor cursor = helper.getDataByDate(date);
        if(cursor.moveToFirst()){
            String string1 = cursor.getString(cursor.getColumnIndex("mood_str"));
            String string2 = cursor.getString(cursor.getColumnIndex("custom_str"));
            String string3 = cursor.getString(cursor.getColumnIndex("date"));
            summary1.setText("Mood : " + string1);
            summary2.setText("Reason : " + string2);
            summary3.setText(string3);
        }else{
            summary1.setText("No Data for selected date");
            summary2.setText("");
            summary3.setText("");
        }
        cursor.close();
    }




    /*public void displayDataForDate(String selectedDate) {

        DatabaseHelper helper = new DatabaseHelper(this);
        String[] data = helper.getDataForSelectedDate(selectedDate);

        if (data != null) {
            String mood_str = data[0];
            String custom_str = data[1];
            String date = data[2];


            summary1.setText(mood_str);
            summary2.setText(custom_str);
            summary3.setText(date);
        } else {
            summary1.setText("");
            summary2.setText("");
            summary3.setText("No data for selected date");
        }
    }*/



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}


