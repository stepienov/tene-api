package com.tenedevia.api.service.impl;

import com.tenedevia.api.dto.BeachDto;
import com.tenedevia.api.dto.WeatherDto;
import com.tenedevia.api.mapper.BeachMapper;
import com.tenedevia.api.repository.BeachRepository;
import com.tenedevia.api.service.BeachService;
import com.tenedevia.api.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BeachServiceImpl implements BeachService {

  private final BeachRepository beachRepository;
  private final WeatherService weatherService;
  private final BeachMapper beachMapper;

  @Override
  public BeachDto getById(Long id) {
    return beachRepository.findById(id)
        .map(entity -> {
          BeachDto dto = beachMapper.toDto(entity);
          WeatherDto weather = weatherService.getWeatherForLocation(
              dto.coordinates().latitude() + "," + dto.coordinates().longitude()
          );
          return new BeachDto(
              dto.id(),
              dto.name(),
              dto.description(),
              dto.coordinates(),
              dto.address(),
              weather
          );
        })
        .orElse(null);
  }

  @Override
  public List<BeachDto> getAll() {
    return beachRepository.findAll().stream()
        .map(beachMapper::toDto)
        .toList();
  }
}
