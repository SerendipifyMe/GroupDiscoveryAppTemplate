
## Serendipify.Me  -  Group Match API for Mobile (Android) apps

### Introduction

[Serendipify.Me](http://serendipify.me) is a simple service developed to facilitate private interests matching is transient groups.

**You want this for your mobile application** if you intend to build a stronger user community, and to enable your users to **discover common interests and initiate partnerships and friendships from within your application**.

WE make available an API (detailed below) that implements the for group search, matching, and people discovery. Serendipify.Me is effectively the *Match.com* for your user base.

In this repository, we also provide you with a skeleton "Template App" to show you how to use the API. Presumably you identify relevant code, add and customize it for your app, and you will be ready to give your users this matching functionality. 

### HTTP API Details

![SerendipifyMe API Diagram](https://github.com/tibisp/GroupDiscoveryAppTemplate/raw/master/img/SerendipifyMe-API-Diagram.png)

The Serendipify.Me API consists of:
- group management functionality (performed by the group initiator)
 - create new group 
   - OWNEREMAIL receives explanatory email, the call returns the new groups' accessKey OWNERSESSION
    - **REQ:** http://serendipify.me/api/group?createNewGroup=&groupName=GROUPNAME&groupOwnerEmail=OWNEREMAIL
    - **RESP:** gives a link to the group analytics information, on success: { "createNewGroup":"OK",  "groupName":"http://serendipify.me/api/group?getGroupAnalytics=&groupName=GROUPNAME&groupSession=OWNERSESSION"}
 - add user to group 
   - USEREMAIL needed for users to receive successful connection requests from other users, the call returns a accessKey for the user: USERSESSION
    - **REQ:** http://serendipify.me/api/group?addUser=&groupName=GROUPNAME&groupSession=OWNERSESSION&userContact=USEREMAIL
    - **RESP:** gives a link to the users' preferences with the embeded accessKey USERSESSION: { "addUser":"OK", "userName":"http://serendipify.me/api/user?getPrefsForGroup=&groupName=GROUPNAME&groupSession=USERSESSION}
 - assess group activity 
   - call RETURNS group information: user count, etc
    - **REQ**: http://serendipify.me/api/group?getGroupAnalytics=&groupName=GROUPNAME&groupSession=OWNERSESSION
    - **RESP**: { "getGroupAnalytics":"OK","userCount":"NNN"}

- user matching functionality (for your mobine app's users)
 - read my current matching interests 
   - call RETURNS the user's current listed interests
    - **REQ:** http://serendipify.me/api/user?getPrefsForGroup=&groupName=GROUPNAME&userSession=USERSESSION
    - **RESP:** { "getPrefsForGroup":"OK ", "prefs": ["pref-1","pref-2","pref-3"]}
 - set new preferences 
   - call RETURNS a link where the user can find their matches for the current preferences
    - **REQ**: http://serendipify.me/api/user?setPrefsForGroup=&groupName=GROUPNAME&userSession=USERSESSION&prefList=pref-1,pref-2,pref-3
    - **RESP:**  { "setPrefsForGroup":"OK", "prefs":"http://serendipify.me/api/user?getMatchesForGroup=&groupName=GROUPNAME&userSession=USERSESSION"}
 - get the matching users 
   - call RETURNS, for each recorded interest, a list of  MATCHING USERs' CONNECTION STRINGS
    - **REQ:** http://serendipify.me/api/user?getMatchesForGroup=&groupName=GROUPNAME&userSession=USERSESSION
    - **RESP:** {"getMatchesForGroup":"OK",  "matches":{"pref-2":["MATCHUSERCONNSTRING1"],"pref-3":["MATCHUSERCONNSTRING2"],"pref-1":["MATCHUSERCONNSTRING3"]}}
 - initiate a connection request using one of the matching user's connection strings 
   - **REQ:** http://serendipify.me/api/user?initiateContact=&groupName=GROUPNAME&userGroupSession=USERSESSION&targetContact=MATCHUSERCONNSTRING1
    - **RESP:** { "initiateContact":"OK",  "Message":"Contact Request Sent Successfully for common interest "pref-1". You get max 3 messages sent per day." }

### Implementation Example 
The code in this repository is a java library for an example Android template group discovery application.
Go into the folders to read more details.

## The Developer Console
To assist you with developing your mobile application against our API, we make available a development console:
 - [http://serendipify.me/api](http://serendipify.me/api)

