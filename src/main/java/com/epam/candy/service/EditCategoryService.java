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

public class EditCategoryService implements Service {
    private final CategoryDao categoryDao = CategoryDaoImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        Long id = Long.parseLong(request.getParameter(ServiceConstant.ID));
        String name = request.getParameter(ServiceConstant.NAME);

        Category category = categoryDao.findById(id);
        category.setName(name);

        String editStatus = ServiceConstant.FAIL;
        if (categoryDao.update(category) != null) {
            editStatus = ServiceConstant.SUCCESS;
        }

        String params = "?" + ServiceConstant.ID +
                "=" + category.getId() + "&" + ServiceConstant.EDIT + "=" + editStatus;

        response.sendRedirect(UrlConstant.ADMIN_SHOW_CATEGORY_EDIT + params);
    }
}
