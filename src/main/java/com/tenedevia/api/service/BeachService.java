package com.tenedevia.api.service;

import com.tenedevia.api.dto.BeachDto;
import java.util.List;

public interface BeachService {
  List<BeachDto> getAll();
  BeachDto getById(Long id);
}
