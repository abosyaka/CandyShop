package com.epam.candy.dao;

import com.epam.candy.entity.Role;

public interface RoleDao extends BaseDao<Role> {
    String COLUMN_ID = "role_id";
    String COLUMN_NAME = "role_name";
    Integer ROLE_USER = 1;
    Integer ROLE_ADMIN = 2;
}
