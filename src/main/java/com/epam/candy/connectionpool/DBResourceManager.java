package com.epam.candy.connectionpool;

import java.util.ResourceBundle;

public class DBResourceManager {
    private final static DBResourceManager INSTANCE = new DBResourceManager();

    private final ResourceBundle bundle = ResourceBundle.getBundle("db");

    public static DBResourceManager getInstance(){
        return INSTANCE;
    }

    public String getValue(String key){
        return bundle.getString(key);
    }
}
