package com.tenedevia.api.service.impl;

import com.tenedevia.api.dto.PoiDto;
import com.tenedevia.api.dto.PoiSearchRequest;
import com.tenedevia.api.mapper.PoiMapper;
import com.tenedevia.api.model.PoiEntity;
import com.tenedevia.api.query.PoiQueryBuilder;
import com.tenedevia.api.repository.PoiRepository;
import com.tenedevia.api.service.PoiService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PoiServiceImpl implements PoiService {

  private final PoiRepository poiRepository;
  private final PoiQueryBuilder queryBuilder;
  private final PoiMapper poiMapper;

  @Override
  public Page<PoiDto> searchPois(PoiSearchRequest request) {
    String countSql = queryBuilder.buildCountQuery(request.filters());
    int total = poiRepository.countPois(countSql);

    String selectSql = queryBuilder.buildSelectQuery(request.filters(), request.page(), request.size(), request.sort());
    List<PoiEntity> pageEntities = poiRepository.findAllPois(selectSql);

    List<PoiDto> pageDtos = pageEntities.stream().map(poiMapper::toDto).toList();

    return new PageImpl<>(pageDtos, PageRequest.of(request.page(), request.size()), total);
  }
}
