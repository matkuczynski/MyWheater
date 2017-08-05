package controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import models.WeatherInfo;
import models.services.IWeatherObserver;
import models.services.WeatherService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable, IWeatherObserver {
    @FXML
    Button buttonShowWeather;
    @FXML
    TextField textFieldChooseCity;
    @FXML
    Label labelResults;


    private WeatherService weatherService = WeatherService.getService();


    public void initialize(URL location, ResourceBundle resources) {
        weatherService.registerObserver(this);
        buttonShowWeather.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                try {
                    weatherService.makeCall(textFieldChooseCity.getText());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void onWeatherUpdate(WeatherInfo info) {
        labelResults.setText("Temperatura: " + info.getTemp() + "C" + "\n"
                + "Cisnienie: " + info.getPressure() + "hPa" + "\n"
                + "Wilgotnosc: " + info.getHumidity() +  "%" + "\n"
                + "Zachmurzenie: " + info.getClouds() + "%");

    }
}

