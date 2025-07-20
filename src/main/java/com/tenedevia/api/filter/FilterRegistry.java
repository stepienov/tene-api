package com.tenedevia.api.filter;

import java.util.HashMap;
import java.util.Map;

public class FilterRegistry {
  private static final FilterRegistry INSTANCE = new FilterRegistry();
  private final Map<String, FilterDefinition> filters = new HashMap<>();
  private final Map<String, String> aliasToJoin = new HashMap<>();
  private final Map<String, String> aliasToDependency = new HashMap<>();

  private FilterRegistry() {
    // BASE
    filters.put("id", new EqualFilter("poi", "id", FilterType.BASE, null, null));
    filters.put("name", new LikeFilter("poi", "name_of_point", FilterType.BASE, null, null));
    filters.put("description", new LikeFilter("poi", "description_of_interest", FilterType.BASE, null, null));
    filters.put("isFree", new EqualFilter("poi", "is_free", FilterType.BASE, null, null));

    // MUNICIPALITIES
    registerAlias("m", "LEFT JOIN municipalities m ON poi.municipality_id = m.id", null);
    filters.put("municipality.id", new EqualFilter("m", "id", FilterType.FOREIGN_FIELD, aliasToJoin.get("m"), null));
    filters.put("municipality.name", new LikeFilter("m", "munici_desc", FilterType.FOREIGN_FIELD, aliasToJoin.get("m"), null));
    filters.put("municipality.regionId", new EqualFilter("m", "id_munici_direction", FilterType.FOREIGN_FIELD, aliasToJoin.get("m"), null));

    // REGION DIRECTIONS
    registerAlias("r", "LEFT JOIN region_directions r ON m.id_munici_direction = r.id", "m");
    filters.put("region.id", new EqualFilter("r", "id", FilterType.FOREIGN_FIELD, aliasToJoin.get("r"), "m"));
    filters.put("region.name", new LikeFilter("r", "region_direction_desc", FilterType.FOREIGN_FIELD, aliasToJoin.get("r"), "m"));

    // BEACH DETAILS
    registerAlias("bd", "LEFT JOIN beach_details bd ON poi.id = bd.poi_id", null);
    filters.put("details.isSandy", new EqualFilter("bd", "is_sandy", FilterType.FOREIGN_FIELD, aliasToJoin.get("bd"), null));
    filters.put("details.hasShower", new EqualFilter("bd", "has_shower", FilterType.FOREIGN_FIELD, aliasToJoin.get("bd"), null));
    filters.put("details.hasLifeguard", new EqualFilter("bd", "has_lifeguard", FilterType.FOREIGN_FIELD, aliasToJoin.get("bd"), null));

    // ACCESS TYPES
    registerAlias("at", "LEFT JOIN access_types at ON poi.access_type_id = at.id", null);
    filters.put("accessType.id", new EqualFilter("at", "id", FilterType.FOREIGN_FIELD, aliasToJoin.get("at"), null));

    // VISIT DURATION TYPES
    registerAlias("vdt", "LEFT JOIN visit_duration_types vdt ON poi.visit_duration_id = vdt.id", null);
    filters.put("visitDuration.id", new EqualFilter("vdt", "id", FilterType.FOREIGN_FIELD, aliasToJoin.get("vdt"), null));

    // POINT TYPES
    registerAlias("pt", "LEFT JOIN point_types pt ON poi.point_type_id = pt.id", null);
    filters.put("pointType.id", new EqualFilter("pt", "id", FilterType.FOREIGN_FIELD, aliasToJoin.get("pt"), null));

    // BEST TIME
    registerAlias("bt", "LEFT JOIN best_time_to_visit_types bt ON poi.best_time_id = bt.id", null);
    filters.put("bestTime.id", new EqualFilter("bt", "id", FilterType.FOREIGN_FIELD, aliasToJoin.get("bt"), null));

    // PERMITS
    registerAlias("perm", "LEFT JOIN permits perm ON poi.permit_id = perm.id", null);
    filters.put("permit.id", new EqualFilter("perm", "id", FilterType.FOREIGN_FIELD, aliasToJoin.get("perm"), null));
  }

  private void registerAlias(String alias, String joinClause, String dependsOn) {
    aliasToJoin.put(alias, joinClause);
    aliasToDependency.put(alias, dependsOn);
  }

  public static FilterRegistry getInstance() { return INSTANCE; }
  public FilterDefinition getFilter(String key) { return filters.get(key); }
  public boolean isAllowed(String key) { return filters.containsKey(key); }
  public String getJoinClauseForAlias(String alias) { return aliasToJoin.get(alias); }
  public String getDependsOnForAlias(String alias) { return aliasToDependency.get(alias); }
}
