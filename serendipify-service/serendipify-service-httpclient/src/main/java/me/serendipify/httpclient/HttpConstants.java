package me.serendipify.httpclient;

public final class HttpConstants {

  private HttpConstants() {}

  public static final String EQUAL = "=";
  public static final String GROUP_ACTION = "group";
  public static final String GROUP_ACTION_NEW = "createNewGroup" + EQUAL;
  public static final String GROUP_ACTION_ADD_USER = "addUser" + EQUAL;
  public static final String GROUP_ANALYTICS = "getGroupAnalytics";

  public static final String GROUP_NAME = "groupName";
  public static final String GROUP_OWNER_EMAIL = "groupOwnerEmail";
  public static final String GROUP_SESSION = "groupSession";
  public static final String USER_CONTACT = "userContact";
  public static final String AMPERSAND = "&";
  public static final String QUESTION = "?";

  public static final String USER_ACTION = "user";
  public static final String USER_PREFS_GET = "getPrefsForGroup" + EQUAL;
  public static final String USER_PREFS_SET = "setPrefsForGroup" + EQUAL;
  public static final String USER_PREFS_LIST = "prefList" + EQUAL;
  public static final String USER_MATCHES = "getMatchesForGroup" + EQUAL;
  public static final String USER_SESSION = "userSession";
}
