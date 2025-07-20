package com.tenedevia.api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "visit_duration_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VisitDurationTypeEntity {

  @Id
  private Long id;

  @Column(name = "duration_label")
  private String label;

  @Column(name = "duration_minutes")
  private Integer minutes;
}
