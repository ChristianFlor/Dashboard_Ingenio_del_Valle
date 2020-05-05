package model;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Simulation {

    public static final char PH = 'a';
    public static final char HUMIDITY = 'b';
    public static final char TEMPERATURE = 'c';
    public static final char CO2 = 'd';

    public static final Double MIN_PH = 0.0;
    public static final Double MIN_HUM = 0.0;
    public static final Double MIN_TEMP = 0.0;
    public static final Double MIN_CO2 = 0.0;

    public static final Double MAX_PH = 0.0;
    public static final Double MAX_HUM = 0.0;
    public static final Double MAX_TEMP = 0.0;
    public static final Double MAX_CO2 = 0.0;

    public Simulation() {
    }

    public ArrayList<Double> simulate(char mode, int periodnum) {
        ArrayList<Double> res = new ArrayList<>();
        double minrange, maxrange;
        switch (mode) {
            case PH:
                minrange=MIN_PH;maxrange=MAX_PH;
                break;
            case HUMIDITY:
                minrange=MIN_HUM;maxrange=MAX_HUM;
                break;
            case TEMPERATURE:
                minrange=MIN_TEMP;maxrange=MAX_TEMP;
                break;
            case CO2:
                minrange=MIN_CO2;maxrange=MAX_CO2;
                break;
            default:
                return res;
        }
        for (int i = 0; i < periodnum; i++) {
            res.add(ThreadLocalRandom.current().nextDouble(minrange, maxrange));
        }
        return res;
    }
}
