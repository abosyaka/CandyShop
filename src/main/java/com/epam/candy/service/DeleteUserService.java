package com.epam.candy.service;

import com.epam.candy.dao.UserDao;
import com.epam.candy.dao.impl.UserDaoImpl;
import com.epam.candy.service.constant.ServiceConstant;
import com.epam.candy.service.constant.UrlConstant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class DeleteUserService implements Service {
    private final UserDao USER_DAO = UserDaoImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        Long id = Long.parseLong(request.getParameter(ServiceConstant.ID));

        String deleteStatus = ServiceConstant.FAIL;
        if (USER_DAO.delete(id)) {
            deleteStatus = ServiceConstant.SUCCESS;
        }

        String params = "?" + ServiceConstant.DELETE + "=" + deleteStatus;
        response.sendRedirect(UrlConstant.ADMIN_SHOW_USERS + params);
    }
}
