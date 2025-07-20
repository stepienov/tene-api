package com.tenedevia.api.filter;

public abstract class FilterDefinition {
  protected String tableAlias;
  protected String column;
  protected FilterType type;
  protected String requiredJoin;
  protected String dependsOnAlias;

  public FilterDefinition(String tableAlias, String column, FilterType type, String requiredJoin, String dependsOnAlias) {
    this.tableAlias = tableAlias;
    this.column = column;
    this.type = type;
    this.requiredJoin = requiredJoin;
    this.dependsOnAlias = dependsOnAlias;
  }

  public String getRequiredJoin() { return requiredJoin; }
  public String getDependsOnAlias() { return dependsOnAlias; }
  public String getTableAlias() { return tableAlias; }
  public String getColumn() { return column; }
  public FilterType getType() { return type; }

  public abstract String buildCondition(String paramName);
}
