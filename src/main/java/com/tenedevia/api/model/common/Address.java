package com.tenedevia.api.model.common;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {

  private String street;

  @Column(name = "house_number")
  private String houseNumber;

  private String apartment;

  @Column(name = "postal_code", nullable = false, length = 10)
  private String postalCode;

  @Column(nullable = false)
  private String city;
}
