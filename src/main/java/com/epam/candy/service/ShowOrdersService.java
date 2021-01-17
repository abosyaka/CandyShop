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

public class ShowOrdersService implements Service {
    private final OrderDao orderDao = OrderDaoImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        User user = (User) request.getSession().getAttribute(ServiceConstant.USER);
        if (user != null) {
            if (user.getRole().getName().equals(ServiceConstant.ROLE_ADMIN)) {
                ArrayList<Order> orders = (ArrayList<Order>) orderDao.findAll();
                request.setAttribute(ServiceConstant.ORDERS, orders);

                RequestDispatcher dispatcher = request.getRequestDispatcher("/admin_orders_list.jsp");
                dispatcher.forward(request, response);
            } else {
                response.sendRedirect(UrlConstant.ERROR_403);
            }
        } else {
            response.sendRedirect(UrlConstant.SHOW_LOGIN);
        }
    }
}
