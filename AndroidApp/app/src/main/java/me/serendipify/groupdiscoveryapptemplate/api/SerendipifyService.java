package me.serendipify.groupdiscoveryapptemplate.api;

import java.util.List;

/**
 * Service to handle all actions for the API
 */
public interface SerendipifyService {

    /**
     * Create a new group
     * @param group group to create
     * @param email email address of the user creating the group
     * @return new Session ID to be used to manage the group
     * @throws SerendipifyException if group creation fails
     */
    String createGroup(String group, String email) throws SerendipifyException;
    /*
    { "createNewGroup":"OK", "groupName":"http://api-dot-serendipify-me.appspot.com/api/group?getGroupAnalytics=&groupName=GROUPNAME&groupSession=c73a5fd7-bb79-456d-ad5e-0610dae25549"}
    { "createNewGroup":"FAILED", "errorMessage":"Group name already in use"}
     */

    /**
     * Read my current matching interests
     * @param group group name
     * @param session session ID for the group and user
     * @return the user's current listed interests
     * @throws SerendipifyException if operation fails. Exception will have the appropriate message to display.
     */
    List<String> readInterests(String group, String session) throws SerendipifyException;

    /**
     * Set new preferences / interests
     * @param group group to set preferences for
     * @param session session for group and user
     * @param preferences new preferences
     * @throws SerendipifyException if operation fails. Exception will have the appropriate message to display.
     */
    void setPreferences(String group, String session, List<String> preferences) throws SerendipifyException;

}
