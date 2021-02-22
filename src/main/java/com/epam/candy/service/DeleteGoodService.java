package com.epam.candy.service;

import com.epam.candy.dao.GoodDao;
import com.epam.candy.dao.impl.GoodDaoImpl;
import com.epam.candy.service.constant.ServiceConstant;
import com.epam.candy.service.constant.UrlConstant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class DeleteGoodService implements Service {
    private final GoodDao GOOD_DAO = GoodDaoImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        Long id = Long.parseLong(request.getParameter(ServiceConstant.ID));

        String deleteStatus = ServiceConstant.FAIL;
        if (GOOD_DAO.delete(id)) {
            deleteStatus = ServiceConstant.SUCCESS;
        }

        String params = "?" + ServiceConstant.DELETE + "=" + deleteStatus;
        response.sendRedirect(UrlConstant.ADMIN_SHOW_GOODS + params);
    }
}
