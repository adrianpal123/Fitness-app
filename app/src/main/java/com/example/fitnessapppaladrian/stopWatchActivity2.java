package com.example.fitnessapppaladrian;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class stopWatchActivity2 extends AppCompatActivity {


    private Button buttonstartWatch;
    private Button buttonfinishWorkOut;
    private ImageView iCanhor;
    private Animation roundingAlone;
    private Chronometer myTimer;
    private String fileName = "";
    private String filePath = "";
    private String fileContent = "";
    private Chronometer outputValue;
    Date currentTime = Calendar.getInstance().getTime();
    private static final int WRITE_EXTERNAL_STORAGE_CODE = 1;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch2);


        buttonstartWatch = (Button) findViewById(R.id.buttonstartwatch);
        buttonfinishWorkOut = (Button) findViewById(R.id.finishWorkout);
        iCanhor = findViewById(R.id.icanhor);
        myTimer = (Chronometer) findViewById(R.id.chronometer1);
        roundingAlone = AnimationUtils.loadAnimation(this, R.anim.roundingalone);
        outputValue = (Chronometer) findViewById(R.id.chronometer1);

        Typeface MMedium = Typeface.createFromAsset(getAssets(),"fonts/MMedium.ttf");

        buttonfinishWorkOut.setAlpha(0);

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

                fileContent = outputValue.getText().toString().trim();
                Toast.makeText(stopWatchActivity2.this, "The value has been saved in a file!",Toast.LENGTH_SHORT).show();

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                    {
                        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                            requestPermissions(permissions, WRITE_EXTERNAL_STORAGE_CODE);
                        } else {
                            saveToTxtFile(fileContent);
                        }
                    }
                    else
                    {
                        saveToTxtFile(fileContent);
                    }
                }
        });




    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {
            case WRITE_EXTERNAL_STORAGE_CODE:
            {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    saveToTxtFile(fileContent);
                }
                else
                {
                    Toast.makeText(stopWatchActivity2.this,"Permission to load the data needed!",Toast.LENGTH_SHORT).show();
                }

            }
        }
    }

    private void saveToTxtFile(String fileContent)
    {
        String timeStamp = new SimpleDateFormat("yyyMMdd_HHmmss", Locale.getDefault()).format(System.currentTimeMillis());

        try {
            File path = Environment.getExternalStorageDirectory();
            File dir = new File(path + "/My Files/");
            dir.mkdirs();
            String fileName = "MyFileStopWatch_" + timeStamp + ".txt";
            File file = new File(dir,fileName);

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            String dateCon = "On " + String.valueOf(this.currentTime) + " my Chronometer showed " + fileContent;
            bw.write(dateCon);
            bw.close();

            Toast.makeText(stopWatchActivity2.this,fileName + " is saved at \n" + dir, Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(stopWatchActivity2.this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }




}