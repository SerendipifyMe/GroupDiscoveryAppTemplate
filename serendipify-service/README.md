
## Serendipify REST API Client in Java

### Introduction

In order to ease adoption of the SerendipifyMe API, a simple Java client was created.
The client consists of two modules, one is the API and one is the basic implementation based on the Apache Http Client library.

In order to use the client, you will need both modules as your dependencies.

### Usage

The service follows a builder pattern for Group and User as well as for the returns of the service methods. Basically a new group is always returned with new information populated.

Below is an example of a usage of the service (for more examples see SerendipifyMediatorHttpClientTest.java which tests the API)

```java
import me.serendipify.Group;
import me.serendipify.SerendipifyException;
import me.serendipify.SerendipifyMediator;
import me.serendipify.User;

public class MyClass {

  private SerendipifyMediator service;
  private User admin;

  public void initialize() {
    // Create the service
    this.service = new SerendipifyMediatorHttpClientImpl("http://serendipify.me/api/");
    this.admin = User.Builder.getInstance().email("admin@connect.me").build();
  }

  public void sampleMethod() {
    // Use the service to create a group named ConnectDotMe and assign an admin to it
    Group group = service.createGroup("ConnectDotMe", admin);
    // Now this group has a group session: group.getSession()

    // Lets add another user to the group
    User johnDoe = User.Builder.getInstance().email("JohnDoe@gmail.com").build();
    Group jdGroup = service.addUser(group, johnDoe);

    // Set the preferences for the user in this group
    Set<String> newPreferences = new HashSet<>();
    newPreferences.add("fishing");
    jdGroup = service.savePreferences(jdGroup,newPreferences);

    // To retrieve preferences set for a groupName
    // This is probably happening sometimes much later after shutting down and reopening the App
    jdGroup = service.retrievePreferences(jdGroup);
    // jdGroup.getPreferences() now has the preferences

    // Retrieve matching users, hopefully there are others who set the same preferences
    jdGroup = service.retrieveMatchingUsers(jdGroup);
    // jdGroup.getMatchingUsers() now has the users by match
  }
```
}
