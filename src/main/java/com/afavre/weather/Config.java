package com.afavre.weather;

import com.afavre.weather.openweather.OpenWeatherConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class Config {

    @Bean
    public WebClient webClientOpenWeather(OpenWeatherConfig openWeatherConfig) {
        return WebClient.builder()
                        .baseUrl(openWeatherConfig.getEndpoint()).build();

    }

}
