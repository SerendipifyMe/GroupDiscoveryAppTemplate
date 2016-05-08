package me.serendipify.httpclient;

import static me.serendipify.httpclient.HttpConstants.AMPERSAND;
import static me.serendipify.httpclient.HttpConstants.EQUAL;
import static me.serendipify.httpclient.HttpConstants.GROUP_ACTION;
import static me.serendipify.httpclient.HttpConstants.GROUP_ACTION_ADD_USER;
import static me.serendipify.httpclient.HttpConstants.GROUP_ACTION_NEW;
import static me.serendipify.httpclient.HttpConstants.GROUP_ANALYTICS;
import static me.serendipify.httpclient.HttpConstants.GROUP_NAME;
import static me.serendipify.httpclient.HttpConstants.GROUP_OWNER_EMAIL;
import static me.serendipify.httpclient.HttpConstants.GROUP_SESSION;
import static me.serendipify.httpclient.HttpConstants.QUESTION;
import static me.serendipify.httpclient.HttpConstants.USER_ACTION;
import static me.serendipify.httpclient.HttpConstants.USER_CONTACT;
import static me.serendipify.httpclient.HttpConstants.USER_MATCHES;
import static me.serendipify.httpclient.HttpConstants.USER_PREFS_GET;
import static me.serendipify.httpclient.HttpConstants.USER_PREFS_LIST;
import static me.serendipify.httpclient.HttpConstants.USER_PREFS_SET;
import static me.serendipify.httpclient.HttpConstants.USER_SESSION;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import me.serendipify.Group;
import me.serendipify.SerendipifyException;
import me.serendipify.SerendipifyMediator;
import me.serendipify.User;

public class SerendipifyMediatorHttpClientImpl implements SerendipifyMediator {

  private final Logger LOGGER = LoggerFactory.getLogger(SerendipifyMediatorHttpClientImpl.class);

  private final String baseURL;

  public SerendipifyMediatorHttpClientImpl(String baseURL) {
    this.baseURL = baseURL;
  }

  @Override
  public Group createGroup(String group, User user) throws SerendipifyException {
    try {
      String request = baseURL + GROUP_ACTION + QUESTION + GROUP_ACTION_NEW + AMPERSAND + GROUP_NAME + EQUAL + group + AMPERSAND +
          GROUP_OWNER_EMAIL + EQUAL + user.getEmail();
      Gson gson = new GsonBuilder().create();
      CreateGroupResult result = gson.fromJson(executeGET(request), CreateGroupResult.class);
      if (result.isError()) {
        throw new SerendipifyException(result.getErrorMessage());
      }
      return Group.Builder.getInstance().name(group).owned(Boolean.TRUE).session(result.getSessionID()).build();
    }
    catch (IOException e) {
      String error = String.format("Failed to create the new group %s", group);
      throw new SerendipifyException(error, e);
    }
  }

  /*
   * Adding user returns space after "OK " in the response:
   * { "addUser":"OK ", "userName":
   * "http://api-dot-serendipify-me.appspot.com/api/user?getPrefsForGroup=&groupName=794490ed-a143-4bda-a33d-3620f1297715&groupSession=e61daa8e
   * -ea71-41a3-b9ed-398e02294cb8"}
   */
  @Override
  public Group addUser(Group group, User newUser) throws SerendipifyException {
    try {
      String request = baseURL + GROUP_ACTION + QUESTION + GROUP_ACTION_ADD_USER + AMPERSAND + GROUP_NAME + EQUAL + group.getName() +
          AMPERSAND + GROUP_SESSION + EQUAL + group.getSession() + AMPERSAND + USER_CONTACT + EQUAL + newUser.getEmail();
      Gson gson = new GsonBuilder().create();
      AddUserResult result = gson.fromJson(executeGET(request), AddUserResult.class);
      if (result.isError()) {
        throw new SerendipifyException("Failed to add user");
      }
      return Group.Builder.getInstance().fromGroup(group).session(result.getUserSession()).build();
    }
    catch (IOException e) {
      String error = String.format("Failed to add user %s to group %s", newUser.getEmail(), group);
      throw new SerendipifyException(error, e);
    }
  }

  @Override
  public Group retrieveGroupAnalytics(Group group) throws SerendipifyException {
    String request = baseURL + GROUP_ACTION + QUESTION + GROUP_ACTION + AMPERSAND + GROUP_ANALYTICS + EQUAL + AMPERSAND +
        GROUP_NAME + EQUAL + group.getName() +
        AMPERSAND + GROUP_SESSION + EQUAL + group.getSession();
    try {
      String response = executeGET(request);
      Gson gson = new GsonBuilder().create();
      GroupAnalyticsResult groupAnalyticsResult = gson.fromJson(response, GroupAnalyticsResult.class);
      if (groupAnalyticsResult.isError(groupAnalyticsResult.getGetGroupAnalytics())) {
        throw new SerendipifyException("Failed to retrieve analytics. Error was " + groupAnalyticsResult.getGetGroupAnalytics());
      }
      return Group.Builder.getInstance().fromGroup(group).userCount(groupAnalyticsResult.getUserCount()).build();
    }
    catch (Exception e) {
      throw new SerendipifyException(e.getMessage());
    }
  }

  @Override
  public Group retrievePreferences(Group group) throws SerendipifyException {
    String request = baseURL + USER_ACTION + QUESTION + USER_PREFS_GET + AMPERSAND +
        GROUP_NAME + EQUAL + group.getName() +
        AMPERSAND + USER_SESSION + EQUAL + group.getSession();
    try {
      String response = executeGET(request);
      Gson gson = new GsonBuilder().create();
      GetPrefsResult prefsResult = gson.fromJson(response, GetPrefsResult.class);
      LOGGER.info(prefsResult.toString());
      if (prefsResult.isError()) {
        throw new SerendipifyException("Failed to retrieve preferences. Error was " + prefsResult.getGetPrefsForGroup());
      }
      return Group.Builder.getInstance().fromGroup(group).preferences(prefsResult.getPrefs()).build();
    }
    catch (Exception e) {
      throw new SerendipifyException(e.getMessage());
    }
  }

  @Override
  public Group savePreferences(Group group, Set<String> preferences) throws SerendipifyException {
    String request = baseURL + USER_ACTION + QUESTION + USER_PREFS_SET + AMPERSAND +
        GROUP_NAME + EQUAL + group.getName() +
        AMPERSAND + USER_SESSION + EQUAL + group.getSession() +
        AMPERSAND + USER_PREFS_LIST + convertSetToString(preferences);
    try {
      String response = executeGET(request);
      Gson gson = new GsonBuilder().create();
      SetPrefsResult setPrefsResult = gson.fromJson(response, SetPrefsResult.class);
      LOGGER.info(setPrefsResult.getSetPrefsForGroup());
      if (!setPrefsResult.isError()) {
        LOGGER.info(setPrefsResult.getPrefs());
      }
    }
    catch (Exception e) {
      throw new SerendipifyException(e.getMessage(), e);
    }
    return group;
  }

  @Override
  /*
  Breaks because returns invalid JSON: { "getMatchesForGroup":"OK",  "matches":"{"serendipify":[]}"}
  ERROR: Unterminated object near OK",  "matches":"{"serendipify":[]}"}
   */
  public Group retrieveMatchingUsers(Group group) throws SerendipifyException {
    String request = baseURL + USER_ACTION + QUESTION + USER_MATCHES + AMPERSAND +
        GROUP_NAME + EQUAL + group.getName() +
        AMPERSAND + USER_SESSION + EQUAL + group.getSession();
    try {
      String response = executeGET(request);
      Gson gson = new GsonBuilder().create();
      RetrieveMatchingUsers matchingUsers = gson.fromJson(response, RetrieveMatchingUsers.class);
      LOGGER.info(matchingUsers.getGetMatchesForGroup());
      if (!matchingUsers.isError()) {
        LOGGER.info(matchingUsers.getMatches().toString());
      }
      Group.Builder builder = Group.Builder.getInstance().fromGroup(group);
      for (Map.Entry<String, ArrayList<String>> preference : matchingUsers.getMatches().entrySet()) {
        for (String userEmail : preference.getValue()) {
          builder.addMatchingUser(preference.getKey(), User.Builder.getInstance().email(userEmail).build());
        }
      }
      return builder.build();
    }
    catch (Exception e) {
      throw new SerendipifyException(e.getMessage(), e);
    }
  }

  private String executeGET(String request) throws IOException {
    LOGGER.info("Executing: {}", request);
    Content returnContent = Request.Get(request).execute().returnContent();
    String returnedJSON = returnContent.asString();
    LOGGER.info(returnedJSON);
    return returnedJSON;
  }

  private String convertSetToString(Set<String> set) {
    StringBuilder str = new StringBuilder();
    set.stream().forEach(s -> str.append(s).append(","));
    return str.substring(0, str.length() - 1);
  }
}
