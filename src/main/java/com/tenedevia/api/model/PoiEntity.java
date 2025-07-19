package com.tenedevia.api.model;

import com.tenedevia.api.model.common.Address;
import com.tenedevia.api.model.common.GeoPoint;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "points_of_interest")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PoiEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name_of_point")
  private String name;

  @Column(name = "description_of_interest")
  private String description;

  @Embedded
  @AttributeOverrides({
      @AttributeOverride(name = "latitude", column = @Column(name = "latitude")),
      @AttributeOverride(name = "longitude", column = @Column(name = "longitude"))
  })
  private GeoPoint coordinates;

}

