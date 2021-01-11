package com.epam.candy.dao;

import com.epam.candy.connectionpool.BasicConnectionPool;
import com.epam.candy.connectionpool.ConnectionPool;
import com.epam.candy.entity.Entity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface BaseDao <T extends Entity> {
    ConnectionPool connectionPool = BasicConnectionPool.getInstance();
    Logger logger = LogManager.getLogger();

    List<T> findAll();
    boolean delete(Long id);
    boolean create(T t);
    T update(T t);
    T findById(Long id);

    default void releaseConnection(Connection connection){
        connectionPool.releaseConnection(connection);
    }

    default void closeStatement(Statement statement){
        if(statement != null){
            try {
                statement.close();
            } catch (SQLException throwable) {
                logger.error(throwable.getMessage());
            }
        }
    }
}
