package com.afavre.weather;

public class Forecast {

    private final String city;

    private final String country;

    private final String forecast;

    private final Integer temperature;

    public Forecast(String country, String city, String forecast, Integer temperature) {
        this.city = city;
        this.country = country;
        this.forecast = forecast;
        this.temperature = temperature;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getForecast() {
        return forecast;
    }

    public Integer getTemperature() {
        return temperature;
    }

    @Override
    public String toString() {
        return "Forecast{" +
               "city='" + city + '\'' +
               ", country='" + country + '\'' +
               ", forecast='" + forecast + '\'' +
               ", temperature=" + temperature +
               '}';
    }
}
