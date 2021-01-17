package com.epam.candy.service;

import com.epam.candy.dao.CategoryDao;
import com.epam.candy.dao.impl.CategoryDaoImpl;
import com.epam.candy.entity.Category;
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

public class ShowCategoryEditService implements Service {
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
            Category category = categoryDao.findById(id);

            request.setAttribute(ServiceConstant.CATEGORY, category);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/edit_category.jsp");
            dispatcher.forward(request, response);
        }
    }
}
