package com.tenedevia.api.dto;

public record WeatherDto(
    Double temperature,
    String conditions,
    Double windSpeed,
    Double windDirection,
    Double cloudCover,
    Double precipitation
) {}
