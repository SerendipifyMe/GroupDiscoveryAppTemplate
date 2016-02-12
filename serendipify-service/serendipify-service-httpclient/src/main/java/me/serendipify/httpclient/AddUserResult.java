package me.serendipify.httpclient;

import static me.serendipify.httpclient.HttpConstants.*;

public class AddUserResult extends AbstractErrorAwareResult {

  private String addUser;
  private String userName;

  public Boolean isError() {
    return isError(addUser);
  }
  public String getAddUser() {
    return addUser;
  }

  public String getUserName() {
    return userName;
  }

  public String getUserSession() {
    return userName.substring(userName.indexOf(GROUP_SESSION + EQUAL) + GROUP_SESSION.length() + 1);
  }
}
