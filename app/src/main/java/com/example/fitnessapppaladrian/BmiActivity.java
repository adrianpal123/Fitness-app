package com.example.fitnessapppaladrian;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class BmiActivity extends AppCompatActivity {

    Button button;
    Button buttonHomePage;
    EditText heightValue;
    EditText weightValue;
    TextView outputValue;
    TextView displayResult;

    Date currentTime = Calendar.getInstance().getTime();
    String fileName = "";
    String filePath = "";
    String fileContent = "";

    private static final int WRITE_EXTERNAL_STORAGE_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        button = (Button) findViewById(R.id.button5);
        weightValue = (EditText) findViewById(R.id.editText3);
        heightValue = (EditText) findViewById(R.id.editText4);
        outputValue = (TextView) findViewById(R.id.Result);
        displayResult = (TextView) findViewById(R.id.textView4);

        fileName = "myFile.txt";
        filePath = "myFileDir";


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calculateBmi();
                displayTheResult();
                fileContent = outputValue.getText().toString().trim();
                if (fileContent.isEmpty()) {
                    Toast.makeText(BmiActivity.this, "The value is invalid, please type in the correct data.",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(BmiActivity.this, "The value has been saved in a file!",Toast.LENGTH_SHORT).show();
                    ;
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
                    Toast.makeText(BmiActivity.this,"Permission to access the file is needed.",Toast.LENGTH_SHORT).show();
                }

            }
        }
    }

    public static void appendStrToFile(String fileName,String str)
    {
        try {

            BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true));
            out.write(str);
            out.flush();
            out.close();
        }
        catch (IOException e) {
            System.out.println("exception occoured" + e);
        }
    }

    private void saveToTxtFile(String fileContent)
        {
            String timeStamp = new SimpleDateFormat("yyyMMdd_HHmmss", Locale.getDefault()).format(System.currentTimeMillis());

            try {
                File path = Environment.getExternalStorageDirectory();
                File dir = new File(path + "/My Files/");
                dir.mkdirs();
                String fileName = "MyFileBMI_" + timeStamp + ".txt";
                File file = new File(dir,fileName);

                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                String dateCon = "On " + String.valueOf(this.currentTime) + " my BMI is: " + fileContent + ", which indicates that I am: " + displayResult.getText();
                bw.write(dateCon);
                bw.close();

            Toast.makeText(BmiActivity.this,fileName + " is saved in \n" + dir, Toast.LENGTH_SHORT).show();
            }catch (Exception e){
                Toast.makeText(BmiActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }


            public void calculateBmi() {
                int height = Integer.parseInt(heightValue.getText().toString());
                int weight = Integer.parseInt(weightValue.getText().toString());

                float formula;

                formula = (float) weight / (height * height) * 10000;
                if (formula >= 18.9 && formula <= 24.9) {
                    outputValue.setTextColor(Color.BLUE);
                    outputValue.setText(String.format("%.1f", formula));

                } else if (formula >= 25 && formula <= 29.9) {
                    outputValue.setTextColor(Color.GREEN);
                    outputValue.setText(String.format("%.1f", formula));
                } else if (formula >= 30 && formula <= 39.9) {
                    outputValue.setTextColor(Color.YELLOW);
                    outputValue.setText(String.format("%.1f", formula));
                } else {
                    outputValue.setTextColor(Color.RED);
                    outputValue.setText(String.format("%.1f", formula));

                }


            }

            public void displayTheResult() {
                int height = Integer.parseInt(heightValue.getText().toString());
                int weight = Integer.parseInt(weightValue.getText().toString());

                float formula;

                formula = (float) weight / (height * height) * 10000;

                if (formula >= 18.9 && formula <= 24.9) {
                    displayResult.setTextColor(Color.BLUE);
                    displayResult.setTypeface(null, Typeface.BOLD_ITALIC);
                    displayResult.setText("Normal");

                } else if (formula >= 25 && formula <= 29.9) {
                    displayResult.setTextColor(Color.GREEN);
                    displayResult.setTypeface(null, Typeface.BOLD_ITALIC);
                    displayResult.setText("Overweight");
                } else if (formula >= 30 && formula <= 39.9) {
                    displayResult.setTextColor(Color.YELLOW);
                    displayResult.setTypeface(null, Typeface.BOLD_ITALIC);
                    displayResult.setText("Obese");
                } else if (formula >= 40 && formula <= 49.9) {
                    displayResult.setTextColor(Color.RED);
                    displayResult.setTypeface(null, Typeface.BOLD_ITALIC);
                    displayResult.setText("Morbidly Obese");
                }

            }


    public void backToHomePageAlarm2()
    {
        Intent intent = new Intent(this, MainActivity.class );
        startActivity(intent);
    }

}






