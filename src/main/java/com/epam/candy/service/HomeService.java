package com.epam.candy.service;

import com.epam.candy.dao.CategoryDao;
import com.epam.candy.dao.GoodDao;
import com.epam.candy.dao.impl.CategoryDaoImpl;
import com.epam.candy.dao.impl.GoodDaoImpl;
import com.epam.candy.entity.Category;
import com.epam.candy.entity.Good;
import com.epam.candy.service.constant.ServiceConstant;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class HomeService implements Service {
    private final CategoryDao categoryDao = CategoryDaoImpl.getInstance();
    private final GoodDao goodDao = GoodDaoImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        ArrayList<Good> goods;
        String categoryIdParameter = request.getParameter(ServiceConstant.CATEGORY);
        String searchParameter = request.getParameter(ServiceConstant.SEARCH);

        if (categoryIdParameter != null) {
            Category category = categoryDao.findById(Long.parseLong(categoryIdParameter));
            goods = (ArrayList<Good>) goodDao.findAllByCategory(category);
        } else if (searchParameter != null) {
            goods = (ArrayList<Good>) goodDao.findAllLike(searchParameter);
        } else {
            goods = (ArrayList<Good>) goodDao.findAll();
        }
        ArrayList<Category> categories = (ArrayList<Category>) categoryDao.findAll();

        request.setAttribute(ServiceConstant.CATEGORIES, categories);
        request.setAttribute(ServiceConstant.GOODS, goods);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }
}
