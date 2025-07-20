package com.tenedevia.api.filter;

public class LikeFilter extends FilterDefinition {

  public LikeFilter(String tableAlias, String column, FilterType type, String requiredJoin, String dependsOnAlias) {
    super(tableAlias, column, type, requiredJoin, dependsOnAlias);
  }

  @Override
  public String buildCondition(String paramName) {
    return "LOWER(" + tableAlias + "." + column + ") LIKE :" + paramName;
  }
}
