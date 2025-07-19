package com.tenedevia.api.mapper;

import com.tenedevia.api.dto.*;
import com.tenedevia.api.model.*;
import com.tenedevia.api.model.common.GeoPoint;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BeachMapper {
  BeachDto toDto(PoiEntity entity);
  GeoPointDto toDto(GeoPoint geoPoint);
}