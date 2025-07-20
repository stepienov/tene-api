package com.tenedevia.api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "beach_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeachDetailsEntity {

  @Id
  private Long id;

  @MapsId
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "poi_id")
  private PoiEntity poi;

  private Boolean isSandy;
  private Boolean hasShower;
  private Boolean hasLifeguard;
}
