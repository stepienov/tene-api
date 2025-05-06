package com.tenedevia.api.model;

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
  private String location;
  private String description;
}
