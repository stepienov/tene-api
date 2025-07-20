package com.tenedevia.api.mapper;

import com.tenedevia.api.dto.*;
import com.tenedevia.api.model.PoiEntity;
import com.tenedevia.api.model.common.GeoPoint;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PoiMapper {
  PoiDto toDto(PoiEntity entity);
  GeoPointDto toDto(GeoPoint geoPoint);
}
