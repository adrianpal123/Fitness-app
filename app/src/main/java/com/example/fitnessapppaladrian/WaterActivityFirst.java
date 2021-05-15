package com.example.fitnessapppaladrian;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class WaterActivityFirst extends AppCompatActivity {

    private Button buttonSetML;
    private EditText checktext;
    private String totalWaterIntake;
    public static int totalWater;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_first);

        buttonSetML = (Button) findViewById(R.id.ButtonForSelectionQuantity);
        checktext = (EditText) findViewById(R.id.editTextNumber1);




        buttonSetML.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                totalWaterIntake = checktext.getText().toString();
                if (totalWaterIntake.isEmpty())
                Toast.makeText(WaterActivityFirst.this, "I want to drink " + 0 + " ml of water today!", Toast.LENGTH_SHORT).show();
                else {
                    totalWater = Integer.parseInt(totalWaterIntake);
                    Toast.makeText(WaterActivityFirst.this, "I want to drink " + totalWaterIntake + " ml of water today!", Toast.LENGTH_SHORT).show();
                }


                if (totalWaterIntake.isEmpty()) {
                        Toast.makeText(WaterActivityFirst.this, "Select how much water you want to drink today!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        openSelection();
                    }

                }



        });

    }

    public void openSelection() {
        Intent intent = new Intent(this, waterActivity.class);
        startActivity(intent);

    }

}