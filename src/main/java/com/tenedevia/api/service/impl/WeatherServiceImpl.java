package com.tenedevia.api.service.impl;

import com.tenedevia.api.dto.external.MeteomaticsResponse;
import com.tenedevia.api.dto.external.MeteomaticsResponse.Data;
import com.tenedevia.api.dto.WeatherDto;
import com.tenedevia.api.service.WeatherService;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class WeatherServiceImpl implements WeatherService {

  @Value("${meteomatics.username}")
  private String username;

  @Value("${meteomatics.password}")
  private String password;

  private final WebClient webClient;

  public WeatherServiceImpl(WebClient.Builder webClientBuilder) {
    this.webClient = webClientBuilder
        .baseUrl("https://api.meteomatics.com")
        .build();
  }

  @Override
  public WeatherDto getWeatherForLocation(String location) {
    String datetime = "now";
    String parameters = "t_2m:C,wind_speed_10m:kmh,wind_dir_10m:d";
    String uri = String.format("/%s/%s/%s/json", datetime, parameters, location);

    MeteomaticsResponse response = webClient.get()
        .uri(uri)
        .headers(headers -> headers.setBasicAuth(username, password))
        .retrieve()
        .bodyToMono(MeteomaticsResponse.class)
        .block();

    if (response != null && response.data() != null && !response.data().isEmpty()) {
      var data = response.data();

      Double temp = getValue(data, "t_2m:C");
      Double windSpeed = getValue(data, "wind_speed_10m:kmh");
      Double windDir = getValue(data, "wind_dir_10m:d");

      return new WeatherDto(temp, "OK", windSpeed, windDir, null, null);
    }

    return new WeatherDto(null, "No data", null, null, null, null);
  }

  private Double getValue(List<Data> dataList, String parameter) {
    return dataList.stream()
        .filter(d -> d.parameter().equals(parameter))
        .findFirst()
        .map(d -> d.coordinates().get(0).dates().get(0).value())
        .orElse(null);
  }
}