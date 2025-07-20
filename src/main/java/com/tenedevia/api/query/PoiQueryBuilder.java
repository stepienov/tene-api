package com.tenedevia.api.query;

import com.tenedevia.api.filter.FilterDefinition;
import com.tenedevia.api.filter.FilterRegistry;
import com.tenedevia.api.filter.LikeFilter;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class PoiQueryBuilder {

  public String buildSelectQuery(Map<String, Object> filters, int page, int size, String sort) {
    StringBuilder sql = new StringBuilder("SELECT poi.* FROM points_of_interest poi");
    Set<String> requiredAliases = new HashSet<>();
    StringBuilder where = new StringBuilder(" WHERE 1=1");

    // Budowanie WHERE i zbieranie aliasów
    for (Map.Entry<String, Object> entry : filters.entrySet()) {
      String key = entry.getKey();
      Object value = entry.getValue();
      if (value == null) continue;

      FilterDefinition def = FilterRegistry.getInstance().getFilter(key);
      if (def == null) throw new IllegalArgumentException("Unsupported filter key: " + key);
      if (def.getRequiredJoin() != null) requiredAliases.add(def.getTableAlias());

      String condition;
      String paramName = key.replace(".", "_");
      if (def instanceof LikeFilter) {
        condition = def.buildCondition(paramName)
            .replace(":" + paramName, "'%" + value.toString().toLowerCase() + "%'");
      } else if (value instanceof String) {
        condition = def.buildCondition(paramName)
            .replace(":" + paramName, "'" + value + "'");
      } else {
        condition = def.buildCondition(paramName)
            .replace(":" + paramName, value.toString());
      }
      where.append(" AND ").append(condition);
    }

    List<String> sortedAliases = topologicalSort(requiredAliases);

    for (String alias : sortedAliases) {
      sql.append(" ").append(FilterRegistry.getInstance().getJoinClauseForAlias(alias));
    }

    sql.append(where);

    if (sort != null && !sort.isEmpty()) {
      sql.append(" ORDER BY ").append(sort);
    }

    sql.append(" LIMIT ").append(size).append(" OFFSET ").append(page * size);

    System.out.println("Executing SQL (select): " + sql);
    return sql.toString();
  }

  public String buildCountQuery(Map<String, Object> filters) {
    StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM points_of_interest poi");
    Set<String> requiredAliases = new HashSet<>();
    StringBuilder where = new StringBuilder(" WHERE 1=1");

    for (Map.Entry<String, Object> entry : filters.entrySet()) {
      String key = entry.getKey();
      Object value = entry.getValue();
      if (value == null) continue;

      FilterDefinition def = FilterRegistry.getInstance().getFilter(key);
      if (def == null) throw new IllegalArgumentException("Unsupported filter key: " + key);
      if (def.getRequiredJoin() != null) requiredAliases.add(def.getTableAlias());

      String condition;
      String paramName = key.replace(".", "_");
      if (def instanceof LikeFilter) {
        condition = def.buildCondition(paramName)
            .replace(":" + paramName, "'%" + value.toString().toLowerCase() + "%'");
      } else if (value instanceof String) {
        condition = def.buildCondition(paramName)
            .replace(":" + paramName, "'" + value + "'");
      } else {
        condition = def.buildCondition(paramName)
            .replace(":" + paramName, value.toString());
      }
      where.append(" AND ").append(condition);
    }

    List<String> sortedAliases = topologicalSort(requiredAliases);

    for (String alias : sortedAliases) {
      sql.append(" ").append(FilterRegistry.getInstance().getJoinClauseForAlias(alias));
    }

    sql.append(where);

    System.out.println("Executing SQL (count): " + sql);
    return sql.toString();
  }

  private List<String> topologicalSort(Set<String> aliases) {
    List<String> sorted = new ArrayList<>();
    Set<String> visited = new HashSet<>();
    for (String alias : aliases) {
      visit(alias, sorted, visited);
    }
    return sorted;
  }

  private void visit(String alias, List<String> sorted, Set<String> visited) {
    if (visited.contains(alias)) return;
    visited.add(alias);

    String dependsOn = FilterRegistry.getInstance().getDependsOnForAlias(alias);
    if (dependsOn != null) {
      visit(dependsOn, sorted, visited);
    }
    if (!sorted.contains(alias)) {
      sorted.add(alias);
    }
  }
}
