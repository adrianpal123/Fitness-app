package com.example.fitnessapppaladrian;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class stepCalculatorActivitypage extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private TextView stepsTaken;
    private boolean running;
    private Sensor mStepCounter;
    private int stepCount=0;

    private Button convertorButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_calculator_activitypage);

        convertorButton = (Button) findViewById(R.id.button6);
        convertorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertorButtonActivityButton();
            }
        });

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        stepsTaken = (TextView) findViewById(R.id.showStepsTaken);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!=null)
        {
            mStepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            running = true;
        }
        else
        {
            stepsTaken.setText("Counter Sensor is not Present");
            running = false;
        }




    }
    public void convertorButtonActivityButton()
    {
        Intent intent = new Intent(this,stepCalculatorConvertorActivityPage.class);
        startActivity(intent);

    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor == mStepCounter)
        {
            stepCount = (int) event.values[0];
            stepsTaken.setText(String.valueOf(stepCount));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!=null)
            sensorManager.registerListener(this,mStepCounter,sensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!=null)
            sensorManager.unregisterListener(this,mStepCounter);

    }
}