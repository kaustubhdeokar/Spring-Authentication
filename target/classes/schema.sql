-- constraints

alter table users_roles add constraint unique_user_role_map UNIQUE(userId, roleId);

-- basic
insert IGNORE into roles (name) values ('USER');

-- structure roles
insert IGNORE into roles (name) values ('STRUCTURE_READ_AUTHORITY');
insert IGNORE into roles (name) values ('STRUCTURE_EDIT_AUTHORITY');
insert IGNORE into roles (name) values ('STRUCTURE_ADMIN_AUTHORITY');
insert IGNORE into roles (name) values ('STRUCTURE_DELETE_AUTHORITY');
insert IGNORE into roles (name) values ('COMPANY_READ_AUTHORITY');
insert IGNORE into roles (name) values ('COMPANY_EDIT_AUTHORITY');
insert IGNORE into roles (name) values ('COMPANY_ADMIN_AUTHORITY');
-- super: admin
insert IGNORE into roles (name) values ('ADMIN');

insert IGNORE into users (username, password, email, created, enabled) values ('admin','$2a$10$VdOP5tsUE4COnt1bEvexM.RkMBxkS/HdHgnRh9LEJG8XzUeX.izPC', 'admin@example.com', CURRENT_TIMESTAMP, true);

select userId into @user_id from users where username='admin';
select roleId into @role_id from roles where name='ADMIN';
INSERT IGNORE into users_roles (userId, roleId) values(@user_id, @role_id);

