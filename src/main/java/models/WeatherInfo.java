package models;

/**
 * Created by Mateusz Kuczyński on 19.07.2017.
 */
public class WeatherInfo {
    private double temp;
    private int pressure;
    private int humidity;
    private int clouds;

    public WeatherInfo(double temp, int pressure, int humidity, int clouds) {
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.clouds = clouds;
    }

    public double getTemp() {
        return temp;
    }

    public int getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public int getClouds() {
        return clouds;
    }
}
