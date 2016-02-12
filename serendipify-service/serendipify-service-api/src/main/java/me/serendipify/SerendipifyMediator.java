package me.serendipify;

import java.util.Set;

/**
 * Service to handle all actions for the API
 */
public interface SerendipifyMediator {

  /**
   * Create a new group
   *
   * @param group group to create
   * @param user user creating the group
   * @return newly created group
   * @throws SerendipifyException if group creation fails
   */
  Group createGroup(String group, User user) throws SerendipifyException;
  /*
   * { "createNewGroup":"OK", "groupName":
   * "http://api-dot-serendipify-me.appspot.com/api/group?getGroupAnalytics=&groupName=GROUPNAME&groupSession=c73a5fd7-bb79-456d-ad5e-0610dae25549"}
   * { "createNewGroup":"FAILED", "errorMessage":"Group name already in use"}
   */

  /**
   * Add user to a particular group
   *
   * @param group group to add the user to
   * @param newUser user to add to the group
   * @return group with the user session
   * @throws SerendipifyException if adding the user to the group has failed
   */
  Group addUser(Group group, User newUser) throws SerendipifyException;

  /**
   * Retrieve group analytics such as user count etc.
   *
   * @param group group to get analytics for
   * @return group with populated analytics ?
   * @throws SerendipifyException when analytics fail to be retrieved
   */
  //TODO: Identify what actually is returned and change the return type
  Group retrieveGroupAnalytics(Group group) throws SerendipifyException;

  /**
   * Read my current preferences
   *
   * @param group group
   * @return Group with populated preferences
   * @throws SerendipifyException if operation fails. Exception will have the appropriate message to display.
   */
  Group retrievePreferences(Group group) throws SerendipifyException;

  /**
   * Set new preferences / interests
   *
   * @param group group to set preferences for
   * @param preferences new preferences
   * @return group with the updated preferences
   * @throws SerendipifyException if operation fails. Exception will have the appropriate message to display.
   */
  Group savePreferences(Group group, Set<String> preferences) throws SerendipifyException;

  /**
   * Retrieve matching users
   *
   * @param group group
   * @return group with matching interests
   * @throws SerendipifyException when retrieve fails
   */
  Group retrieveMatchingUsers(Group group) throws SerendipifyException;
}
