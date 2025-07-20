package com.tenedevia.api.specification;

import com.tenedevia.api.model.PoiEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PoiSpecification {

  public static Specification<PoiEntity> withFilters(Map<String, Object> filters) {
    return (root, query, cb) -> {
      List<Predicate> predicates = new ArrayList<>();

      filters.forEach((key, value) -> {
        if (value == null) return;

        switch (key) {
          case "name" -> predicates.add(cb.like(cb.lower(root.get("name")), "%" + value.toString().toLowerCase() + "%"));
          case "description" -> predicates.add(cb.like(cb.lower(root.get("description")), "%" + value.toString().toLowerCase() + "%"));
          case "municipality.id" -> predicates.add(cb.equal(root.get("municipality").get("id"), value));
          case "municipality.name" -> predicates.add(cb.like(cb.lower(root.get("municipality").get("municiDesc")), "%" + value.toString().toLowerCase() + "%"));
          case "region.id" -> predicates.add(cb.equal(root.get("municipality").get("region").get("id"), value));
          case "region.name" -> predicates.add(cb.like(cb.lower(root.get("municipality").get("region").get("regionDirectionDesc")), "%" + value.toString().toLowerCase() + "%"));
          case "details.isSandy" -> predicates.add(cb.equal(root.get("beachDetails").get("isSandy"), value));
          case "details.hasShower" -> predicates.add(cb.equal(root.get("beachDetails").get("hasShower"), value));
          case "details.hasLifeguard" -> predicates.add(cb.equal(root.get("beachDetails").get("hasLifeguard"), value));
          case "accessType.id" -> predicates.add(cb.equal(root.get("accessType").get("id"), value));
          case "visitDuration.id" -> predicates.add(cb.equal(root.get("visitDurationType").get("id"), value));
          case "pointType.id" -> predicates.add(cb.equal(root.get("pointType").get("id"), value));
          case "bestTime.id" -> predicates.add(cb.equal(root.get("bestTimeToVisitType").get("id"), value));
          case "permit.id" -> predicates.add(cb.equal(root.get("permit").get("id"), value));
        }
      });

      return cb.and(predicates.toArray(new Predicate[0]));
    };
  }
}
