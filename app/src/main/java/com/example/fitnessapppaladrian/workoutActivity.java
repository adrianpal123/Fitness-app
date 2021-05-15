package com.example.fitnessapppaladrian;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class workoutActivity extends AppCompatActivity {

    TextView titlePage;
    ImageView imgPage;
    Animation animimgpage;
    Button buttonWorkOut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        Typeface MLight = Typeface.createFromAsset(getAssets(),"fonts/MLight.ttf");
        Typeface MMedium = Typeface.createFromAsset(getAssets(),"fonts/MMedium.ttf");
        Typeface Vidaloka = Typeface.createFromAsset(getAssets(),"fonts/Vidaloka.ttf");

        titlePage = (TextView) findViewById(R.id.worktext1);
        imgPage = (ImageView) findViewById(R.id.imageViewWorkout);


        //titlePage.setTypeface(Vidaloka);
        //imgPage.startAnimation(animimgpage);


        buttonWorkOut = (Button) findViewById(R.id.buttonWorkOut1);
        buttonWorkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWorkOut();
            }
        });


    }

    public void openWorkOut()
    {
        Intent intent = new Intent (this, workoutActivity2.class );
        startActivity(intent);
    }
}