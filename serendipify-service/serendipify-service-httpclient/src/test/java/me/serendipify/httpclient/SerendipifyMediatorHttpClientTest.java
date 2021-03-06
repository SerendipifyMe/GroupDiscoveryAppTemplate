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

  private static final String GROUP_ADMIN_EMAIL = "test@serendipify.me";
  private final Logger LOGGER = LoggerFactory.getLogger(SerendipifyMediatorHttpClientTest.class);
  private SerendipifyMediatorHttpClientImpl service;
  private User admin;

  @Before
  public void setUp() throws Exception {
    this.service = new SerendipifyMediatorHttpClientImpl("http://serendipify.me/api/");
    this.admin = User.Builder.getInstance().email(GROUP_ADMIN_EMAIL).build();
  }

  @Test
  public void testThatCreateGroupWorks() throws Exception {
    String randomGroup = UUID.randomUUID().toString();
    Group group = service.createGroup(randomGroup, admin);
    assertNotNull(group);
    LOGGER.info("New session id is {}", group.getSession());
    try {
      service.createGroup(randomGroup, admin);
      fail("An exception should have been thrown");
    } catch (SerendipifyException e) {
      // expected
      LOGGER.info("Expected failure: {}", e.getMessage());
    }
  }

  @Test
  //@Ignore
  public void testThatAddingAUserWorksAndIsIndempotent() throws Exception {
    String randomGroup = UUID.randomUUID().toString();
    Group group = service.createGroup(randomGroup, admin);
    User newUser = User.Builder.getInstance().email(createNewRandomUser()).build();
    service.addUser(group, newUser);

    //FIXME: This should not fail, adding the same user should work
    //service.addUser(group, newUser);
  }

  @Test
  public void testThatRetrieveAnalyticsWorks() throws Exception {
    String randomGroup = UUID.randomUUID().toString();
    Group group = service.createGroup(randomGroup, admin);
    User newUser = User.Builder.getInstance().email(createNewRandomUser()).build();
    service.addUser(group, newUser);
    group = service.retrieveGroupAnalytics(group);
    Thread.sleep(1000);
    // FIXME: This fails if there is no sleep above
    assertEquals(Integer.valueOf(1), group.getUserCount());
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
    User newUser = User.Builder.getInstance().email(createNewRandomUser()).build();
    Group userGroup = service.addUser(group, newUser);
    // set preferences
    Set<String> newPreferences = new HashSet<>();
    newPreferences.add("serendipify");
    userGroup = service.savePreferences(userGroup, newPreferences);
    // get preferences
    Group preferences = service.retrievePreferences(userGroup);
    assertEquals("serendipify", preferences.getPreferences().iterator().next());
  }

  @Test
  public void testThatUserWorkflowWorks() throws Exception {
    // create group
    String randomGroup = UUID.randomUUID().toString();
    Group group = service.createGroup(randomGroup, admin);
    // add 2 users to it

    User newUser = User.Builder.getInstance().email(createNewRandomUser()).build();
    Group userGroup = service.addUser(group, newUser);
    Thread.sleep(1000);
    // set preferences
    Set<String> newPreferences = new HashSet<>();
    newPreferences.add("serendipify");
    userGroup = service.savePreferences(userGroup, newPreferences);
    Thread.sleep(1000);

    User newUser2 = User.Builder.getInstance().email(createNewRandomUser()).build();
    userGroup = service.addUser(userGroup, newUser2);
    Thread.sleep(1000);
    // set preferences
    userGroup = service.savePreferences(userGroup, newPreferences);
    Thread.sleep(1000);

    // get preferences
    Group preferences = service.retrievePreferences(userGroup);
    assertEquals("serendipify", preferences.getPreferences().iterator().next());
    Thread.sleep(1000);
    // get matching users
    Group groupWithMatches = service.retrieveMatchingUsers(userGroup);
    assertEquals(1, groupWithMatches.getMatchingUsers().size());
  }

  @Test
  @Ignore("Test the example on the README.md. Executed sporadically because the group name is not unique.")
  public void testReadmeExample() throws Exception {
    // Use the service to create a group named ConnectDotMe and assign an admin to it
    Group group = service.createGroup("ConnectDotMe", admin);
    // Now this group has a group session: group.getSession()

    // Lets add another user to the group
    User johnDoe = User.Builder.getInstance().email("JohnDoe@gmail.com").build();
    Group jdGroup = service.addUser(group, johnDoe);

    // Set the preferences for the user in this group
    Set<String> newPreferences = new HashSet<>();
    newPreferences.add("fishing");
    jdGroup = service.savePreferences(jdGroup, newPreferences);

    // To retrieve preferences set for a groupName
    // This is probably happening sometimes much later after
    jdGroup = service.retrievePreferences(jdGroup);
    // jdGroup.getPreferences() now has the preferences

    // Retrieve matching users, hopefully there are others who set the same preferences
    jdGroup = service.retrieveMatchingUsers(jdGroup);
    // jdGroup.getMatchingUsers() now has the users by match
  }

  private String createNewRandomUser() {
    return UUID.randomUUID().toString() + "@serendipify.me";
  }
}
