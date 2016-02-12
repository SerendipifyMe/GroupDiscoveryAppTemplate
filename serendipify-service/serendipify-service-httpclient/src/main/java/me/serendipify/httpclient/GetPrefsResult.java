package me.serendipify.httpclient;

import java.util.Set;

public class GetPrefsResult extends AbstractErrorAwareResult {

  private String getPrefsForGroup;
  private Set<String> prefs;

  public boolean isError() {
    return isError(getPrefsForGroup);
  }

  public String getGetPrefsForGroup() {
    return getPrefsForGroup;
  }

  public Set<String> getPrefs() {
    return prefs;
  }
}
