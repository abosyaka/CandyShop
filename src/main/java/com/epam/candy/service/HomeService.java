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
    private final CategoryDao CATEGORY_DAO = CategoryDaoImpl.getInstance();
    private final GoodDao GOOD_DAO = GoodDaoImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        ArrayList<Good> goods;
        String categoryIdParameter = request.getParameter(ServiceConstant.CATEGORY);
        String searchParameter = request.getParameter(ServiceConstant.SEARCH);

        if (categoryIdParameter != null) {
            Category category = CATEGORY_DAO.findById(Long.parseLong(categoryIdParameter));
            goods = (ArrayList<Good>) GOOD_DAO.findAllByCategory(category);
        } else if (searchParameter != null) {
            goods = (ArrayList<Good>) GOOD_DAO.findAllLike(searchParameter);
        } else {
            goods = (ArrayList<Good>) GOOD_DAO.findAll();
        }
        ArrayList<Category> categories = (ArrayList<Category>) CATEGORY_DAO.findAll();

        request.setAttribute(ServiceConstant.CATEGORIES, categories);
        request.setAttribute(ServiceConstant.GOODS, goods);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }
}
