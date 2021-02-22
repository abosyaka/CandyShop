package com.epam.candy.service;

import com.epam.candy.dao.OrderDetailDao;
import com.epam.candy.dao.impl.OrderDetailDaoImpl;
import com.epam.candy.entity.OrderDetail;
import com.epam.candy.entity.User;
import com.epam.candy.service.constant.ServiceConstant;
import com.epam.candy.service.constant.UrlConstant;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;

public class ShowOrderDetailService implements Service {
    private final OrderDetailDao ORDER_DETAIL_DAO = OrderDetailDaoImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        User currentUser = (User) request.getSession().getAttribute(SESSION_USER);
        if (currentUser == null) {
            response.sendRedirect(UrlConstant.SHOW_LOGIN);
        } else {
            Long id = Long.parseLong(request.getParameter(ServiceConstant.ID));
            Integer totalPrice = 0;

            ArrayList<OrderDetail> orderDetails = (ArrayList<OrderDetail>) ORDER_DETAIL_DAO.findAllByOrderId(id);

            for(OrderDetail detail : orderDetails){
                totalPrice += detail.getCount() * detail.getGood().getPrice();
            }

            DecimalFormat decimalFormat = new DecimalFormat("#,###.00");

            request.setAttribute(ServiceConstant.ORDER_DETAILS, orderDetails);
            request.setAttribute(ServiceConstant.TOTAL_PRICE, decimalFormat.format(totalPrice));

            RequestDispatcher dispatcher = request.getRequestDispatcher("/order_details.jsp");
            dispatcher.forward(request, response);
        }
    }
}
