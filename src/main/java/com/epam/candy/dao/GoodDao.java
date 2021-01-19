package com.epam.candy.dao;

import com.epam.candy.entity.Category;
import com.epam.candy.entity.Good;

import java.util.List;

public interface GoodDao extends BaseDao<Good> {
    String COLUMN_ID = "good_id";
    String COLUMN_NAME = "good_name";
    String COLUMN_DESCRIPTION = "good_description";
    String COLUMN_PICTURE_URL = "picture_url";
    String COLUMN_WEIGHT = "good_weight";
    String COLUMN_PRICE = "price";
    String COLUMN_CATEGORY_ID = "category_id";
    String COLUMN_INGREDIENTS = "ingredients";
    String COLUMN_STORAGE_PERIOD = "storage_period";

    List<Good> findAllByCategory(Category category);
    List<Good> findAllLike(String value);
}
