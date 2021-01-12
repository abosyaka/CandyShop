package com.epam.candy.service;

import com.epam.candy.dao.OrderDao;
import com.epam.candy.dao.impl.OrderDaoImpl;
import com.epam.candy.entity.Order;
import com.epam.candy.service.constant.ServiceConstant;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class ShowOrdersService implements Service{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        OrderDao orderDao = OrderDaoImpl.getInstance();

        ArrayList<Order> orders = (ArrayList<Order>) orderDao.findAll();
        request.setAttribute(ServiceConstant.ORDERS, orders);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin_orders_list.jsp");
        dispatcher.forward(request,response);
    }
}
