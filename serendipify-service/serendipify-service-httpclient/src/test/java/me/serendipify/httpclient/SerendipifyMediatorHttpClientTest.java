package me.serendipify.httpclient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.serendipify.Group;
import me.serendipify.SerendipifyException;
import me.serendipify.User;

public class SerendipifyMediatorHttpClientTest {

  private static final String GROUP_ADMIN_EMAIL = "test@test.com";
  private static final String GROUP_NEW_USER = "test2@test.com";
  private final Logger LOGGER = LoggerFactory.getLogger(SerendipifyMediatorHttpClientTest.class);
  private SerendipifyMediatorHttpClientImpl service;
  private User admin;

  @Before
  public void setUp() throws Exception {
    this.service = new SerendipifyMediatorHttpClientImpl("http://api-dot-serendipify-me.appspot.com/api/");
    this.admin = new User.Builder().email(GROUP_ADMIN_EMAIL).build();
  }

  @Test
  //@Ignore
  public void testThatCreateGroupWorks() throws Exception {
    String randomGroup = UUID.randomUUID().toString();
    Group group = service.createGroup(randomGroup, admin);
    assertNotNull(group);
    LOGGER.info("New session id is {}", group.getSession());
    Thread.sleep(1000);
    // fails the second time as the group is already created
    try {
      service.createGroup(randomGroup, admin);
      fail("An exception should have been thrown");
    } catch (SerendipifyException e) {
      // expected
      LOGGER.info("Exepected failure: {}", e.getMessage());
    }
  }

  @Test
  //@Ignore
  public void testThatAddingAUserWorks() throws Exception {
    String randomGroup = UUID.randomUUID().toString();
    Group group = service.createGroup(randomGroup, admin);
    User newUser = new User.Builder().email(GROUP_NEW_USER).build();
    service.addUser(group, newUser);
    service.addUser(group, newUser);
  }

  @Test
  @Ignore("The returned JSON is not valid, this test fails")
  public void testThatRetrieveAnalyticsWorks() throws Exception {
    String randomGroup = UUID.randomUUID().toString();
    Group group = service.createGroup(randomGroup, admin);
    User newUser = new User.Builder().email(GROUP_NEW_USER).build();
    service.addUser(group, newUser);
    service.retrieveGroupAnalytics(group);
  }

  @Test
  @Ignore("Isn't the group creator cannot set preferences.")
  public void testThatRetrievePreferencesWorks() throws Exception {
    String randomGroup = UUID.randomUUID().toString();
    Group group = service.createGroup(randomGroup, admin);
    service.retrievePreferences(group);
  }

  @Test
  public void testThatPreferencesWorks() throws Exception {
    // create group
    String randomGroup = UUID.randomUUID().toString();
    Group group = service.createGroup(randomGroup, admin);
    // add user to it
    User newUser = new User.Builder().email(GROUP_NEW_USER).build();
    Group userGroup = service.addUser(group, newUser);
    // set preferences
    Set<String> newPreferences = new HashSet<>();
    newPreferences.add("serendipify");
    userGroup = service.savePreferences(userGroup,newPreferences);
    // get preferences
    Group preferences = service.retrievePreferences(userGroup);
    assertEquals("serendipify", preferences.getPreferences().iterator().next());
  }

  @Test
  public void testThatUserWorkflowWorks() throws Exception {
    // create group
    String randomGroup = UUID.randomUUID().toString();
    Group group = service.createGroup(randomGroup, admin);
    // add user to it
    User newUser = new User.Builder().email(GROUP_NEW_USER).build();
    Group userGroup = service.addUser(group, newUser);
    // set preferences
    Set<String> newPreferences = new HashSet<>();
    newPreferences.add("serendipify");
    userGroup = service.savePreferences(userGroup,newPreferences);
    // get preferences
    Group preferences = service.retrievePreferences(userGroup);
    assertEquals("serendipify", preferences.getPreferences().iterator().next());
    // get matching users
    Group groupWithMatches = service.retrieveMatchingUsers(userGroup);
    assertEquals(0, groupWithMatches.getMatchingUsers().size());
  }

}
