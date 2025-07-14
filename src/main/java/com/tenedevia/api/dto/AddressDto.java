package com.tenedevia.api.dto;

public record AddressDto(
    String street,
    String houseNumber,
    String apartment,
    String postalCode,
    String city
) {}