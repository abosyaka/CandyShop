package com.epam.candy.dao;

import com.epam.candy.entity.Order;
import com.epam.candy.entity.User;

import java.util.List;

public interface OrderDao extends BaseDao<Order> {
    String COLUMN_ID = "order_id";
    String COLUMN_USER_ID = "user_id";
    String COLUMN_STATUS_ID = "status_id";
    String COLUMN_ORDER_DATE = "order_date";

    List<Order> findAllByUser(User user);
}
