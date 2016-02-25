
### Serendipify.Me  -  Group Match API for Mobile (Android) apps

#### Introduction

[Serendipify.Me](http://serendipify.me) is a simple service developed to facilitate private interests matching is transient groups.

**You want this for your mobile application** if you intend to build a stronger user community, and to enable your users to **discover common interests and initiate partnerships and friendships from within your application**.

WE make available an API (detailed below) that implements the for group search, matching, and people discovery. Serendipify.Me is effectively the *Match.com* for your user base.

In this repository, we also provide you with a skeleton "Template App" to show you how to use the API. Presumably you identify relevant code, add and customize it for your app, and you will be ready to give your users this matching functionality. 

#### Details and Implementation Example

![SerendipifyMe API Diagram](https://github.com/tibisp/GroupDiscoveryAppTemplate/raw/master/img/SerendipifyMe-API-Diagram.png)

The Serendipify.Me API consists of:
- group management functionality (performed by the group initiator)
 - create new group 
   - (OWNEREMAIL receives further directions, call **returns the new groups' accessKey OWNERSESSION**)
    - REQ:http://serendipify.me/api/group?createNewGroup=&groupName=GROUPNAME&groupOwnerEmail=OWNEREMAIL
    - RESP (gives a link to the group analytics information, on success): { "createNewGroup":"OK",  "groupName":"http://serendipify.me/api/group?getGroupAnalytics=&groupName=GROUPNAME&groupSession=OWNERSESSION"}
 - add user to group 
   - (USEREMAIL receives successful connection requests from other users, call RETURNS USERSESSION)
    - REQ: http://serendipify.me/api/group?addUser=&groupName=GROUPNAME&groupSession=OWNERSESSION&userContact=USEREMAIL
    - RESP: returns the access key USERSESSION: { "addUser":"OK", "userName":"http://serendipify.me/api/user?getPrefsForGroup=&groupName=GROUPNAME&groupSession=USERSESSION}
 - assess group activity 
   - (call RETURNS group information: user count, etc)
    - http://serendipify.me/api/group?getGroupAnalytics=&groupName=GROUPNAME&groupSession=OWNERSESSION


- user matching functionality (for your mobine app's users)
 - read my current matching interests 
   - (call RETURNS the user's current listed interests)
    - http://serendipify.me/api/user?getPrefsForGroup=&groupName=GROUPNAME&userSession=USERSESSION
 - set new preferences 
   - (call RETURNS the same USERSESSION)
    - http://serendipify.me/api/user?setPrefsForGroup=&groupName=GROUPNAME&userSession=USERSESSION&prefList=Pref-A,Pref-B
 - get the matching users 
   - (call RETURNS, for each recorded interest, a list of  MATCHING USERs' targetContact)
    - http://serendipify.me/api/user?getMatchesForGroup=&groupName=GROUPNAME&userSession=USERSESSION

### Developer Console
To assist you with developing your mobile application against our API, we make available a development console:
 - [http://serendipify.me/api](http://serendipify.me/api)

