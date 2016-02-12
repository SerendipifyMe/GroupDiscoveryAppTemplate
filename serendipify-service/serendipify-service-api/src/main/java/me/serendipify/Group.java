package me.serendipify;

import java.util.HashSet;
import java.util.Set;

public class Group {

  private final String name;
  private final String session;
  private final Boolean owned;
  private final Set<String> preferences;
  private final Set<User> matchingUsers;

  private Group(String name, String session, Boolean owned, Set<String> preferences, Set<User> matchingUsers) {
    this.name = name;
    this.session = session;
    this.owned = owned;
    this.preferences = preferences;
    this.matchingUsers = matchingUsers;
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

  // TODO: not sure what gets returned
  public void getAnalytics() {

  }

  /**
   * User preferences for the current group
   * @return
   */
  public Set<String> getPreferences() {
    return preferences;
  }

  /**
   * Matching users
   * @return
   */
  public Set<User> getMatchingUsers() {
    return matchingUsers;
  }

  public static class Builder {

    private String name;
    private String session;
    private Boolean owned;
    private Set<String> preferences;
    private Set<User> matchingUsers;

    public static final Builder getInstance() {
      return new Builder();
    }

    public Builder fromGroup(Group group) {
      this.name = group.name;
      this.session = group.session;
      this.owned = group.owned;
      this.preferences = group.preferences;
      this.matchingUsers = group.matchingUsers;
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

    public Builder matchingUsers(Set<User> matchingUsers) {
      this.matchingUsers = matchingUsers;
      return this;
    }

    public Builder addMatchingUser(User user) {
      if (this.matchingUsers == null) {
        this.matchingUsers = new HashSet<>();
      }
      this.matchingUsers.add(user);
      return this;
    }

    public Group build() {
      return new Group(name, session, owned, preferences, matchingUsers);
    }
  }
}
