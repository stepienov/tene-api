package com.tenedevia.api.filter;

public class EqualFilter extends FilterDefinition {

  public EqualFilter(String tableAlias, String column, FilterType type, String requiredJoin, String dependsOnAlias) {
    super(tableAlias, column, type, requiredJoin, dependsOnAlias);
  }

  @Override
  public String buildCondition(String paramName) {
    return tableAlias + "." + column + " = :" + paramName;
  }
}
