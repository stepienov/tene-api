package com.tenedevia.api.dto.external;

import java.util.List;

public record MeteomaticsResponse(
    List<Data> data
) {
  public record Data(
      String parameter,
      List<Coordinate> coordinates
  ) {}

  public record Coordinate(
      double lat,
      double lon,
      List<DateValue> dates
  ) {}

  public record DateValue(
      String date,
      Double value
  ) {}
}
