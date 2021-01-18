package com.epam.candy.dao;

import com.epam.candy.entity.Status;

public interface StatusDao extends BaseDao<Status> {
    String COLUMN_ID = "status_id";
    String COLUMN_NAME = "status_name";

    Status findByName(String name);
}
