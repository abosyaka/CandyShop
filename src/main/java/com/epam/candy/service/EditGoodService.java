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

public class EditGoodService implements Service {
    CategoryDao categoryDao = CategoryDaoImpl.getInstance();
    GoodDao goodDao = GoodDaoImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        String name = request.getParameter(ServiceConstant.NAME);
        Long id = Long.parseLong(request.getParameter(ServiceConstant.ID));
        String description = request.getParameter(ServiceConstant.DESCRIPTION);
        String ingredients = request.getParameter(ServiceConstant.INGREDIENTS);
        Long categoryId = Long.parseLong(request.getParameter(ServiceConstant.CATEGORY));
        Integer price = Integer.parseInt(request.getParameter(ServiceConstant.PRICE));
        Double weight = Double.parseDouble(request.getParameter(ServiceConstant.WEIGHT));
        Integer storagePeriod = Integer.parseInt(request.getParameter(ServiceConstant.STORAGE_PERIOD));

        Category category = categoryDao.findById(categoryId);
        Good good = goodDao.findById(id);

        good.setCategory(category);
        good.setName(name);
        good.setDescription(description);
        good.setIngredients(ingredients);
        good.setPrice(price);
        good.setWeight(weight);
        good.setStoragePeriod(storagePeriod);

        String editStatus = ServiceConstant.FAIL;
        if (goodDao.update(good) != null) {
            editStatus = ServiceConstant.SUCCESS;
        }

        String params = "?" + ServiceConstant.ID +
                "=" + good.getId() + "&" + ServiceConstant.EDIT + "=" + editStatus;

        response.sendRedirect(UrlConstant.ADMIN_SHOW_GOOD_EDIT + params);
    }
}
