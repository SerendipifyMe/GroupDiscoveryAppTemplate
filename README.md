
###Serendipify.Me Group Discover API for Mobile (Android) Integration

Use this to develop your own application's modulde that implements the Serendipify.Me API for group search, match, and discovery

Serendipify.Me provides you with a very basic but revelant functionality: for any set or subset of users of your application, 
you can facilitate for them to discover interesting partners through shared interests. 
Serendipify.Me is effectively the Match.com for groups of people.

The Serendipify.Me API (configured to work with the "test" instance) consists of:
- group management (performed by the group initiator - identified as wit the "testSession" id
  - create new group: 
*http://test.serendipify.me/api/group?createNewGroup=&groupName=xxx&userGroupSession=testSession*
  - add user to group
*http://test.serendipify.me/api/group?addUser=&groupName=xxx&userGroupSession=testSession&userName=testUser&userContact=email@example.com*
- user matching functionality 
  - read my current matching interests:
*http://test.serendipify.me/api/user?getPrefsForGroup=&groupName=xxx&userGroupSession=testSession*
  - set new preferences
*http://test.serendipify.me/api/user?setPrefsForGroup=&groupName=xxx&userGroupSession=testSession&&prefList=TESTPREF*
  - get the matching users
*http://test.serendipify.me/api/user?getMatchesForGroup=&groupName=xxx&userGroupSession=testSession*
