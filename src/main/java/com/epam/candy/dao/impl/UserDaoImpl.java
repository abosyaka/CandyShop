package com.epam.candy.dao.impl;

import com.epam.candy.dao.RoleDao;
import com.epam.candy.dao.UserDao;
import com.epam.candy.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private static final UserDaoImpl INSTANCE = new UserDaoImpl();
    private final RoleDao roleDao = RoleDaoImpl.getInstance();
    private Connection connection;

    private static final String SQL_FIND_ALL_USERS = "SELECT * FROM \"user\"";
    private static final String SQL_INSERT_USER =
            "INSERT INTO \"user\" (user_email, user_password, user_name, role_id) VALUES(?,?,?,?)";
    private static final String SQL_DELETE_USER_BY_ID = "DELETE FROM \"user\" WHERE user_id=?";
    private static final String SQL_SELECT_USER_BY_ID = "SELECT * FROM \"user\" WHERE user_id=?";
    private static final String SQL_SELECT_USER_BY_EMAIL = "SELECT * FROM \"user\" WHERE user_email=?";
    private static final String SQL_UPDATE_USER =
            "UPDATE \"user\" SET user_email=?, user_password=?, user_name=?, role_id=? WHERE user_id=?";

    protected UserDaoImpl() {
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_ALL_USERS);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User(
                        resultSet.getLong(COLUMN_ID),
                        resultSet.getString(COLUMN_EMAIL),
                        resultSet.getString(COLUMN_PASSWORD),
                        resultSet.getString(COLUMN_NAME),
                        roleDao.findById(resultSet.getLong(COLUMN_ROLE_ID))
                );

                users.add(user);
            }
        } catch (SQLException throwable) {
            logger.error(throwable.getMessage());
        } finally {
            releaseConnection(connection);
            closeStatement(preparedStatement);
        }
        return users;
    }

    @Override
    public boolean delete(Long id) {
        if(id == null) return false;

        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_DELETE_USER_BY_ID);
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
    public boolean create(User user) {
        if(user == null) return false;

        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();

            preparedStatement = connection.prepareStatement(SQL_INSERT_USER);

            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setInt(4, roleDao.ROLE_USER);

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
    public User update(User user) {

        PreparedStatement preparedStatement = null;
        User updatedUser = null;
        try {
            connection = connectionPool.getConnection();

            preparedStatement = connection.prepareStatement(SQL_UPDATE_USER);

            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setInt(4, user.getRole().getId().intValue());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                updatedUser = new User(
                        resultSet.getLong(COLUMN_ID),
                        resultSet.getString(COLUMN_EMAIL),
                        resultSet.getString(COLUMN_PASSWORD),
                        resultSet.getString(COLUMN_NAME),
                        roleDao.findById(resultSet.getLong(COLUMN_ROLE_ID))
                );

            }
        } catch (SQLException throwable) {
            logger.error(throwable.getMessage());
        } finally {
            releaseConnection(connection);
            closeStatement(preparedStatement);
        }
        return updatedUser;
    }

    @Override
    public User findById(Long id) {
        User foundUser = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();

            preparedStatement = connection.prepareStatement(SQL_SELECT_USER_BY_ID);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                foundUser = new User(
                        resultSet.getLong(COLUMN_ID),
                        resultSet.getString(COLUMN_EMAIL),
                        resultSet.getString(COLUMN_PASSWORD),
                        resultSet.getString(COLUMN_NAME),
                        roleDao.findById(resultSet.getLong(COLUMN_ROLE_ID))
                );
            }
        } catch (SQLException throwable) {
            logger.error(throwable.getMessage());
        } finally {
            releaseConnection(connection);
            closeStatement(preparedStatement);
        }
        return foundUser;
    }

    @Override
    public User findByEmail(String email) {
        User foundUser = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();

            preparedStatement = connection.prepareStatement(SQL_SELECT_USER_BY_EMAIL);
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                foundUser = new User(
                        resultSet.getLong(COLUMN_ID),
                        resultSet.getString(COLUMN_EMAIL),
                        resultSet.getString(COLUMN_PASSWORD),
                        resultSet.getString(COLUMN_NAME),
                        roleDao.findById(resultSet.getLong(COLUMN_ROLE_ID))
                );
            }
        } catch (SQLException throwable) {
            logger.error(throwable.getMessage());
        } finally {
            releaseConnection(connection);
            closeStatement(preparedStatement);
        }
        return foundUser;
    }

    public static UserDaoImpl getInstance() {
        return INSTANCE;
    }
}
