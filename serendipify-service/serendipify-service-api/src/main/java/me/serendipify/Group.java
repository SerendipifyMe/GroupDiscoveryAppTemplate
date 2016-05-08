package me.serendipify;

import java.util.*;

public class Group {

  private final String name;
  private final String session;
  private final Boolean owned;
  private final Set<String> preferences;
  private final Map<String, Set<User>> matchingUsers;
  private final Integer userCount;

  private Group(String name, String session, Boolean owned, Set<String> preferences, Map<String, Set<User>> matchingUsers,
                Integer userCount) {
    this.name = name;
    this.session = session;
    this.owned = owned;
    this.preferences = preferences;
    this.matchingUsers = matchingUsers;
    this.userCount = userCount;
  }

  /**
   * Group Name
   * 
   * @return
   */
  public String getName() {
    return name;
  }

  /**
   * Session of this group
   * 
   * @return
   */
  public String getSession() {
    return session;
  }

  /**
   * True if the Group is owned, False if user is a participant
   * 
   * @return
   */
  public Boolean isOwned() {
    return owned;
  }


  /**
   * Number of users in the group
   *
   * @return number of users in the group
   */
  public Integer getUserCount() {
    return userCount;
  }

  /**
   * User preferences for the current group
   * @return
   */
  public Set<String> getPreferences() {
    return preferences;
  }

  /**
   * Matching users by preference
   * @return
   */
  public Map<String, Set<User>> getMatchingUsers() {
    return matchingUsers;
  }

  public static class Builder {

    private String name;
    private String session;
    private Boolean owned;
    private Set<String> preferences;
    // A set of preference and users matched
    private Map<String, Set<User>> matchingUsers;
    private Integer userCount;

    public static final Builder getInstance() {
      return new Builder();
    }

    private Builder() {
      // private, use contstructor instead
    }

    public Builder fromGroup(Group group) {
      this.name = group.name;
      this.session = group.session;
      this.owned = group.owned;
      this.preferences = group.preferences;
      this.matchingUsers = group.matchingUsers;
      this.userCount = group.userCount;
      return this;
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder session(String session) {
      this.session = session;
      return this;
    }

    public Builder owned(Boolean owned) {
      this.owned = owned;
      return this;
    }

    public Builder preferences(Set<String> preferences) {
      this.preferences = preferences;
      return this;
    }

    public Builder addPreference(String preference) {
      if (this.preferences == null) {
        this.preferences = new HashSet<>();
      }
      this.preferences.add(preference);
      return this;
    }

    public Builder matchingUsers(Map<String, Set<User>> matchingUsers) {
      this.matchingUsers = matchingUsers;
      return this;
    }

    public Builder addMatchingUser(String preference, User user) {
      if (this.matchingUsers == null) {
        this.matchingUsers = new HashMap<>();
      }
      if (this.matchingUsers.containsKey(preference)) {
        this.matchingUsers.get(preference).add(user);
      } else {
        Set<User> users = new HashSet<>();
        users.add(user);
        this.matchingUsers.put(preference, users);
      }
      return this;
    }

    public Builder userCount(Integer userCount) {
      this.userCount = userCount;
      return this;
    }

    public Group build() {
      if (matchingUsers == null) {
        matchingUsers = new HashMap<>();
      }
      return new Group(name, session, owned, preferences, matchingUsers, userCount);
    }
  }
}
