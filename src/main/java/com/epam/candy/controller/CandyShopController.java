package com.epam.candy.controller;

import com.epam.candy.dao.BaseDao;
import com.epam.candy.entity.User;
import com.epam.candy.service.Service;
import com.epam.candy.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;


public class CandyShopController extends HttpServlet {
    static final Logger logger = LogManager.getLogger();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestedUri = request.getRequestURI().toLowerCase();
        ServiceFactory serviceFactory = ServiceFactory.getInstance();

        Service currentService = serviceFactory.getService(requestedUri);

        try{
            currentService.execute(request,response);
        } catch (ParseException | SQLException e) {
            logger.error(e.getMessage());
        }
    }
}
