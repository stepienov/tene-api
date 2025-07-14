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
public class GeoPoint {
  @Column(nullable = false)
  private Double latitude;

  @Column(nullable = false)
  private Double longitude;
}

