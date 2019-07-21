package com.afavre.weather.openweather.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenWeatherResponse {

    private List<OpenWeatherWeatherObjectResponse> weather;

    private OpenWeatherMainObjectResponse main;

    public OpenWeatherResponse() {
    }

    public List<OpenWeatherWeatherObjectResponse> getWeather() {
        return weather;
    }

    public void setWeather(List<OpenWeatherWeatherObjectResponse> weather) {
        this.weather = weather;
    }

    public OpenWeatherMainObjectResponse getMain() {
        return main;
    }

    public void setMain(OpenWeatherMainObjectResponse main) {
        this.main = main;
    }
}
