package com.example.fitnessapppaladrian;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class stepCalculatorConvertorActivityPage extends AppCompatActivity {

    private Button buttonConv;
    private EditText editText1;
    private EditText editText2;
    private TextView textView1;
    private TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_calculator_convertor_page);

        buttonConv = (Button) findViewById(R.id.button7);
        buttonConv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateConversion();
            }
        });

        textView1 = (TextView) findViewById(R.id.textViewConv1);
        editText1 = (EditText) findViewById(R.id.editTextConv1);

        textView2 = (TextView) findViewById(R.id.textViewConv2);
        editText2 = (EditText) findViewById(R.id.editTextConv2);


    }


    public void calculateConversion()
    {
        int pasiNumber = Integer.parseInt(editText1.getText().toString());

        float pasToKm = (float) (pasiNumber / 1312.335);


        if (pasiNumber >= 0)
        {
            editText2.setText(String.valueOf((pasToKm)));
        }


    }
}