package com.epam.candy.service;

import com.epam.candy.dao.CategoryDao;
import com.epam.candy.dao.impl.CategoryDaoImpl;
import com.epam.candy.entity.Category;
import com.epam.candy.service.constant.ServiceConstant;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class ShowCategoriesService implements Service{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        CategoryDao categoryDao = CategoryDaoImpl.getInstance();

        ArrayList<Category> categories = (ArrayList<Category>) categoryDao.findAll();

        request.setAttribute(ServiceConstant.CATEGORIES, categories);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin_categories_list.jsp");
        dispatcher.forward(request,response);
    }
}
