package com.epam.candy.service;

import com.epam.candy.dao.OrderDao;
import com.epam.candy.dao.OrderDetailDao;
import com.epam.candy.dao.StatusDao;
import com.epam.candy.dao.impl.OrderDaoImpl;
import com.epam.candy.dao.impl.OrderDetailDaoImpl;
import com.epam.candy.dao.impl.StatusDaoImpl;
import com.epam.candy.entity.Order;
import com.epam.candy.entity.OrderDetail;
import com.epam.candy.entity.Status;
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

public class ShowOrderEditService implements Service {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        User currentUser = (User) request.getSession().getAttribute(SESSION_USER);
        if(currentUser == null){
            response.sendRedirect(UrlConstant.SHOW_LOGIN);
        } else if(currentUser.getRole().getName().equals(ServiceConstant.ROLE_USER)){
            response.sendRedirect(UrlConstant.ERROR_403);
        } else {
            OrderDao orderDao = OrderDaoImpl.getInstance();
            StatusDao statusDao = StatusDaoImpl.getInstance();
            OrderDetailDao orderDetailDao = OrderDetailDaoImpl.getInstance();

            Long id = Long.parseLong(request.getParameter(ServiceConstant.ID));

            ArrayList<OrderDetail> details = (ArrayList<OrderDetail>) orderDetailDao.findAllByOrderId(id);
            Order order = orderDao.findById(id);
            ArrayList<Status> statuses = (ArrayList<Status>) statusDao.findAll();

            request.setAttribute(ServiceConstant.ORDER_DETAILS, details);
            request.setAttribute(ServiceConstant.STATUSES, statuses);
            request.setAttribute(ServiceConstant.ORDER, order);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/edit_order.jsp");
            dispatcher.forward(request, response);
        }
    }
}
