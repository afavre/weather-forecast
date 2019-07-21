package com.afavre.weather.openweather;

import com.afavre.weather.Forecast;
import com.afavre.weather.WeatherForecastService;
import com.afavre.weather.openweather.response.OpenWeatherResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class OpenWeatherForecastService implements WeatherForecastService {

    private static Logger log = LoggerFactory.getLogger(OpenWeatherForecastService.class);

    private final WebClient client;

    private final OpenWeatherConfig config;

    public OpenWeatherForecastService(WebClient client, OpenWeatherConfig openWeatherConfig) {
        this.client = client;
        this.config = openWeatherConfig;
    }

    @Override
    @NewSpan("forecast_open-weather")
    public Mono<Forecast> getForecast(String country, String city) {
        WebClient.RequestHeadersSpec<?> uri = client.get()
                                                    .uri(builder -> builder.path(config.getPath())
                                                                           .queryParam("q", city + "," + country)
                                                                           .queryParam("appId",
                                                                                       config.getAppId())
                                                                           .queryParam("units", "metric")
                                                                           .build());
        return uri.accept(MediaType.APPLICATION_JSON)
                  .retrieve()
                  .bodyToMono(OpenWeatherResponse.class)
                  .map(value -> new Forecast(country,
                                             city,
                                             value.getWeather() != null ? value.getWeather().get(0).getMain(): null, // could trigger NPE
                                             value.getMain() != null ? value.getMain().getTemp(): null))
                  .log();
    }

}
