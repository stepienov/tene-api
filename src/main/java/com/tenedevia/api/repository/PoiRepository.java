package com.tenedevia.api.repository;

import com.tenedevia.api.model.PoiEntity;

import java.util.List;

public interface PoiRepository {
  List<PoiEntity> findAllPois(String sql);

  int countPois(String sql);
}
