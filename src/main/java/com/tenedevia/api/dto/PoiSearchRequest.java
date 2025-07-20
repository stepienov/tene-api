package com.tenedevia.api.dto;

import java.util.Map;

public record PoiSearchRequest(
    Map<String, Object> filters,
    int page,
    int size,
    String sort
) {}