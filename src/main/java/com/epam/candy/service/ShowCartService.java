package com.epam.candy.service;

import com.epam.candy.entity.Good;
import com.epam.candy.service.constant.ServiceConstant;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class ShowCartService implements Service {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        HttpSession session = request.getSession();
        Map<Good, Integer> cartMap = (Map<Good, Integer>) session.getAttribute(ServiceConstant.CART);

        if (cartMap == null) {
            cartMap = new HashMap<>();
            session.setAttribute(ServiceConstant.CART, cartMap);
        }

        Integer totalPrice = 0;

        for (Good good : cartMap.keySet()) {
            totalPrice += good.getPrice() * cartMap.get(good);
        }

        DecimalFormat decimalFormat = new DecimalFormat("#,###.00");

        request.setAttribute(ServiceConstant.TOTAL_PRICE, decimalFormat.format(totalPrice));

        RequestDispatcher dispatcher = request.getRequestDispatcher("/cart.jsp");
        dispatcher.forward(request, response);
    }
}
