package com.tenedevia.api.service;

import com.tenedevia.api.dto.WeatherDto;

public interface WeatherService {
  WeatherDto getWeatherForLocation(String location);
}

