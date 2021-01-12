package com.epam.candy.dao.impl;

import com.epam.candy.dao.OrderDao;
import com.epam.candy.dao.StatusDao;
import com.epam.candy.dao.UserDao;
import com.epam.candy.entity.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    private static final OrderDaoImpl INSTANCE = new OrderDaoImpl();
    private final UserDao userDao = UserDaoImpl.getInstance();
    private final StatusDao statusDao = StatusDaoImpl.getInstance();
    private Connection connection = null;

    private static final String SQL_FIND_ALL_ORDERS = "SELECT * FROM \"order\"";
    private static final String SQL_INSERT_ORDER =
            "INSERT INTO \"order\" (user_id, status_id) VALUES(?,?)";
    private static final String SQL_DELETE_ORDER_BY_ID = "DELETE FROM \"order\" WHERE order_id=?";
    private static final String SQL_SELECT_ORDER_BY_ID = "SELECT * FROM \"order\" WHERE order_id=?";
    private static final String SQL_UPDATE_ORDER =
            "UPDATE \"order\" SET status_id=? WHERE order_id=?";

    protected OrderDaoImpl(){}

    @Override
    public List<Order> findAll() {
        PreparedStatement preparedStatement = null;
        List<Order> orders = new ArrayList<>();

        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_ALL_ORDERS);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Order order = new Order(
                        resultSet.getLong(COLUMN_ID),
                        userDao.findById(resultSet.getLong(COLUMN_USER_ID)),
                        statusDao.findById(resultSet.getLong(COLUMN_STATUS_ID)),
                        resultSet.getDate(COLUMN_ORDER_DATE).toLocalDate()
                );

                orders.add(order);
            }
        } catch (SQLException throwable) {
            logger.error(throwable.getMessage());
        } finally {
            releaseConnection(connection);
            closeStatement(preparedStatement);
        }
        return orders;
    }

    @Override
    public boolean delete(Long id) {
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_DELETE_ORDER_BY_ID);

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
    public boolean create(Order order) {
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();

            preparedStatement = connection.prepareStatement(SQL_INSERT_ORDER);

            preparedStatement.setLong(1, order.getUser().getId());
            preparedStatement.setLong(2, order.getStatus().getId());

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
    public Order update(Order order) {
        PreparedStatement preparedStatement = null;
        Order updatedOrder = null;

        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_UPDATE_ORDER);

            preparedStatement.setLong(1, order.getStatus().getId());
            preparedStatement.setLong(2, order.getId());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                updatedOrder = new Order(
                        resultSet.getLong(COLUMN_ID),
                        userDao.findById(resultSet.getLong(COLUMN_USER_ID)),
                        statusDao.findById(resultSet.getLong(COLUMN_STATUS_ID)),
                        resultSet.getDate(COLUMN_ORDER_DATE).toLocalDate()
                );
            }
        } catch (SQLException throwable) {
            logger.error(throwable.getMessage());
        } finally {
            releaseConnection(connection);
            closeStatement(preparedStatement);
        }
        return updatedOrder;
    }

    @Override
    public Order findById(Long id) {
        PreparedStatement preparedStatement = null;
        Order foundOrder = null;

        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_ORDER_BY_ID);

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                foundOrder = new Order(
                        resultSet.getLong(COLUMN_ID),
                        userDao.findById(resultSet.getLong(COLUMN_USER_ID)),
                        statusDao.findById(resultSet.getLong(COLUMN_STATUS_ID)),
                        resultSet.getDate(COLUMN_ORDER_DATE).toLocalDate()
                );
            }
        } catch (SQLException throwable) {
            logger.error(throwable.getMessage());
        } finally {
            releaseConnection(connection);
            closeStatement(preparedStatement);
        }
        return foundOrder;
    }

    public static OrderDaoImpl getInstance(){
        return INSTANCE;
    }
}
