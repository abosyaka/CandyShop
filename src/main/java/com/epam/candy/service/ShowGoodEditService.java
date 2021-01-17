package com.epam.candy.service;

import com.epam.candy.dao.CategoryDao;
import com.epam.candy.dao.GoodDao;
import com.epam.candy.dao.impl.CategoryDaoImpl;
import com.epam.candy.dao.impl.GoodDaoImpl;
import com.epam.candy.entity.Category;
import com.epam.candy.entity.Good;
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

public class ShowGoodEditService implements Service {
    private final GoodDao goodDao = GoodDaoImpl.getInstance();
    private final CategoryDao categoryDao = CategoryDaoImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        User currentUser = (User) request.getSession().getAttribute(SESSION_USER);
        if (currentUser == null) {
            response.sendRedirect(UrlConstant.SHOW_LOGIN);
        } else if (currentUser.getRole().getName().equals(ServiceConstant.ROLE_USER)) {
            response.sendRedirect(UrlConstant.ERROR_403);
        } else {
            Long id = Long.parseLong(request.getParameter(ServiceConstant.ID));
            Good good = goodDao.findById(id);
            ArrayList<Category> categories = (ArrayList<Category>) categoryDao.findAll();

            request.setAttribute(ServiceConstant.GOOD, good);
            request.setAttribute(ServiceConstant.CATEGORIES, categories);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/edit_good.jsp");
            dispatcher.forward(request, response);
        }
    }
}
