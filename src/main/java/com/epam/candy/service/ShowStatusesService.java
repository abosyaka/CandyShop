package com.epam.candy.service;

import com.epam.candy.dao.StatusDao;
import com.epam.candy.dao.impl.StatusDaoImpl;
import com.epam.candy.entity.Status;
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
import java.util.ArrayList;

public class ShowStatusesService implements Service {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        User user = (User) request.getSession().getAttribute(ServiceConstant.USER);
        if (user != null) {
            if (user.getRole().getName().equals(ServiceConstant.ROLE_ADMIN)) {
                StatusDao statusDao = StatusDaoImpl.getInstance();

                ArrayList<Status> statuses = (ArrayList<Status>) statusDao.findAll();
                request.setAttribute(ServiceConstant.STATUSES, statuses);

                RequestDispatcher dispatcher = request.getRequestDispatcher("/admin_statuses_list.jsp");
                dispatcher.forward(request, response);
            } else {
                response.sendRedirect(UrlConstant.ERROR_403);
            }
        } else {
            response.sendRedirect(UrlConstant.SHOW_LOGIN);
        }
    }
}
