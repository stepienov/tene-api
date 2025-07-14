package com.tenedevia.api.dto;

public record BeachDto(
    Long id,
    String name,
    String description,
    GeoPointDto coordinates,
    AddressDto address,
    WeatherDto weather
) {}
