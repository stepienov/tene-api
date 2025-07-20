package com.tenedevia.api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "permits")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PermitEntity {

  @Id
  private Long id;

  @Column(name = "permit_desc")
  private String description;
}
