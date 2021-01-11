package com.epam.candy.dao.impl;

import com.epam.candy.dao.RoleDao;
import com.epam.candy.entity.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl implements RoleDao {
    private static final RoleDaoImpl INSTANCE = new RoleDaoImpl();
    private Connection connection = null;

    private static final String SQL_FIND_ALL_ROLES = "SELECT * FROM role";
    private static final String SQL_INSERT_ROLE =
            "INSERT INTO role (role_name) VALUES(?)";
    private static final String SQL_DELETE_ROLE_BY_ID = "DELETE FROM role WHERE role_id=?";
    private static final String SQL_SELECT_ROLE_BY_ID = "SELECT * FROM role WHERE role_id=?";
    private static final String SQL_UPDATE_ROLE =
            "UPDATE user SET role_name=? WHERE role_id=?";

    protected RoleDaoImpl() {
    }

    @Override
    public List<Role> findAll() {
        PreparedStatement preparedStatement = null;
        List<Role> roles = new ArrayList<>();

        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_ALL_ROLES);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Role role = new Role(
                        resultSet.getLong(COLUMN_ID),
                        resultSet.getString(COLUMN_NAME)
                );

                roles.add(role);
            }
        } catch (SQLException throwable) {
            logger.error(throwable.getMessage());
        } finally {
            releaseConnection(connection);
            closeStatement(preparedStatement);
        }
        return roles;
    }

    @Override
    public boolean delete(Long id) {
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_DELETE_ROLE_BY_ID);

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
    public boolean create(Role role) {
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();

            preparedStatement = connection.prepareStatement(SQL_INSERT_ROLE);

            preparedStatement.setString(1, role.getName());

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
    public Role update(Role role) {
        PreparedStatement preparedStatement = null;
        Role updatedRole = null;

        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_UPDATE_ROLE);

            preparedStatement.setString(1, role.getName());
            preparedStatement.setLong(2, role.getId());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                updatedRole = new Role(
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
        return updatedRole;
    }

    @Override
    public Role findById(Long id) {
        PreparedStatement preparedStatement = null;
        Role foundRole = null;

        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_ROLE_BY_ID);

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                foundRole = new Role(
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
        return foundRole;
    }

    public static RoleDaoImpl getInstance() {
        return INSTANCE;
    }
}
