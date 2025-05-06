package com.tenedevia.api.controller;

import com.tenedevia.api.dto.BeachDto;
import com.tenedevia.api.service.BeachService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/beaches")
@RequiredArgsConstructor
@CrossOrigin
public class BeachController {

  private final BeachService beachService;

  @GetMapping
  public List<BeachDto> getAll() {
    return beachService.getAll();
  }

  @GetMapping("/{id}")
  public BeachDto getById(@PathVariable Long id) {
    return beachService.getById(id);
  }
}
