package com.tenedevia.api.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeachDto {
  private Long id;
  private String name;
  private String location;
  private String description;
}
