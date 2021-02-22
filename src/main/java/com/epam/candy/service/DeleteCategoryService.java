package com.epam.candy.service;

import com.epam.candy.dao.CategoryDao;
import com.epam.candy.dao.impl.CategoryDaoImpl;
import com.epam.candy.service.constant.ServiceConstant;
import com.epam.candy.service.constant.UrlConstant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class DeleteCategoryService implements Service {
    private final CategoryDao CATEGORY_DAO = CategoryDaoImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        Long id = Long.parseLong(request.getParameter(ServiceConstant.ID));

        String deleteStatus = ServiceConstant.FAIL;
        if (CATEGORY_DAO.delete(id)) {
            deleteStatus = ServiceConstant.SUCCESS;
        }

        String params = "?" + ServiceConstant.DELETE + "=" + deleteStatus;
        response.sendRedirect(UrlConstant.ADMIN_SHOW_CATEGORIES + params);
    }
}
