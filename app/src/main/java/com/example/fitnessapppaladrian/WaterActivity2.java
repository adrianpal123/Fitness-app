package com.example.fitnessapppaladrian;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.Calendar;

public class WaterActivity2 extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    private TextView txt;
    private Button buttonDay;
    private Button buttonNight;
    private Button buttonHomePage;
    public int ml150=0;
    public int ml200=0;
    public int ml270=0;



    public static int minutes;
    public static String theTime;

    public static boolean dayCheck = false;
    public static boolean nightCheck = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water2);

        txt = (TextView) findViewById(R.id.alarmOutput);


        if (waterActivity.clickedbutton150ml == true) {
            ml150 = 150;
            ml200 = 0;
            ml270 = 0;
        }
        if (waterActivity.clickedbutton200ml == true) {
            ml150 = 0;
            ml200 = 200;
            ml270 = 0;
        }
        if (waterActivity.clickedbutton270ml == true)
        {
            ml150 = 0;
            ml200 = 0;
            ml270 = 270;
        }


        System.out.println(ml150);
        System.out.println(ml200);
        System.out.println(ml270);

        System.out.println(WaterActivityFirst.totalWater);
        // Cantitatea totala de apa pe care a ales-o sa o bea/Cantitatea canii de apa alese.  2000/200 = 10;
        // Daca alege pe zi va fi 12/10 = 1.2 ore;
        // Daca alege si pe timpul noptii va fi 24/10 = 2.4 ore;
        System.out.println(getHowManyTimesItNeedsToRingDay());
        System.out.println(getHowManyTimesItNeedsToRingNight());

        System.out.println(minutesDay());
        System.out.println(minutesNight());


        buttonDay = (Button) findViewById(R.id.buttonDay);
        buttonDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theTime = "during the day";
                txt.setText("This alarm rings during the day! EVERY " + minutesDay() + " minutes.");
                minutes = minutesDay();
                myButtonDayApp();
            }
        });


        buttonNight = (Button) findViewById(R.id.buttonNight);
        buttonNight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theTime = "during the day and during the night";
                txt.setText("This alarm rings during the day and during the night! EVERY " + minutesNight() + " minutes.");
                minutes = minutesNight();
                myButtonNightApp();
            }
        });


        buttonHomePage = (Button) findViewById(R.id.buttonHomepageAlarm2);
        buttonHomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToHomePageAlarm2();
            }
        });







    }

    public float getHowManyTimesItNeedsToRingDay()
    {
        float imp;
        DecimalFormat df = new DecimalFormat("#.####");
        if (ml150>0)
            imp = WaterActivityFirst.totalWater/(float)ml150+1;
        else if (ml200>0)
            imp = WaterActivityFirst.totalWater/(float)ml200+1;
        else
            imp = WaterActivityFirst.totalWater/(float)ml270+1;

        if (imp>0)
            return Float.parseFloat(df.format(12/imp));
        return 0;

    }
    public float getHowManyTimesItNeedsToRingNight()
    {
        float imp;
        DecimalFormat df = new DecimalFormat("#.####");
        if (ml150>0)
            imp = WaterActivityFirst.totalWater/(float)ml150+1;
        else if (ml200>0)
            imp = WaterActivityFirst.totalWater/(float)ml200+1;
        else
            imp = WaterActivityFirst.totalWater/(float)ml270+1;

        if (imp>0)
            return Float.parseFloat(df.format(24/imp));
        return 0;


    }

    public int transformToMinutesDay (float x)
    {
        return (int) (x*60);
    }
    public int transformToMinutesNight (float x)
    {
        return (int) (x*60);
    }
    public int minutesDay ()
    {
        return transformToMinutesDay(getHowManyTimesItNeedsToRingDay());
    }
    public int minutesNight ()
    {
        return transformToMinutesNight(getHowManyTimesItNeedsToRingNight());
    }

    public void myButtonDayApp()
    {
        startAlarm(true);
    }

    public void myButtonNightApp()
    {
        startAlarm(false);

    }


    private void startAlarm(boolean isday)
    {
        AlarmManager alarmManager;
        PendingIntent pendingIntent;
        Intent alarmIntent;


        Calendar calendar;
        calendar = Calendar.getInstance();
        int currentHour;
        currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        currentHour = calendar.get(Calendar.MINUTE);

        alarmManager  = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmIntent = new Intent(WaterActivity2.this,AlarmNotificationReciever.class);

        calendar = Calendar.getInstance();
        currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        currentHour = calendar.get(Calendar.MINUTE);
        pendingIntent = PendingIntent.getBroadcast(this,0,alarmIntent,0);

        if (!isday)
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,SystemClock.elapsedRealtime()+minutesNight(),minutesNight(),pendingIntent);
        else
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,SystemClock.elapsedRealtime()+minutesDay(),minutesDay(),pendingIntent);

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

    }

    public void backToHomePageAlarm2()
    {
        Intent intent = new Intent(this, MainActivity.class );
        startActivity(intent);
    }
}