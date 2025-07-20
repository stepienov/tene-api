package com.tenedevia.api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "region_directions")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegionDirectionEntity {

  @Id
  private Long id;

  @Column(name = "region_direction_desc")
  private String regionDirectionDesc;
}
