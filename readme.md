
- [Setup \& Steps to Run](#setup--steps-to-run)
- [Project Summary](#project-summary)
- [High Level Diagram](#high-level-diagram)
- [Behaviour](#behaviour)
- [Results](#results)
  - [Operation by structure read role](#operation-by-structure-read-role)
  - [Operations by structure\_edit\_role (write/edit possible)](#operations-by-structure_edit_role-writeedit-possible)
  - [Operations by structure\_admin\_user](#operations-by-structure_admin_user)

#### Setup & Steps to Run
For setup & steps to run, please refer: steps.md

#### Project Summary
Roles and permission handling in spring boot.
- Using jwt for authentication.
- Roles/granted authorities & permissions used for authorization.
- Entities - structures, companies. 
- Structures can contain multiple Companies in them.
- Access control is done on structure & company entity through roles and permissions.

#### High Level Diagram
![High level Diagram](hld.png)

#### Behaviour
- Details about roles and permissions.
- Role to permission mapping.
- User role has no access for structure/company entity.
- Admin role has super access.

#### Results

##### Operation by structure read role
- Roles assigned
  ![Roles assigned](res/structure-read/roles-assigned.png)
- Read all
  ![Read all](res/structure-read/read-all.png)
- Read by name
  ![Read by name](res/structure-read/roles-assigned.png)
- Structure Edit fails due to auth error
  ![Edit failed](res/structure-read/structure-edit-failed.png)
- Structure Create fails due to auth error
  ![Create failed](res/structure-read/create-fail.png)
- Structure Delete fails due to auth error
  ![Delete failed](res/structure-read/delete-fails.png)
- Company read pass
  ![Company read](res/structure-read/company-read.png)

##### Operations by structure_edit_role (write/edit possible)
- Registration
  ![Registration](res/structure-edit/registeration.png)
- Login
  ![Login](res/structure-edit/login.png)
- Roles assigned
  ![Roles assigned](res/structure-edit/roles-assigned.png)
- Get all structures
  ![Roles assigned](res/structure-edit/get-all.png)
- Edit done
  ![Edit done](res/structure-edit/structure_edit.png)
- Delete fails
  ![Delete fails](res/structure-edit/delete-failed.png)
- Create fails
  ![Create fails](res/structure-edit/create-failed.png)

##### Operations by structure_admin_user
- Read operation
  ![Read operation](res/structure-admin/read-operation-by-structure-admin.png)
- Create structure operation
  ![Create structure operation](res/structure-admin/read-operation-by-structure-admin.png)
- Delete structure
  ![Delete structure](res/structure-admin/delete-by-structure-admin.png)
- Update structure
  ![Update structure](res/structure-admin/structure-edit-structure-admin.png)
- Get by name
  ![Get by name](res/structure-admin/structure-by-name-structure-admin.png)
