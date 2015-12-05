
### Serendipify.Me  -  Group Discover API for Mobile (Android) Integration

#### Introduction

Serendipify.Me is a simple service developed to facilitate private interests matching is transient groups.

YOU WANT THIS for your mobile application if you intend to build a stronger user community, and to enable your users to discover common interests and initiate partnerships and friendships from within your application.

WE make available an API (detailed below) that you can use to develop your own mobile application's modulde (pages) that implements the for group search, matching, and people discovery. Serendipify.Me is effectively the *Match.com* for your user base.

#### Details and Implementation Example

The Serendipify.Me API (configured to work with the "test" instance) consists of:
- group management (performed by the group initiator - identified by the "testSession" id
  - create new group: 
*http://test.serendipify.me/api/group?createNewGroup=&groupName=xxx&userGroupSession=testSession*
  - add user to group
*http://test.serendipify.me/api/group?addUser=&groupName=xxx&userGroupSession=testSession&userName=testUser&userContact=email@example.com*
- user matching functionality 
  - read my current matching interests:
*http://test.serendipify.me/api/user?getPrefsForGroup=&groupName=xxx&userGroupSession=testSession*
  - set new preferences
*http://localhost:8888/api/user?setPrefsForGroup=&groupName=xxx&userGroupSession=63e8afbb-6357-4e02-9340-a70b6b332a0c&prefList=A,B*
  - get the matching users
*http://test.serendipify.me/api/user?getMatchesForGroup=&groupName=xxx&userGroupSession=testSession*
