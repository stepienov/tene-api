package com.tenedevia.api.service.impl;

import com.tenedevia.api.dto.BeachDto;
import com.tenedevia.api.model.BeachEntity;
import com.tenedevia.api.repository.BeachRepository;
import com.tenedevia.api.service.BeachService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BeachServiceImpl implements BeachService {

  private final BeachRepository beachRepository;

  @Override
  public List<BeachDto> getAll() {
    return beachRepository.findAll().stream()
        .map(this::toDto)
        .collect(Collectors.toList());
  }

  @Override
  public BeachDto getById(Long id) {
    return beachRepository.findById(id)
        .map(this::toDto)
        .orElse(null);
  }

  private BeachDto toDto(BeachEntity entity) {
    return BeachDto.builder()
        .id(entity.getId())
        .name(entity.getName())
        .location(entity.getLocation())
        .description(entity.getDescription())
        .build();
  }
}
