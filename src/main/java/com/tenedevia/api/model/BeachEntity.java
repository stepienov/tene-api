package com.tenedevia.api.model;

import com.tenedevia.api.model.common.Address;
import com.tenedevia.api.model.common.GeoPoint;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "beaches")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeachEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String description;

  @Embedded
  private GeoPoint coordinates;

  @Embedded
  private Address address;
}

