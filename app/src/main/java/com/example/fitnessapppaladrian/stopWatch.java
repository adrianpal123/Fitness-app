package com.example.fitnessapppaladrian;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class stopWatch extends AppCompatActivity {

    private TextView tvSplash, tvSubSplash;
    private Button buttonGo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch);

        tvSplash = (TextView) findViewById(R.id.tvsplash);
        tvSubSplash = (TextView) findViewById(R.id.tvSubSplash);

        Typeface Mlight = Typeface.createFromAsset(getAssets(),"fonts/MLight.ttf");
        Typeface MMedium = Typeface.createFromAsset(getAssets(),"fonts/MMedium.ttf");
        Typeface Mregular = Typeface.createFromAsset(getAssets(),"fonts/MRegular.ttf");



        buttonGo = (Button) findViewById(R.id.buttonGo);
        buttonGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStopWatch();
            }
        });

        tvSplash.setTypeface(Mregular);
        tvSubSplash.setTypeface(Mlight);
        buttonGo.setTypeface(MMedium);

    }

    public void openStopWatch()
    {
        Intent intent = new Intent (this, stopWatchActivity2.class );
        startActivity(intent);
    }
}