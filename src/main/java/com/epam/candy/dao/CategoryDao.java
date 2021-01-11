package com.epam.candy.dao;

import com.epam.candy.entity.Category;

public interface CategoryDao extends BaseDao<Category> {
    String COLUMN_ID = "category_id";
    String COLUMN_NAME = "category_name";
}
