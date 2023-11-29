## Flows
- all dto's can be found src/main/java/com/example/dealsplus/dto/

### Auth flows
- Sign up 
  - @PostMapping("/signup") - args: RegisterUserDto
  - ![img/1-signup.png](img/1-signup.png) 
  - sends a verification mail on mail address. 
  
2. Login disabled case
  - unless the account is verified, the login will fail.
  - ![img/2-disabled-login-fails.png](img/2-disabled-login-fails.png)

3. Verify Email snip.
  - Use the link in the email to verify/enable the account.
  - ![img/3-account-verification-link.png](img/3-account-verification-link.png)

4. Account verified.
  - After the link is clicked, following image opens up.
  - ![4-account-verified.png](img/4-account-verified.png)

### 5. Incorrect password case
- Trail 1 - fail
  - ![fail-1.png](img/5-incorrect-password_1.png)
- Trail 2 - fail
  - ![fail-2.png](img/5-incorrect-password_2.png)
- Trail 3 - fail -> locked.
  - ![fail-3.png](img/5-incorrect-password_3.png)
- Test - if account locked, correct password also does not work.
  - ![account-locked.png](img/5-incorrect-password-login-with-right-password-also-doesnt work.png)
- Reset password
  - ![account-locked.png](img/6-1-reset-password-request.png)
  - post - ("/resetpassword/{email}")
- Reset password mail.
  - ![account-locked.png](img/6-2-reset-password-mail.png)
  - each mail has a token -<strong> unique to the user </strong>-. using that url, it's used to reset the password for the following call.
  - baseurl/completeresetpassword/{token}
- Password reset complete.
  - @PostMapping("/completeresetpassword/{token}/{newpassword}")
  - ![account-locked.png](img/7-password-reset-complete.png)

6. Login
   - ![7-2-login.png](img/7-2-login.png)


### Structure flows.
1. Structure create @PostMapping("/create")  - args : StructureDto
   - fail case 1 - any non-admin user tries to create a structure.
   - ![structure-creation.png](img/10-1-structure-create-fail.png)
   - fail case 2 - structure with same name is created.
   - mysql> ALTER TABLE structure ADD CONSTRAINT structure_name_unq UNIQUE(structure_name);
   - ![structure-creation.png](img/10-2-structure-create-fail.png)
   - structure created.
   - ![structure-creation.png](img/10-3-structure-created.png)
   
2. Structure permission flow.
   - Admin user adding structure admin permission to a user for a particular structure.
     - ![structure-perm.png](img/11-admin-user-adding-admin-permission-for-structure-1.png)
   - User having admin permission for a structure, able to assign specific permissionsMapping for different users for the same structure.
     - read
       - - ![structure-perm.png](img/11-structure-admin-user-adding-read-permission-for-user.png)
     - write
       - - ![structure-perm.png](img/11-structure-admin-user-adding-write-permission-for-user.png)
     - delete
       - - ![structure-perm.png](img/11-strcture-admin-user-adding-delete-permission-for-user.png)
  - 

Steps:
Setting up gmail for sending mail.
https://www.geeksforgeeks.org/spring-boot-sending-email-via-smtp/

