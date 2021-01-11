package com.epam.candy.dao;

import com.epam.candy.entity.User;

public interface UserDao extends BaseDao<User> {
    String COLUMN_ID = "user_id";
    String COLUMN_EMAIL = "user_email";
    String COLUMN_PASSWORD = "user_password";
    String COLUMN_NAME = "user_name";
    String COLUMN_ROLE_ID = "role_id";

    User findByEmail(String email);
}
