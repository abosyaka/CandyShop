package com.epam.candy.service;

import com.epam.candy.dao.OrderDao;
import com.epam.candy.dao.OrderDetailDao;
import com.epam.candy.dao.StatusDao;
import com.epam.candy.dao.impl.OrderDaoImpl;
import com.epam.candy.dao.impl.OrderDetailDaoImpl;
import com.epam.candy.dao.impl.StatusDaoImpl;
import com.epam.candy.entity.*;
import com.epam.candy.service.constant.ServiceConstant;
import com.epam.candy.service.constant.UrlConstant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Map;

public class MakeOrderService implements Service{
    private final OrderDao ORDER_DAO = OrderDaoImpl.getInstance();
    private final OrderDetailDao ORDER_DETAIL_DAO = OrderDetailDaoImpl.getInstance();
    private final StatusDao STATUS_DAO = StatusDaoImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute(SESSION_USER);
        if(currentUser == null){
            response.sendRedirect(UrlConstant.SHOW_LOGIN);
        } else {
            Map<Good, Integer> cartMap = (Map<Good, Integer>) session.getAttribute(ServiceConstant.CART);
            Status status = STATUS_DAO.findByName(ServiceConstant.STATUS_WAITING);

            Order order = new Order(
                    currentUser,
                    status
            );

            ORDER_DAO.create(order);



            for(Good good : cartMap.keySet()){
                OrderDetail orderDetail = new OrderDetail(
                        order,
                        good,
                        cartMap.get(good)
                );

                ORDER_DETAIL_DAO.create(orderDetail);
            }
            session.removeAttribute(ServiceConstant.CART);
            response.sendRedirect(UrlConstant.SHOW_CART);
        }
    }
}
