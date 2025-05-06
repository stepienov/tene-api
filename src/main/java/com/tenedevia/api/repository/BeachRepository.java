package com.tenedevia.api.repository;

import com.tenedevia.api.model.BeachEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeachRepository extends JpaRepository<BeachEntity, Long> {
}
