package models.services;

import models.Config;
import models.WeatherInfo;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mateusz Kuczyński on 19.07.2017.
 */
public class WeatherService {

    private static WeatherService ourService = new WeatherService(new HttpService());
    public static WeatherService getService(){
        return ourService;
    }


    private List<IWeatherObserver> observerList = new ArrayList<>();
    private HttpService httpService;
    private double temp;
    private int pressure;
    private int humidity;
    private int clouds;


    private void parseJson (String json){
        JSONObject rootObject = new JSONObject(json);
        if (rootObject.getInt("cod") != 200){
            System.out.println("Miasto nie istnieje");
            temp = 0;
            pressure = 0;
            humidity = 0;
            clouds = 0;
            return;
        }
        JSONObject mainObject = rootObject.getJSONObject("main");
        JSONObject cloudsObject = rootObject.getJSONObject("clouds");


        temp = mainObject.getDouble("temp");
        pressure = mainObject.getInt("pressure");
        humidity = mainObject.getInt("humidity");
        clouds = cloudsObject.getInt("all");

        notifyObserver();
    }

    private WeatherService(HttpService httpService){
        this.httpService = httpService;
    }

    public void makeCall(String city) throws IOException {
        parseJson(httpService.connectAndResponse(Config.APP_URL + "?q=" + city + "&appid=" + Config.APP_ID));
    }
    public  void registerObserver(IWeatherObserver observer){
        observerList.add(observer);
    }
    public void notifyObserver(){
        WeatherInfo weatherInfo = new WeatherInfo(temp, pressure, humidity, clouds);
        for (IWeatherObserver iWeatherObserver : observerList){
            iWeatherObserver.onWeatherUpdate(weatherInfo);
        }
    }
}
