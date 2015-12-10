
### Serendipify.Me  -  Group Match API for Mobile (Android) apps

#### Introduction

[Serendipify.Me](http://serendipify.me) is a simple service developed to facilitate private interests matching is transient groups.

**You want this for your mobile application** if you intend to build a stronger user community, and to enable your users to **discover common interests and initiate partnerships and friendships from within your application**.

WE make available an API (detailed below) that implements the for group search, matching, and people discovery. Serendipify.Me is effectively the *Match.com* for your user base.

In this repository, we also provide you with a skeleton "Template App" to show you how to use the API. Presumably you identify relevant code, add and customize it for your app, and you will be ready to give your users this matching functionality. 

#### Details and Implementation Example

![SerendipifyMe API Diagram](https://github.com/tibisp/GroupDiscoveryAppTemplate/raw/master/img/SerendipifyMe-API-Diagram.png)

The Serendipify.Me API (configured to work with the ["api instance"](http://api-dot-serendipify-me.appspot.com/) consists of:
- group management functionality (performed by the group initiator)
 - create new group
 http://api-dot-serendipify-me.appspot.com/api/group?createNewGroup=&groupName=GROUPNAME&groupSession=OWNERSESSION
 - add user to group
 http://api-dot-serendipify-me.appspot.com/api/group?addUser=&groupName=GROUPNAME&groupSession=OWNERSESSION&userContact=USEREMAIL
 - assess group activity
http://api-dot-serendipify-me.appspot.com/api/group?getGroupAnalytics=&groupName=GROUPNAME&groupSession=OWNERSESSION


- user matching functionality (for your mobine app's users)
 - read my current matching interests
 http://api-dot-serendipify-me.appspot.com/api/user?getPrefsForGroup=&groupName=GROUPNAME&userSession=USERSESSION
 - set new preferences
 http://api-dot-serendipify-me.appspot.com/api/user?setPrefsForGroup=&groupName=GROUPNAME&userSession=USERSESSION&prefList=Pref-A,Pref-B
 - get the matching users
http://api-dot-serendipify-me.appspot.com/api/user?getMatchesForGroup=&groupName=GROUPNAME&userSession=USERSESSION

### Developer Console
To assist you with developing your mobile application against our API, we make available a development console:
[http://api-dot-serendipify-me.appspot.com/apidevconsole](http://api-dot-serendipify-me.appspot.com/apidevconsole)

