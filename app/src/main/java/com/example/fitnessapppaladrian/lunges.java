package com.example.fitnessapppaladrian;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class lunges extends AppCompatActivity {

    private Button buttonstartWatch;
    private Button buttonfinishWorkOut;
    private ImageView iCanhor;
    private Chronometer outputValue;
    private Animation roundingAlone;
    private Chronometer myTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunges);

        buttonstartWatch = (Button) findViewById(R.id.buttonstartwatch1lunges);
        buttonfinishWorkOut = (Button) findViewById(R.id.finishWorkout1lunges);
        iCanhor = findViewById(R.id.icanhor1lunges);
        myTimer = (Chronometer) findViewById(R.id.chronometer12lunges);
        roundingAlone = AnimationUtils.loadAnimation(this, R.anim.roundingalone);
        outputValue = (Chronometer) findViewById(R.id.chronometer12lunges);

        Typeface MMedium = Typeface.createFromAsset(getAssets(), "fonts/MMedium.ttf");

        buttonfinishWorkOut.setAlpha(0);

        buttonstartWatch.setTypeface(MMedium);

        buttonstartWatch.setTypeface(MMedium);

        buttonstartWatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iCanhor.startAnimation(roundingAlone);
                buttonfinishWorkOut.animate().alpha(1).translationY(-80).setDuration(300).start();
                buttonstartWatch.animate().alpha(0).setDuration(300).start();

                myTimer.setBase(SystemClock.elapsedRealtime());
                myTimer.start();


            }

        });


        buttonfinishWorkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myTimer.stop();
                iCanhor.clearAnimation();
                myMountainClimbersSet();
            }


        });

    }
    public void myMountainClimbersSet()
    {
        Intent intent = new Intent(this, mountainClimbers.class );
        startActivity(intent);

    }

}
