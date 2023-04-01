package com.example.astrotool.calculs;

public class CalculsAstro {

    public static double getJD (int year, int month, int day, double hour, double minut, double seconds){
        double JDT= 0;
        if (1900 < year && year < 2100 ) {
            if (hour < 12) {
                hour = hour + 12;
                day = day - 1;
            } else {
                hour = hour - 12;
            }
            double dec = hour/24+minut/1440+seconds/86400;
            double JD = 367*year-7*(year+(month+9)/12)/4+275*month/9+day+1721014;
            JDT = JD+dec;
        }
        return JDT;
    }

    public static double getMeanGHAAries(double JDT){
        double T = (JDT-2451545)/36525;
        double gha2 = (280.46061837 + 360.98564736629 * (JDT - 2451545) + 0.000387933 * T * T - ((T * T * T) / 38710000));
        gha2 = gha2 % 360;
        if (gha2<0){
            gha2 = gha2+360;
        }
        return gha2;
    }

    public static double[] getNutation (double JDT){
        double[] nutation = new double[2];
        nutation[0]= 0;
        nutation[1]=0;
        double T = (JDT-2451545)/36525;
        double L = 280.4665 + 36000.7698*T + 0.000303*T*T;
        double omega = 125.0443-1934.13626*T + 0.002075*T*T;
        //double test = Math.cos(omega);
        nutation[0]=-(17.2 + 0.01742*T)* Math.sin(omega*(Math.PI/180)) -(1.3 + 0.00016*T)*Math.sin((2*L)*(Math.PI/180));
        nutation[1]=(9.2 + 0.00089*T)*Math.cos(omega*(Math.PI/180)) + (0.6-0.00031*T)*Math.cos((2*L)*(Math.PI/180));
        //nutation[0]=test;


        return nutation;
    }

    public static double getTrueObliquity (double JDT){
        double[]nutation = getNutation(JDT);
        double T = (JDT-2451545)/36525;
        double mean_obliquity = 23.43929111111-0.01300416667*T-0.00000016389*T*T+0.00000050361*T*T*T;
        double nutation_e = nutation[1]/(60*60);
        return mean_obliquity+nutation_e;
    }

    public static double getTrueGHAAries(double JDT){
        double true_obliquity = getTrueObliquity(JDT);
        double nutation_L = getNutation(JDT)[0];
        double mean_gha = getMeanGHAAries(JDT);
        double true_gha_second =nutation_L*Math.cos(true_obliquity*(Math.PI/180));
        return mean_gha + (true_gha_second/(60*60));

    }

}
