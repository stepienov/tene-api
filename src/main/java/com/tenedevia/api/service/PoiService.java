package com.tenedevia.api.service;

import com.tenedevia.api.dto.PoiDto;
import com.tenedevia.api.dto.PoiSearchRequest;
import org.springframework.data.domain.Page;

public interface PoiService {
  Page<PoiDto> searchPois(PoiSearchRequest request);
}
