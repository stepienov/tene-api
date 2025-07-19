package com.tenedevia.api.repository;

import com.tenedevia.api.model.PoiEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

public interface BeachRepository extends JpaRepository<PoiEntity, Long> {

  @NonNull
  @Query(value = "SELECT * FROM points_of_interest WHERE id = :id", nativeQuery = true)
  Optional<PoiEntity> findById(@NonNull @Param("id") Long id);





}
