package com.epam.candy.dao.impl;

import com.epam.candy.connectionpool.BasicConnectionPool;
import com.epam.candy.dao.CategoryDao;
import com.epam.candy.dao.GoodDao;
import com.epam.candy.dao.RoleDao;
import com.epam.candy.entity.Category;
import com.epam.candy.entity.Good;
import com.epam.candy.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodDaoImpl implements GoodDao {
    private static final GoodDaoImpl INSTANCE = new GoodDaoImpl();
    private final CategoryDao categoryDao = CategoryDaoImpl.getInstance();
    private Connection connection;

    private static final String SQL_FIND_ALL_GOODS = "SELECT * FROM good";
    private static final String SQL_INSERT_GOOD =
            "INSERT INTO good (good_name, good_description, picture_url, good_weight, price, category_id, ingredients, storage_period) " +
                    "VALUES(?,?,?,?,?,?,?,?)";
    private static final String SQL_DELETE_GOOD_BY_ID = "DELETE FROM good WHERE good_id=?";
    private static final String SQL_SELECT_GOOD_BY_ID = "SELECT * FROM good WHERE good_id=?";
    private static final String SQL_UPDATE_GOOD =
            "UPDATE good SET " +
                    "good_name=?, good_description=?, picture_url=?, good_weight=?, price=?, category_id=?, ingredients=?, storage_period=? " +
                    "WHERE good_id=?";
    private static final String SQL_FIND_ALL_BY_CATEGORY = "SELECT * FROM good WHERE category_id=?";
    private static final String SQL_FIND_ALL_LIKE = "SELECT * FROM good WHERE good_name ILIKE ?";

    protected GoodDaoImpl() {
    }

    @Override
    public List<Good> findAll() {
        List<Good> goods = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_ALL_GOODS);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Good good = new Good(
                        resultSet.getLong(COLUMN_ID),
                        resultSet.getString(COLUMN_NAME),
                        resultSet.getString(COLUMN_DESCRIPTION),
                        resultSet.getString(COLUMN_PICTURE_URL),
                        resultSet.getDouble(COLUMN_WEIGHT),
                        resultSet.getInt(COLUMN_PRICE),
                        categoryDao.findById(resultSet.getLong(COLUMN_CATEGORY_ID)),
                        resultSet.getString(COLUMN_INGREDIENTS),
                        resultSet.getInt(COLUMN_STORAGE_PERIOD)
                );

                goods.add(good);
            }
        } catch (SQLException throwable) {
            logger.error(throwable.getMessage());
        } finally {
            releaseConnection(connection);
            closeStatement(preparedStatement);
        }
        return goods;
    }

    @Override
    public boolean delete(Long id) {
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_DELETE_GOOD_BY_ID);
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
    public boolean create(Good good) {
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();

            preparedStatement = connection.prepareStatement(SQL_INSERT_GOOD);

            preparedStatement.setString(1, good.getName());
            preparedStatement.setString(2, good.getDescription());
            preparedStatement.setString(3, good.getPictureUrl());
            preparedStatement.setDouble(4, good.getWeight());
            preparedStatement.setInt(5, good.getPrice());
            preparedStatement.setLong(6, good.getCategory().getId());
            preparedStatement.setString(7, good.getIngredients());
            preparedStatement.setInt(8, good.getStoragePeriod());

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
    public Good update(Good good) {
        PreparedStatement preparedStatement = null;
        Good updatedGood = good;
        try {
            connection = connectionPool.getConnection();

            preparedStatement = connection.prepareStatement(SQL_UPDATE_GOOD);

            preparedStatement.setString(1, good.getName());
            preparedStatement.setString(2, good.getDescription());
            preparedStatement.setString(3, good.getPictureUrl());
            preparedStatement.setDouble(4, good.getWeight());
            preparedStatement.setInt(5, good.getPrice());
            preparedStatement.setLong(6, good.getCategory().getId());
            preparedStatement.setString(7, good.getIngredients());
            preparedStatement.setInt(8, good.getStoragePeriod());
            preparedStatement.setLong(9, good.getId());

            preparedStatement.executeUpdate();

            updatedGood = good;
        } catch (SQLException throwable) {
            logger.error(throwable.getMessage());
        } finally {
            releaseConnection(connection);
            closeStatement(preparedStatement);
        }
        return updatedGood;
    }

    @Override
    public Good findById(Long id) {
        Good foundGood = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();

            preparedStatement = connection.prepareStatement(SQL_SELECT_GOOD_BY_ID);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                foundGood = new Good(
                        resultSet.getLong(COLUMN_ID),
                        resultSet.getString(COLUMN_NAME),
                        resultSet.getString(COLUMN_DESCRIPTION),
                        resultSet.getString(COLUMN_PICTURE_URL),
                        resultSet.getDouble(COLUMN_WEIGHT),
                        resultSet.getInt(COLUMN_PRICE),
                        categoryDao.findById(resultSet.getLong(COLUMN_CATEGORY_ID)),
                        resultSet.getString(COLUMN_INGREDIENTS),
                        resultSet.getInt(COLUMN_STORAGE_PERIOD)
                );
            }
        } catch (SQLException throwable) {
            logger.error(throwable.getMessage());
        } finally {
            releaseConnection(connection);
            closeStatement(preparedStatement);
        }
        return foundGood;
    }

    @Override
    public List<Good> findAllByCategory(Category category) {
        List<Good> goods = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_ALL_BY_CATEGORY);
            preparedStatement.setLong(1, category.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Good good = new Good(
                        resultSet.getLong(COLUMN_ID),
                        resultSet.getString(COLUMN_NAME),
                        resultSet.getString(COLUMN_DESCRIPTION),
                        resultSet.getString(COLUMN_PICTURE_URL),
                        resultSet.getDouble(COLUMN_WEIGHT),
                        resultSet.getInt(COLUMN_PRICE),
                        categoryDao.findById(resultSet.getLong(COLUMN_CATEGORY_ID)),
                        resultSet.getString(COLUMN_INGREDIENTS),
                        resultSet.getInt(COLUMN_STORAGE_PERIOD)
                );

                goods.add(good);
            }
        } catch (SQLException throwable) {
            logger.error(throwable.getMessage());
        } finally {
            releaseConnection(connection);
            closeStatement(preparedStatement);
        }
        return goods;
    }

    @Override
    public List<Good> findAllLike(String value) {
        List<Good> goods = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_ALL_LIKE);
            preparedStatement.setString(1, "%" + value + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Good good = new Good(
                        resultSet.getLong(COLUMN_ID),
                        resultSet.getString(COLUMN_NAME),
                        resultSet.getString(COLUMN_DESCRIPTION),
                        resultSet.getString(COLUMN_PICTURE_URL),
                        resultSet.getDouble(COLUMN_WEIGHT),
                        resultSet.getInt(COLUMN_PRICE),
                        categoryDao.findById(resultSet.getLong(COLUMN_CATEGORY_ID)),
                        resultSet.getString(COLUMN_INGREDIENTS),
                        resultSet.getInt(COLUMN_STORAGE_PERIOD)
                );

                goods.add(good);
            }
        } catch (SQLException throwable) {
            logger.error(throwable.getMessage());
        } finally {
            releaseConnection(connection);
            closeStatement(preparedStatement);
        }
        return goods;
    }

    public static GoodDaoImpl getInstance() {
        return INSTANCE;
    }
}
