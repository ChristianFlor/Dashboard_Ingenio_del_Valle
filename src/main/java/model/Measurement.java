package model;

import java.util.Date;

public class Measurement {

    private String id;
    private double pH;
    private double humidity;
    private double co2;
    private double temperature;
    private long dateTime;

    public Measurement(String id, double pH, double humidity, double co2, double temperature, long dateTime) {
        this.id = id;
        this.pH = pH;
        this.humidity = humidity;
        this.co2 = co2;
        this.temperature = temperature;
        this.dateTime = dateTime;
    }

    public Measurement() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getpH() {
        return pH;
    }

    public void setpH(double pH) {
        this.pH = pH;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getCo2() {
        return co2;
    }

    public void setCo2(double co2) {
        this.co2 = co2;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public long getDateTime() {
        return dateTime;
    }

    public void setDateTime(long dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "id='" + id + '\'' +
                ", pH=" + pH +
                ", humidity=" + humidity +
                ", co2=" + co2 +
                ", temperature=" + temperature +
                ", dateTime=" + dateTime +
                '}';
    }
}
