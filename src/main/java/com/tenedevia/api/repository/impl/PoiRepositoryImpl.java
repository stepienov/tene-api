package com.tenedevia.api.repository.impl;

import com.tenedevia.api.model.PoiEntity;
import com.tenedevia.api.repository.PoiRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PoiRepositoryImpl implements PoiRepository {

  @PersistenceContext
  private EntityManager em;

  @Override
  public List<PoiEntity> findAllPois(String sql) {
    Query query = em.createNativeQuery(sql, PoiEntity.class);
    return query.getResultList();
  }

  @Override
  public int countPois(String sql) {
    Query query = em.createNativeQuery(sql);
    Number countResult = (Number) query.getSingleResult();
    return countResult.intValue();
  }
}
