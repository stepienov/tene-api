package com.tenedevia.api.controller;

import com.tenedevia.api.dto.PoiDto;
import com.tenedevia.api.dto.PoiSearchRequest;
import com.tenedevia.api.service.PoiService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pois")
@RequiredArgsConstructor
@CrossOrigin
public class PoiController {

  private final PoiService poiService;

  @PostMapping("/search")
  public Page<PoiDto> searchPois(@RequestBody PoiSearchRequest request) {
    System.out.println(request);
    return poiService.searchPois(request);
  }
}
