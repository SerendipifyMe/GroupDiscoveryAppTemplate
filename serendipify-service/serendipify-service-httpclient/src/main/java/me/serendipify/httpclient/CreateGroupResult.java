package me.serendipify.httpclient;

public class CreateGroupResult extends AbstractErrorAwareResult {

  private String createNewGroup;
  private String groupName;
  private String errorMessage;

  public boolean isError() {
    return isError(createNewGroup);
  }
  public String getSessionID() {
    int groupSessionIndex = groupName.indexOf("groupSession=");
    if (groupSessionIndex != -1) {
      return groupName.substring(groupSessionIndex + "groupSession=".length());
    }
    return null;
  }

  public String getErrorMessage() {
    return errorMessage;
  }
}
