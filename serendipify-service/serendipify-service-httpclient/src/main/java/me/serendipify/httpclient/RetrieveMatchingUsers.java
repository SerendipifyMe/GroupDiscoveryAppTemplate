package me.serendipify.httpclient;

import java.util.ArrayList;
import java.util.Map;

public class RetrieveMatchingUsers extends AbstractErrorAwareResult {

  private String getMatchesForGroup;
  private Map<String, ArrayList<String>> matches;

  public boolean isError() {
    return super.isError(getMatchesForGroup);
  }

  public String getGetMatchesForGroup() {
    return getMatchesForGroup;
  }

  public Map<String, ArrayList<String>> getMatches() {
    return matches;
  }
}
