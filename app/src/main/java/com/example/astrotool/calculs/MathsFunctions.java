package com.example.astrotool.calculs;

import java.text.DecimalFormat;

public class MathsFunctions {

    public static double getHourFromAngle (double angle){
        double hourAngle =0;
        return hourAngle = (angle*24)/360;

    }

    public static String formatAngle (double angle){
        int d_int = (int)Math.abs(angle);
        double m = (angle-d_int)*60;
        int m_int = (int)m;
        double s = (m-m_int)*60;
        DecimalFormat decimalFormat = new DecimalFormat("00.0");
        String s_int = decimalFormat.format(s);
        return d_int+ "°"+ m_int + "'"+ s_int+ "''";
    }

    public static String formatHour (double hour){
        int d_int = (int)Math.abs(hour);
        double m = (hour-d_int)*60;
        int m_int = (int)m;
        double s = (m-m_int)*60;
        DecimalFormat decimalFormat = new DecimalFormat("00.0");
        String s_int = decimalFormat.format(s);
        return d_int+ "h"+ m_int + "'"+ s_int+ "''";
    }
}
