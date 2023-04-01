package com.example.astrotool;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.astrotool.calculs.CalculsAstro;
import com.example.astrotool.calculs.MathsFunctions;

import java.text.DecimalFormat;

public class Test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText year = findViewById(R.id.editTextTime);
        EditText month = findViewById(R.id.editTextTime2);
        EditText day = findViewById(R.id.editTextTime3);
        EditText hour = findViewById(R.id.editTextTime4);
        EditText minut = findViewById(R.id.editTextTime5);
        EditText second = findViewById(R.id.editTextTime6);
        Button valider = findViewById(R.id.button2);
        valider.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                int MM = Integer.parseInt(month.getText().toString());
                int YYYY = Integer.parseInt(year.getText().toString());
                int DD = Integer.parseInt(day.getText().toString());
                double hh = Integer.parseInt(hour.getText().toString());
                double mm = Integer.parseInt(minut.getText().toString());
                double ss = Integer.parseInt(second.getText().toString());
                if (1900 < YYYY && YYYY < 2100 ) {

                    double JDT = CalculsAstro.getJD(YYYY, MM, DD, hh, mm, ss);
                    TextView res = findViewById(R.id.textView2);
                    TextView GHA2 = findViewById(R.id.textView3);
                    TextView obl = findViewById(R.id.textView);
                    DecimalFormat decimalFormat1 = new DecimalFormat("###.00000");
                    String DJT_aff = decimalFormat1.format(JDT);
                    String df = "Jour julien : " + DJT_aff;
                    res.setText(df);
                    double gha2 = CalculsAstro.getMeanGHAAries(JDT);
                    String angle2 = MathsFunctions.formatAngle(gha2);
                    GHA2.setText("AHSo Moyen : "+ angle2);
                    double true_obliquity = CalculsAstro.getTrueGHAAries(JDT);
                    String angle = MathsFunctions.formatAngle(true_obliquity);
                    obl.setText("AHSo Vrai : " + angle);
                    double LST =  CalculsAstro.getLST(-2, true_obliquity);
                    LST = MathsFunctions.getHourFromAngle(LST);
                    TextView localSideralTime = findViewById(R.id.textView4);
                    localSideralTime.setText(MathsFunctions.formatHour(LST));






                }
            }
        });




    }
}