package com.epam.candy.service;

import com.epam.candy.dao.UserDao;
import com.epam.candy.dao.impl.UserDaoImpl;
import com.epam.candy.entity.User;
import com.epam.candy.service.constant.ServiceConstant;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class ShowUsersService implements Service {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        UserDao userDao = UserDaoImpl.getInstance();

        ArrayList<User> users = (ArrayList<User>) userDao.findAll();
        request.setAttribute(ServiceConstant.USERS, userDao.findAll());

        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin_users_list.jsp");
        dispatcher.forward(request,response);
    }
}
