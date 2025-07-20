package com.tenedevia.api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "point_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PointTypeEntity {

  @Id
  private Long id;

  @Column(name = "point_type_desc")
  private String description;
}
