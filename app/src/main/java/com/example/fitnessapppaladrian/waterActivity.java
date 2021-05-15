package com.example.fitnessapppaladrian;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class waterActivity extends AppCompatActivity {

    private Button button150ml;
    private Button button200ml;
    private Button button270ml;

    public static boolean clickedbutton150ml = false;
    public static boolean clickedbutton200ml = false;
    public static boolean clickedbutton270ml = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);


        button150ml = (Button) findViewById(R.id.button150ml);
        button150ml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedbutton150ml = true;
                displayWaterReminderClockPage();
            }
        });

        button200ml = (Button) findViewById(R.id.button200ml);
        button200ml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedbutton200ml = true;
                displayWaterReminderClockPage();
            }
        });

        button270ml = (Button) findViewById(R.id.button270ml);
        button270ml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedbutton270ml = true;
                displayWaterReminderClockPage();
            }
        });
    }


    public void displayWaterReminderClockPage()
    {
        Intent intent = new Intent(this,WaterActivity2.class);
        startActivity(intent);

    }

}