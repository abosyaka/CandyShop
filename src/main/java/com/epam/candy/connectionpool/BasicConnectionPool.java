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
    static final Logger logger = LogManager.getLogger();
    private BlockingQueue<Connection> givenAwayConQueue;
    private BlockingQueue<Connection> connectionQueue;

    private final String driverName;
    private final String url;
    private final String password;
    private final String user;
    private int poolSize;

    private BasicConnectionPool(){
        DBResourceManager dbResourceManager = DBResourceManager.getInstance();
        this.driverName = dbResourceManager.getValue(DBParameter.DB_DRIVER);
        this.url = dbResourceManager.getValue(DBParameter.DB_URL);
        this.password = dbResourceManager.getValue(DBParameter.DB_PASSWORD);
        this.user = dbResourceManager.getValue(DBParameter.DB_USER);
        try {
            poolSize = Integer.parseInt(dbResourceManager.getValue(DBParameter.DB_POOLSIZE));
        } catch (NumberFormatException e){
            poolSize = 10;
        }

        initPoolData();
    }

    public void initPoolData(){
        try {
            Class.forName(driverName);
            givenAwayConQueue = new ArrayBlockingQueue<>(poolSize);
            connectionQueue = new ArrayBlockingQueue<>(poolSize);

            for(int i = 0; i < poolSize; i++){
                Connection connection = DriverManager.getConnection(url,user,password);
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
            logger.error(e.getMessage());
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
        for(int i = 0; i < poolSize; i++){
            try {
                connectionQueue.take().close();
            } catch (SQLException | InterruptedException throwable) {
                throwable.printStackTrace();
            }
        }
    }

    @Override
    public String getUrl() {
        return this.url;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUser() {
        return this.user;
    }

    public static BasicConnectionPool getInstance(){
        return BASIC_CONNECTION_POOL;
    }
}
