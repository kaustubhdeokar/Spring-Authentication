# Dealsplus - backend assignment

## **Objective**:

We are going to develop a compact authentication and authorisation module for Dealsplus.

## Context:

- Dealsplus manages multiple investments (also called structures) of a private equity fund. Each structure holds various data relevant for the fund to manage that investment (equity data, data of different companies in that investment ..). we have different types of users, there are some users who should only read the structure data and others can write as well, hence there is a need to have role based access control
- Different types of roles :
    - Global admin : - 
        - can read, write, delete on all structure data - Done
        - Manage roles and permissions for other users.
    - Structure reader on structure X:
        - can read on a particular structure data - Done
    - Structure admin on structure X:
        - can read, write, delete on a given structure data - Done
        - Can add / remove a structure reader - 
- A point to note here is a given user can have multiple roles, they can be a reader on structure X, admin on structure Y, reader on structure Z - Done

### Requirements:

- Done : Create a User with attributes like username, email, password, roles
- Done : Implement relevant endpoints for user registration, login, and role management .. 
- Use Spring Security to secure the endpoints and enforce role-based access control. if you are using a different programming language choose the appropriate framework accordingly
- Done: Implement password hashing.
- Support user account locking after multiple failed login attempts.
- Implement password reset functionality with 
- Done: email-based verification.

### **;Evaluation Criteria (non-exhaustive):**

- Proper implementation of CRUD operations using appropriate HTTP methods and status codes.
- Adherence to REST principles and best practices.
- Correct usage of Spring Boot annotations and dependencies (for Java).
- Proper exception handling and error responses.
- Code quality, readability, and adherence to Java best practices.
- Clear and concise documentation on how to run and test the API.

### Submission:

- Submit your code by creating a private github link
- Deploy your REST API’s on a hosting platform of your choice
- Share the submission to sai@dealsplus.io


Steps:
Setting up gmail for sending mail. 
https://www.geeksforgeeks.org/spring-boot-sending-email-via-smtp/