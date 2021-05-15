package com.example.fitnessapppaladrian;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

        private Button BmiButton;
        private Button stepButton;
        private Button waterButton;
        private Button stopWatchButton;
        private Button workoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BmiButton = (Button) findViewById(R.id.button4);
        BmiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBMIActivity();
            }
        });

        stepButton = (Button) findViewById(R.id.button3);
        stepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stepCalculatorActivity();
            }
        });

        waterButton = (Button) findViewById(R.id.button2);
        waterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                waterReminderActivity();
            }
        });

        stopWatchButton = (Button) findViewById(R.id.button8);
        stopWatchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopWatchActivity();
            }
        });

        workoutButton = (Button) findViewById(R.id.button);
        workoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workoutActivity();
            }
        });
    }

    // BMI starting button
    public void openBMIActivity()
    {
        Intent intent = new Intent(this,BmiActivity.class);
        startActivity(intent);
    }

    //StepCalculator starting button
    public void stepCalculatorActivity()
    {
        Intent intent = new Intent(this,stepCalculatorActivitypage.class);
        startActivity(intent);

    }

    //waterReminder starting button
    public void  waterReminderActivity()
    {
        Intent intent = new Intent(this,WaterActivityFirst.class);
        startActivity(intent);
    }

    //stopWatch starting button
    public void stopWatchActivity()
    {
        Intent intent = new Intent (this,stopWatch.class);
        startActivity(intent);
    }

    //workout starting button...
    public void workoutActivity()
    {
        Intent intent = new Intent (this, workoutActivity.class );
        startActivity(intent);
    }

}