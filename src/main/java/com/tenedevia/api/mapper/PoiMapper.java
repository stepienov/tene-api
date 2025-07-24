package com.tenedevia.api.mapper;

import com.tenedevia.api.dto.*;
import com.tenedevia.api.model.*;
import com.tenedevia.api.model.common.GeoPoint;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PoiMapper {

  @Mapping(target = "municipality", expression = "java(entity.getMunicipality() != null ? entity.getMunicipality().getMuniciDesc() : null)")
  @Mapping(target = "region", expression = "java(entity.getMunicipality() != null && entity.getMunicipality().getRegion() != null ? entity.getMunicipality().getRegion().getRegionDirectionDesc() : null)")
  PoiDto toDto(PoiEntity entity);

  GeoPointDto toDto(GeoPoint geoPoint);
}
