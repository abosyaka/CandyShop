package com.epam.candy.service;

import com.epam.candy.dao.OrderDao;
import com.epam.candy.dao.impl.OrderDaoImpl;
import com.epam.candy.entity.Order;
import com.epam.candy.entity.User;
import com.epam.candy.service.constant.ServiceConstant;
import com.epam.candy.service.constant.UrlConstant;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class ShowUserOrdersService implements Service {
    private final OrderDao ORDER_DAO = OrderDaoImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        User currentUser = (User) request.getSession().getAttribute(SESSION_USER);
        if (currentUser == null) {
            response.sendRedirect(UrlConstant.SHOW_LOGIN);
        } else {
            ArrayList<Order> orders = (ArrayList<Order>) ORDER_DAO.findAllByUser(currentUser);

            request.setAttribute(ServiceConstant.ORDERS, orders);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/user_orders.jsp");
        dispatcher.forward(request, response);
    }
}
