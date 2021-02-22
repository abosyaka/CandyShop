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
import java.util.Map;

public class DeleteFromCartService implements Service {
    private final GoodDao GOOD_DAO = GoodDaoImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        Long id = Long.parseLong(request.getParameter(ServiceConstant.ID));
        Boolean isOne = Boolean.parseBoolean(request.getParameter("isOne"));
        HttpSession session = request.getSession();

        Map<Good, Integer> cartMap = (Map<Good, Integer>) session.getAttribute(ServiceConstant.CART);
        Good good = GOOD_DAO.findById(id);

        if (cartMap != null) {
            if (isOne) {
                if (cartMap.get(good) - 1 < 1) {
                    cartMap.remove(good);
                } else {
                    cartMap.put(good, cartMap.get(good) - 1);
                }
            } else {
                cartMap.remove(good);
            }
        }

        session.setAttribute(ServiceConstant.CART, cartMap);

        response.sendRedirect(UrlConstant.SHOW_CART);
    }
}
