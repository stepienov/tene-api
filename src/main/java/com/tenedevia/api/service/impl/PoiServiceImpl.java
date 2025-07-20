package com.tenedevia.api.service.impl;

import com.tenedevia.api.dto.PoiDto;
import com.tenedevia.api.dto.PoiSearchRequest;
import com.tenedevia.api.dto.WeatherDto;
import com.tenedevia.api.mapper.PoiMapper;
import com.tenedevia.api.model.PoiEntity;
import com.tenedevia.api.repository.PoiRepository;
import com.tenedevia.api.service.PoiService;
import com.tenedevia.api.service.WeatherService;
import com.tenedevia.api.specification.PoiSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PoiServiceImpl implements PoiService {

  private final PoiRepository poiRepository;
  private final PoiMapper poiMapper;
  private final WeatherService weatherService;

  @Override
  public Page<PoiDto> searchPois(PoiSearchRequest request) {
    String sortField = mapSortField(request.sort());
    Pageable pageable = PageRequest.of(
        request.page(),
        request.size(),
        Sort.by(sortField != null ? sortField : "id")
    );

    var spec = PoiSpecification.withFilters(request.filters());
    Page<PoiEntity> entityPage = poiRepository.findAll(spec, pageable);

    List<PoiDto> dtoList = entityPage.stream()
        .map(entity -> {
          WeatherDto weather = null;
          if (entity.getCoordinates() != null) {
            String loc = entity.getCoordinates().getLatitude() + "," + entity.getCoordinates().getLongitude();
            weather = weatherService.getWeatherForLocation(loc);
          }
          return new PoiDto(
              entity.getId(),
              entity.getName(),
              entity.getDescription(),
              poiMapper.toDto(entity.getCoordinates()),
              entity.getMunicipality() != null ? entity.getMunicipality().getMuniciDesc() : null,
              entity.getMunicipality() != null && entity.getMunicipality().getRegion() != null
                  ? entity.getMunicipality().getRegion().getRegionDirectionDesc()
                  : null,
              weather
          );
        })
        .toList();

    return new PageImpl<>(dtoList, pageable, entityPage.getTotalElements());
  }

  private String mapSortField(String sort) {
    if (sort == null || sort.isBlank()) return "id";
    return switch (sort) {
      case "poi.name" -> "name";
      case "municipality.name" -> "municipality.municiDesc";
      case "region.name" -> "municipality.region.regionDirectionDesc";
      default -> sort;
    };
  }
}
