package com.tenedevia.api.model;

import com.tenedevia.api.model.common.GeoPoint;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "points_of_interest")
@Getter
@Setter
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

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "municipality_id")
  private MunicipalityEntity municipality;

  @OneToOne(fetch = FetchType.LAZY, mappedBy = "poi")
  private BeachDetailsEntity beachDetails;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "access_type_id")
  private AccessTypeEntity accessType;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "visit_duration_id")
  private VisitDurationTypeEntity visitDurationType;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "point_type_id")
  private PointTypeEntity pointType;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "best_time_id")
  private BestTimeToVisitTypeEntity bestTimeToVisitType;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "permit_id")
  private PermitEntity permit;
}
