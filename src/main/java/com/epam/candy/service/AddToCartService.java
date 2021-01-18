package com.epam.candy.service;

import com.epam.candy.dao.GoodDao;
import com.epam.candy.dao.impl.GoodDaoImpl;
import com.epam.candy.entity.Good;
import com.epam.candy.service.constant.ServiceConstant;
import com.epam.candy.service.constant.UrlConstant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class AddToCartService implements Service {
    private final GoodDao goodDao = GoodDaoImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        HttpSession session = request.getSession();
        Map<Good, Integer> cartMap = (Map<Good, Integer>) session.getAttribute(ServiceConstant.CART);

        Long id = Long.parseLong(request.getParameter(ServiceConstant.ID));
        Good good = goodDao.findById(id);

        if (cartMap == null) {
            cartMap = new HashMap<>();
        }

        if (!cartMap.containsKey(good)) {
            cartMap.put(good, 1);
        } else {
            for (Good item : cartMap.keySet()) {
                if (item.equals(good)) {
                    cartMap.put(good, cartMap.get(good) + 1);
                    break;
                }
            }
        }

        session.setAttribute(ServiceConstant.CART, cartMap);

        response.sendRedirect(UrlConstant.SHOW_CART);
    }
}
