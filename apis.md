## Flows

* all dto's can be found src/main/java/com/example/dealsplus/dto/

### Auth flows
- Sign up 
  - @PostMapping("/signup") - args: RegisterUserDto
  - ![img/signup.png](img/signup.png)  
 
2. Login disabled case

3. Verify Email snip. 
 
4. Account verified.

5. 
  
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


### Structure flows.
- @PostMapping("/create")  - args : StructureDto 
- @PostMapping("/addpermission") - args : StructurePermissionDto - add specific permission (read, write, delete) for a particular user.
- @PostMapping("/read/{structureName}/user/{username}")  - read structure if permissible.
- @PostMapping("/delete/{structureName}/user/{username}") - delete structure if permissible.
- @PostMapping("/edit/{structureName}/user/{username}") - edit structure if permissible. 

Steps:
Setting up gmail for sending mail.
https://www.geeksforgeeks.org/spring-boot-sending-email-via-smtp/

mysql> ALTER TABLE structure ADD CONSTRAINT structure_name_unq UNIQUE(structure_name);
