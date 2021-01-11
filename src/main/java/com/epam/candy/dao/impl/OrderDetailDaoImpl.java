package com.epam.candy.dao.impl;

import com.epam.candy.dao.*;
import com.epam.candy.entity.Order;
import com.epam.candy.entity.OrderDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDaoImpl implements OrderDetailDao {
    private static final OrderDetailDaoImpl INSTANCE = new OrderDetailDaoImpl();
    private final OrderDao orderDao = OrderDaoImpl.getInstance();
    private final GoodDao goodDao = GoodDaoImpl.getInstance();
    private Connection connection = null;

    private static final String SQL_FIND_ALL_ORDER_DETAILS = "SELECT * FROM order_detail";
    private static final String SQL_INSERT_ORDER_DETAIL =
            "INSERT INTO order_detail (order_id, good_id, count) VALUES(?,?,?)";
    private static final String SQL_DELETE_ORDER_DETAIL_BY_ID = "DELETE FROM order_detail WHERE detail_id=?";
    private static final String SQL_SELECT_ORDER_DETAIL_BY_ID = "SELECT * FROM order_detail WHERE detail_id=?";
    private static final String SQL_UPDATE_ORDER_DETAIL =
            "UPDATE order_detail SET good_id=?, count=? WHERE detail_id=?";

    protected OrderDetailDaoImpl() {
    }

    @Override
    public List<OrderDetail> findAll() {
        PreparedStatement preparedStatement = null;
        List<OrderDetail> orderDetails = new ArrayList<>();

        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_ALL_ORDER_DETAILS);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                OrderDetail orderDetail = new OrderDetail(
                        resultSet.getLong(COLUMN_ID),
                        orderDao.findById(resultSet.getLong(COLUMN_ORDER_ID)),
                        goodDao.findById(resultSet.getLong(COLUMN_GOOD_ID)),
                        resultSet.getInt(COLUMN_COUNT)
                );

                orderDetails.add(orderDetail);
            }
        } catch (SQLException throwable) {
            logger.error(throwable.getMessage());
        } finally {
            releaseConnection(connection);
            closeStatement(preparedStatement);
        }
        return orderDetails;
    }

    @Override
    public boolean delete(Long id) {
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_DELETE_ORDER_DETAIL_BY_ID);

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

        } catch (SQLException throwable) {
            logger.error(throwable.getMessage());
            return false;
        } finally {
            releaseConnection(connection);
            closeStatement(preparedStatement);
        }
        return true;
    }

    @Override
    public boolean create(OrderDetail orderDetail) {
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();

            preparedStatement = connection.prepareStatement(SQL_INSERT_ORDER_DETAIL);

            preparedStatement.setLong(1, orderDetail.getOrder().getId());
            preparedStatement.setLong(2, orderDetail.getGood().getId());
            preparedStatement.setInt(3, orderDetail.getCount());

            preparedStatement.executeUpdate();
        } catch (SQLException throwable) {
            logger.error(throwable.getMessage());
            return false;
        } finally {
            releaseConnection(connection);
            closeStatement(preparedStatement);
        }
        return true;
    }

    @Override
    public OrderDetail update(OrderDetail orderDetail) {
        PreparedStatement preparedStatement = null;
        OrderDetail updatedOrderDetail = null;

        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_UPDATE_ORDER_DETAIL);

            preparedStatement.setLong(1, orderDetail.getGood().getId());
            preparedStatement.setInt(2, orderDetail.getCount());
            preparedStatement.setLong(3, orderDetail.getId());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                updatedOrderDetail = new OrderDetail(
                        resultSet.getLong(COLUMN_ID),
                        orderDao.findById(resultSet.getLong(COLUMN_ORDER_ID)),
                        goodDao.findById(resultSet.getLong(COLUMN_GOOD_ID)),
                        resultSet.getInt(COLUMN_COUNT)
                );
            }
        } catch (SQLException throwable) {
            logger.error(throwable.getMessage());
        } finally {
            releaseConnection(connection);
            closeStatement(preparedStatement);
        }
        return updatedOrderDetail;
    }

    @Override
    public OrderDetail findById(Long id) {
        PreparedStatement preparedStatement = null;
        OrderDetail foundOrderDetail = null;

        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_ORDER_DETAIL_BY_ID);

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                foundOrderDetail = new OrderDetail(
                        resultSet.getLong(COLUMN_ID),
                        orderDao.findById(resultSet.getLong(COLUMN_ORDER_ID)),
                        goodDao.findById(resultSet.getLong(COLUMN_GOOD_ID)),
                        resultSet.getInt(COLUMN_COUNT)
                );
            }
        } catch (SQLException throwable) {
            logger.error(throwable.getMessage());
        } finally {
            releaseConnection(connection);
            closeStatement(preparedStatement);
        }
        return foundOrderDetail;
    }

    public static OrderDetailDaoImpl getInstance() {
        return INSTANCE;
    }
}
