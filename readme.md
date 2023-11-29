# Roles and permission handling in spring boot.

An entity - structures.
Access control is done on structure entity through roles and permissions.

- Role to permission mapping.
```
USER(USER_ROLE),
STRUCTURE_READ_AUTHORITY("structure:read"),
STRUCTURE_EDIT_AUTHORITY("structure:read,structure:update"),
STRUCTURE_ADMIN_AUTHORITY("structure:read,structure:update,structure:create,structure:delete"),
ADMIN(ADMIN_ROLE);
```

- Admin role has super access.

- With a structure admin role - all CRUD operations are possible on the entity.
- With a structure read role -  all read operations are possible on the entity.
- With a structure edit role -  all write/update operations are possible on the entity.

Users | Create | Read | Update | Delete |
--- |--------|------|--------|--------|
Structure Read | No     | Yes  | No     | No  
Structure Update/Edit  | No     | Yes  | Yes     | No  
Structure Admin  | Yes    | Yes  | Yes     | Yes  

### Operation by structure read role

  ---------


 
- Roles assigned
- - ![Roles assigned](res/structure-read/roles-assigned.png)  
- Read all
- - ![Read all](res/structure-read/read-all.png)

- Read by name
- - ![Read by name](res/structure-read/roles-assigned.png)

- Structure Edit fails due to auth error
- - ![Edit failed](res/structure-read/structure-edit-failed.png)

- Structure Create fails due to auth error
- - ![Create failed](res/structure-read/create-fail.png)

- Structure Delete fails due to auth error
- - ![Delete failed](res/structure-read/delete-fails.png)


### Operations by structure_edit_role (write/edit possible)

- Registration
- - ![Registration](res/structure-edit/registeration.png)

- Login
- - ![Login](res/structure-edit/login.png)

- Roles assigned
- - ![Roles assigned](res/structure-edit/roles-assigned.png)

- Get all structures
- - ![Roles assigned](res/structure-edit/get-all.png)

- Edit done
- - ![Edit done](res/structure-edit/structure_edit.png)

- Delete fails
- - ![Delete fails](res/structure-edit/delete-failed.png)

- Create fails
- - ![Create fails](res/structure-edit/create-failed.png)

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


