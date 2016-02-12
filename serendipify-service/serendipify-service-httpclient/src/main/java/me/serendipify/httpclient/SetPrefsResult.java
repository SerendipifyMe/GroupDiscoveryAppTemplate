package me.serendipify.httpclient;

public class SetPrefsResult extends AbstractErrorAwareResult {

  private String setPrefsForGroup;
  private String prefs;

  public boolean isError() {
    return isError(setPrefsForGroup);
  }

  public String getSetPrefsForGroup() {
    return setPrefsForGroup;
  }

  public String getPrefs() {
    return prefs;
  }
}
