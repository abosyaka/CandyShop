package com.epam.candy.service;

import com.epam.candy.dao.CategoryDao;
import com.epam.candy.dao.GoodDao;
import com.epam.candy.dao.impl.CategoryDaoImpl;
import com.epam.candy.dao.impl.GoodDaoImpl;
import com.epam.candy.entity.Category;
import com.epam.candy.entity.Good;
import com.epam.candy.service.constant.ServiceConstant;
import com.epam.candy.service.constant.UrlConstant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class AddGoodService implements Service {
    private final GoodDao GOOD_DAO = GoodDaoImpl.getInstance();
    private final CategoryDao CATEGORY_DAO = CategoryDaoImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        Long categoryId = Long.parseLong(request.getParameter(ServiceConstant.CATEGORY));
        String name = request.getParameter(ServiceConstant.NAME);
        String description = request.getParameter(ServiceConstant.DESCRIPTION);
        String ingredients = request.getParameter(ServiceConstant.INGREDIENTS);
        Integer price = Integer.parseInt(request.getParameter(ServiceConstant.PRICE));
        Double weight = Double.parseDouble(request.getParameter(ServiceConstant.WEIGHT));
        Integer storagePeriod = Integer.parseInt(request.getParameter(ServiceConstant.STORAGE_PERIOD));

        Category category = CATEGORY_DAO.findById(categoryId);
        Good good = new Good();

        good.setName(name);
        good.setDescription(description);
        good.setCategory(category);
        good.setStoragePeriod(storagePeriod);
        good.setWeight(weight);
        good.setPrice(price);
        good.setIngredients(ingredients);

        String addStatus = ServiceConstant.FAIL;
        if (GOOD_DAO.create(good)) {
            addStatus = ServiceConstant.SUCCESS;
        }

        String params = "?" + ServiceConstant.ADD + "=" + addStatus;
        response.sendRedirect(UrlConstant.ADMIN_SHOW_GOODS + params);
    }
}
