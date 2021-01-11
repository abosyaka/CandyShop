package com.epam.candy.dao.impl;

import com.epam.candy.dao.StatusDao;
import com.epam.candy.entity.Role;
import com.epam.candy.entity.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatusDaoImpl implements StatusDao {
    private static final StatusDaoImpl INSTANCE = new StatusDaoImpl();
    private Connection connection = null;

    private static final String SQL_FIND_ALL_STATUS = "SELECT * FROM status";
    private static final String SQL_INSERT_STATUS =
            "INSERT INTO status (status_name) VALUES(?)";
    private static final String SQL_DELETE_STATUS_BY_ID = "DELETE FROM status WHERE status_id=?";
    private static final String SQL_SELECT_STATUS_BY_ID = "SELECT * FROM status WHERE status_id=?";
    private static final String SQL_UPDATE_STATUS =
            "UPDATE status SET status_name=? WHERE status_id=?";

    protected StatusDaoImpl() {
    }

    @Override
    public List<Status> findAll() {
        PreparedStatement preparedStatement = null;
        List<Status> statuses = new ArrayList<>();

        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_ALL_STATUS);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Status status = new Status(
                        resultSet.getLong(COLUMN_ID),
                        resultSet.getString(COLUMN_NAME)
                );

                statuses.add(status);
            }
        } catch (SQLException throwable) {
            logger.error(throwable.getMessage());
        } finally {
            releaseConnection(connection);
            closeStatement(preparedStatement);
        }
        return statuses;
    }

    @Override
    public boolean delete(Long id) {
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_DELETE_STATUS_BY_ID);

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
    public boolean create(Status status) {
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();

            preparedStatement = connection.prepareStatement(SQL_INSERT_STATUS);

            preparedStatement.setString(1, status.getName());

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
    public Status update(Status status) {
        PreparedStatement preparedStatement = null;
        Status updatedStatus = null;

        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_UPDATE_STATUS);

            preparedStatement.setString(1, status.getName());
            preparedStatement.setLong(2, status.getId());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                updatedStatus = new Status(
                        resultSet.getLong(COLUMN_ID),
                        resultSet.getString(COLUMN_NAME)
                );
            }
        } catch (SQLException throwable) {
            logger.error(throwable.getMessage());
        } finally {
            releaseConnection(connection);
            closeStatement(preparedStatement);
        }
        return updatedStatus;
    }

    @Override
    public Status findById(Long id) {
        PreparedStatement preparedStatement = null;
        Status foundStatus = null;

        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_STATUS_BY_ID);

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                foundStatus = new Status(
                        resultSet.getLong(COLUMN_ID),
                        resultSet.getString(COLUMN_NAME)
                );
            }
        } catch (SQLException throwable) {
            logger.error(throwable.getMessage());
        } finally {
            releaseConnection(connection);
            closeStatement(preparedStatement);
        }
        return foundStatus;
    }

    public static StatusDaoImpl getInstance() {
        return INSTANCE;
    }
}
