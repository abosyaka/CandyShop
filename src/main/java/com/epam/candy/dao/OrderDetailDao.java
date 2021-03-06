package com.epam.candy.dao;

import com.epam.candy.entity.OrderDetail;

import java.util.List;

public interface OrderDetailDao extends BaseDao<OrderDetail> {
    String COLUMN_ID = "detail_id";
    String COLUMN_ORDER_ID = "order_id";
    String COLUMN_GOOD_ID = "good_id";
    String COLUMN_COUNT = "count";

    List<OrderDetail> findAllByOrderId(Long id);
}
