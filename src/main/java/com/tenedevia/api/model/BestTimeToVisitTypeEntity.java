package com.tenedevia.api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "best_time_to_visit_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BestTimeToVisitTypeEntity {

  @Id
  private Long id;

  @Column(name = "best_time_desc")
  private String description;
}
