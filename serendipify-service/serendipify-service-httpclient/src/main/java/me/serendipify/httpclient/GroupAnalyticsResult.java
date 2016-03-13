package me.serendipify.httpclient;

public class GroupAnalyticsResult extends AbstractErrorAwareResult {

  private String getGroupAnalytics;
  private Integer userCount;

  public String getGetGroupAnalytics() {
    return getGroupAnalytics;
  }

  public Integer getUserCount() {
    return userCount;
  }
}
