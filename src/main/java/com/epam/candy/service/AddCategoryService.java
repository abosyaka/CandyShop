package com.epam.candy.service;

import com.epam.candy.dao.CategoryDao;
import com.epam.candy.dao.impl.CategoryDaoImpl;
import com.epam.candy.entity.Category;
import com.epam.candy.service.constant.ServiceConstant;
import com.epam.candy.service.constant.UrlConstant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class AddCategoryService implements Service{
    private final CategoryDao CATEGORY_DAO = CategoryDaoImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        String name = request.getParameter(ServiceConstant.NAME);

        Category category = new Category(name);

        String addStatus = ServiceConstant.FAIL;
        if (CATEGORY_DAO.create(category)) {
            addStatus = ServiceConstant.SUCCESS;
        }

        String params = "?" + ServiceConstant.ADD + "=" + addStatus;
        response.sendRedirect(UrlConstant.ADMIN_SHOW_CATEGORIES + params);
    }
}
