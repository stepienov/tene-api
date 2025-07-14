package com.tenedevia.api.dto.external;

public record WeatherApiResponse(
    CurrentConditions currentConditions
) {
  public record CurrentConditions(
      Double temp,
      String conditions,
      Double windspeed,
      Double winddir,
      Double cloudcover,
      Double precip
  ) {}
}
