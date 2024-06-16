### Roles and permission handling in spring boot.

- Using jwt for authentication.
- Roles/granted authorities & permissions used for authorization.

- Entities - structures, companies. 
- Structures can contain multiple Companies in them.
- Access control is done on structure & company entity through roles and permissions.

### Behaviour

- With a structure admin role - all CRUD operations are possible on the structure & company.
- With a structure edit role - all write/update operations are possible on the structure & all CRUD operations on company entity.
- With a structure read role - all read operations are possible on the structure entity and company entity.


| Users with Roles      | Structure-Create | Structure-Read | Structure-Update | Structure-Delete | Company-Create | Company-Read | Company-Update | Company-Delete |
|-----------------------|------------------|----------------|------------------|------------------|----------------|--------------|----------------|----------------|
| Company Read          | No               | Yes            | No               | No               | No             | Yes          | No             | No             |       
| Company Update/Edit   | No               | No             | No               | No               | No             | Yes          | Yes            | No             |        
| Company Admin         | No               | No             | No               | No               | Yes            | Yes          | Yes            | Yes            |         
| Structure Read        | No               | Yes            | No               | No               | No             | Yes          | No             | No             |          
| Structure Update/Edit | No               | Yes            | Yes              | No               | Yes            | Yes          | Yes            | Yes            |           
| Structure Admin       | Yes              | Yes            | Yes              | Yes              | Yes            | Yes          | Yes            | Yes            |            


- Role to permission mapping.
- User role has no access for structure/company entity.
- Admin role has super access.
```
    USER(USER_ROLE),
    ADMIN(ADMIN_ROLE),
    
    STRUCTURE_READ_AUTHORITY("structure:read,company:read"),
    STRUCTURE_EDIT_AUTHORITY("structure:read,structure:update,company:read,company:update,company:create,company:delete"),
    STRUCTURE_ADMIN_AUTHORITY("structure:read,structure:update,structure:create,structure:delete,company:read,company:update,company:create,company:delete"),

    COMPANY_READ_AUTHORITY("company:read"),
    COMPANY_EDIT_AUTHORITY("company:read,company:update"),
    COMPANY_ADMIN_AUTHORITY("company:read,company:update,company:create,company:delete");
    
```

### Operation by structure read role

  ---------

- Roles assigned
-
    - ![Roles assigned](res/structure-read/roles-assigned.png)
- Read all
-
    - ![Read all](res/structure-read/read-all.png)

- Read by name
-
    - ![Read by name](res/structure-read/roles-assigned.png)

- Structure Edit fails due to auth error
-
    - ![Edit failed](res/structure-read/structure-edit-failed.png)

- Structure Create fails due to auth error
-
    - ![Create failed](res/structure-read/create-fail.png)

- Structure Delete fails due to auth error
-
    - ![Delete failed](res/structure-read/delete-fails.png)

- Company read pass
- - ![Company read](res/structure-read/company-read.png)

- Company 

### Operations by structure_edit_role (write/edit possible)

- Registration
-
    - ![Registration](res/structure-edit/registeration.png)

- Login
-
    - ![Login](res/structure-edit/login.png)

- Roles assigned
-
    - ![Roles assigned](res/structure-edit/roles-assigned.png)

- Get all structures
-
    - ![Roles assigned](res/structure-edit/get-all.png)

- Edit done
-
    - ![Edit done](res/structure-edit/structure_edit.png)

- Delete fails
-
    - ![Delete fails](res/structure-edit/delete-failed.png)

- Create fails
-
    - ![Create fails](res/structure-edit/create-failed.png)

### Operations by structure_admin_user

- Read operation
  ![res/structure-admin/read-operation-by-structure-admin.png](res/structure-admin/read-operation-by-structure-admin.png)

- Create structure operation
  ![res/read-operation-by-structure-admin.png](res/structure-admin/read-operation-by-structure-admin.png)

- Delete structure
  ![res/delete-by-structure-admin.png](res/structure-admin/delete-by-structure-admin.png)

- Update structure
  ![res/structure-edit-structure-admin.png](res/structure-admin/structure-edit-structure-admin.png)

- Get by name
  ![res/structure-by-name-structure-admin.png](res/structure-admin/structure-by-name-structure-admin.png)


