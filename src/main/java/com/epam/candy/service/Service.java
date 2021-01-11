package com.epam.candy.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public interface Service {
    Logger logger = LogManager.getLogger();
    String SESSION_USER = "user";

    void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException;
}
