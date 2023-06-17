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
        EditText longitude = findViewById(R.id.editTextNumberDecimal1);
        EditText RA = findViewById(R.id.editTextNumberDecimal2);
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
                double longit = Double.parseDouble(longitude.getText().toString());
                double ra = Double.parseDouble(RA.getText().toString());

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
                    double angl1 = MathsFunctions.getHourFromAngle(gha2);
                    String angle2 = MathsFunctions.formatHour(angl1);
                    GHA2.setText("AHSo Moyen : "+ angle2);
                    double true_obliquity = CalculsAstro.getTrueGHAAries(JDT);

                    //double anglE = MathsFunctions.getHourFromAngle(true_obliquity);
                    String angl = MathsFunctions.formatAngle(true_obliquity);
                    obl.setText("AHSo Vrai : " + angl);
                    double LST =  CalculsAstro.getLST(longit, true_obliquity);
                    double RA = MathsFunctions.getAngleFromHour(ra);
                    double LHA = CalculsAstro.getLHA(LST, RA);
                    //LST = MathsFunctions.getHourFromAngle(LST);
                    //LHA = MathsFunctions.getHourFromAngle(LHA);
                    TextView localSideralTime = findViewById(R.id.textView4);
                    TextView localHourAngle = findViewById(R.id.textView5);
                    String lst = MathsFunctions.formatAngle(LST);
                    localSideralTime.setText("LST : "+ lst);
                    String lha = MathsFunctions.formatAngle(LHA);
                    localHourAngle.setText("LHA : "+ lha);






                }
            }
        });




    }
}