package com.tenedevia.api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "municipalities")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MunicipalityEntity {

  @Id
  private Long id;

  @Column(name = "munici_desc")
  private String municiDesc;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_munici_direction")
  private RegionDirectionEntity region;
}
