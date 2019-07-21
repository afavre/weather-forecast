package com.afavre.weather;

import reactor.core.publisher.Mono;

public interface WeatherForecastService {

    Mono<Forecast> getForecast(String country, String city);

}
