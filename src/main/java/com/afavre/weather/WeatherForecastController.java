package com.afavre.weather;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class WeatherForecastController {

    private final WeatherForecastService weatherForecastService;
    private final WeatherForecastService weatherForecastServiceFailover;

    private static Logger log = LoggerFactory.getLogger(WeatherForecastController.class);


    public WeatherForecastController(@Qualifier("openWeatherForecastService") WeatherForecastService weatherForecastService,
                                     @Qualifier("weatherForecastServiceFailover") WeatherForecastService weatherForecastServiceFailover) {
        this.weatherForecastService = weatherForecastService;
        this.weatherForecastServiceFailover = weatherForecastServiceFailover;
    }

    @GetMapping("/forecast/{country}/{city}")
    public Mono<Forecast> getForecast(@PathVariable("country") String country,
                                      @PathVariable("city") String city) {
        return weatherForecastService.getForecast(country, city)
                                     .onErrorResume(e -> {
            log.warn("Error while getting forecast from external provider [ {} ] - using failover", e.getMessage());
            return weatherForecastServiceFailover.getForecast(country, city);
        });
    }

}
