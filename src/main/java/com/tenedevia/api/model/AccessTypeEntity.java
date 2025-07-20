package com.tenedevia.api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "access_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccessTypeEntity {

  @Id
  private Long id;

  @Column(name = "access_type_desc")
  private String description;
}
