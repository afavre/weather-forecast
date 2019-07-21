package com.afavre.weather;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class WeatherForecastServiceFailover implements WeatherForecastService {

    private static Logger log = LoggerFactory.getLogger(WeatherForecastServiceFailover.class);

    @NewSpan("forecast_failover")
    public Mono<Forecast> getForecast(String country, String city) {
        return Mono.zip(getForecast(), getTemperature())
                   .map(tuple -> new Forecast(country,
                                              city,
                                              tuple.getT1(),
                                              tuple.getT2()));

    }

    // Mono is not really useful right now, but it could be a call to an external service
    private Mono<Integer> getTemperature() {
        return Mono.just(new Random().nextInt(30));
    }

    private Mono<String> getForecast() {
        List<String> weathers = Arrays.asList("sunny", "rainy", "cloudy", "snowy", "stormy");
        return Mono.just(weathers.get(new Random().nextInt(weathers.size())));
    }

}
