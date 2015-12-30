package me.serendipify.groupdiscoveryapptemplate.api;

public class CreateGroupResult {

    private String createNewGroup;
    private String groupName;
    private String errorMessage;

    public boolean isError() {
        return false == createNewGroup.equals("OK");
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
