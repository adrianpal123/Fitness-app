package com.example.fitnessapppaladrian;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class workoutActivity2 extends AppCompatActivity {

    Button buttonStartWorkOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout2);

        buttonStartWorkOut = (Button) findViewById(R.id.buttonWorkoutStart);
        buttonStartWorkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstWorkOutRoutine();
            }
        });
    }

    public void firstWorkOutRoutine()
    {
        Intent intent = new Intent (this, legraises.class );
        startActivity(intent);

    }
}