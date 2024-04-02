


## Flows

### Password reset
-  on failed login three times, the account is locked and passwords needs to be reset.
```
post api call.
post - ("/resetpassword/{email}")
```
sends a mail with password reset option, with a token associated with the user. For uniquely identifying the user.
```
post call /completeresetpassword/{token}/{newpassword}
```
Login is successful post that.

