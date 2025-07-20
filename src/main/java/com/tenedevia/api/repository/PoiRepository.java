package com.tenedevia.api.repository;

import com.tenedevia.api.model.PoiEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PoiRepository extends JpaRepository<PoiEntity, Long>, JpaSpecificationExecutor<PoiEntity> {
}
