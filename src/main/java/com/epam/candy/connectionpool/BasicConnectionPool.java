package com.epam.candy.connectionpool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BasicConnectionPool implements ConnectionPool {
    private static final BasicConnectionPool BASIC_CONNECTION_POOL = new BasicConnectionPool();
    static final Logger LOGGER = LogManager.getLogger();
    private BlockingQueue<Connection> givenAwayConQueue;
    private BlockingQueue<Connection> connectionQueue;

    private final String DRIVER_NAME;
    private final String URL;
    private final String PASSWORD;
    private final String USER;
    private final int POOL_SIZE;

    private BasicConnectionPool(){
        int poolSize;
        DBResourceManager dbResourceManager = DBResourceManager.getInstance();
        this.DRIVER_NAME = dbResourceManager.getValue(DBParameter.DB_DRIVER);
        this.URL = dbResourceManager.getValue(DBParameter.DB_URL);
        this.PASSWORD = dbResourceManager.getValue(DBParameter.DB_PASSWORD);
        this.USER = dbResourceManager.getValue(DBParameter.DB_USER);
        try {
            poolSize = Integer.parseInt(dbResourceManager.getValue(DBParameter.DB_POOLSIZE));
        } catch (NumberFormatException e){
            poolSize = 10;
        }

        POOL_SIZE = poolSize;
        initPoolData();
    }

    public void initPoolData(){
        try {
            Class.forName(DRIVER_NAME);
            givenAwayConQueue = new ArrayBlockingQueue<>(POOL_SIZE);
            connectionQueue = new ArrayBlockingQueue<>(POOL_SIZE);

            for(int i = 0; i < POOL_SIZE; i++){
                Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                connectionQueue.offer(connection);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() {
        Connection connection = null;

        try {
            connection = connectionQueue.take();
            givenAwayConQueue.add(connection);
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage());
        }

        return connection;
    }

    @Override
    public void releaseConnection(Connection connection) {
        if(givenAwayConQueue.remove(connection)){
            connectionQueue.offer(connection);
        }

    }

    @Override
    public void destroyPool(){
        for(int i = 0; i < POOL_SIZE; i++){
            try {
                connectionQueue.take().close();
            } catch (SQLException | InterruptedException throwable) {
                throwable.printStackTrace();
            }
        }
    }

    @Override
    public String getUrl() {
        return this.URL;
    }

    @Override
    public String getPassword() {
        return this.PASSWORD;
    }

    @Override
    public String getUser() {
        return this.USER;
    }

    public static BasicConnectionPool getInstance(){
        return BASIC_CONNECTION_POOL;
    }
}
