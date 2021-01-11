package com.epam.candy.connectionpool;

import java.sql.Connection;

public interface ConnectionPool {
    Connection getConnection();
    void releaseConnection(Connection connection);
    String getUrl();
    String getPassword();
    String getUser();
    void destroyPool();
}
