package com.example.astrotool;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.astrotool.calculs.CalculsAstro;

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
                    int d_int = (int)Math.abs(gha2);
                    double m = (gha2-d_int)*60;
                    int m_int = (int)m;
                    double s = (m-m_int)*60;
                    DecimalFormat decimalFormat = new DecimalFormat("00.0");
                    String s_int = decimalFormat.format(s);
                    GHA2.setText("AHso moyen : " +d_int+ "°"+ m_int + "'"+ s_int+ "''");
                    double true_obliquity = CalculsAstro.getTrueGHAAries(JDT);
                    int d_int2 = (int)true_obliquity;
                    double m2 = (true_obliquity-d_int2)*60;
                    int m_int2 = (int)m2;
                    double s2 = (m2-m_int2)*60;
                    String s_int2 = decimalFormat.format(s2);
                    obl.setText("AHso vrai : "+d_int2+"°"+m_int2+"'"+s_int2+"''");
                    //obl.setText(String.valueOf(true_obliquity));





                }
            }
        });




    }
}