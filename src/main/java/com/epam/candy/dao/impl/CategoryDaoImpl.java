package com.epam.candy.dao.impl;

import com.epam.candy.dao.CategoryDao;
import com.epam.candy.entity.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    private static final CategoryDaoImpl INSTANCE = new CategoryDaoImpl();
    private Connection connection = null;

    private static final String SQL_FIND_ALL_CATEGORIES = "SELECT * FROM category";
    private static final String SQL_INSERT_CATEGORY =
            "INSERT INTO category (category_name) VALUES(?)";
    private static final String SQL_DELETE_CATEGORY_BY_ID = "DELETE FROM category WHERE category_id=?";
    private static final String SQL_SELECT_CATEGORY_BY_ID = "SELECT * FROM category WHERE category_id=?";
    private static final String SQL_UPDATE_CATEGORY =
            "UPDATE category SET category_name=? WHERE category_id=?";

    protected CategoryDaoImpl() {
    }

    @Override
    public List<Category> findAll() {
        PreparedStatement preparedStatement = null;
        List<Category> categories = new ArrayList<>();

        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_ALL_CATEGORIES);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Category category = new Category(
                        resultSet.getLong(COLUMN_ID),
                        resultSet.getString(COLUMN_NAME)
                );

                categories.add(category);
            }
        } catch (SQLException throwable) {
            logger.error(throwable.getMessage());
        } finally {
            releaseConnection(connection);
            closeStatement(preparedStatement);
        }
        return categories;
    }

    @Override
    public boolean delete(Long id) {
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_DELETE_CATEGORY_BY_ID);

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
    public boolean create(Category category) {
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();

            preparedStatement = connection.prepareStatement(SQL_INSERT_CATEGORY);

            preparedStatement.setString(1, category.getName());

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
    public Category update(Category category) {
        PreparedStatement preparedStatement = null;
        Category updatedCategory = null;

        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_UPDATE_CATEGORY);

            preparedStatement.setString(1, category.getName());
            preparedStatement.setLong(2, category.getId());

            preparedStatement.executeUpdate();

            updatedCategory = category;
        } catch (SQLException throwable) {
            logger.error(throwable.getMessage());
        } finally {
            releaseConnection(connection);
            closeStatement(preparedStatement);
        }
        return updatedCategory;
    }

    @Override
    public Category findById(Long id) {
        PreparedStatement preparedStatement = null;
        Category foundCategory = null;

        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_CATEGORY_BY_ID);

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                foundCategory = new Category(
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
        return foundCategory;
    }

    public static CategoryDaoImpl getInstance() {
        return INSTANCE;
    }
}
