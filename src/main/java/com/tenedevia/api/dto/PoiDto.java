package com.tenedevia.api.dto;

public record PoiDto(
    Long id,
    String name,
    String description,
    GeoPointDto coordinates,
    String municipality,
    String region,
    WeatherDto weather
) {}
