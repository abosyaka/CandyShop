package com.epam.candy.service;

import com.epam.candy.dao.RoleDao;
import com.epam.candy.dao.UserDao;
import com.epam.candy.dao.impl.RoleDaoImpl;
import com.epam.candy.dao.impl.UserDaoImpl;
import com.epam.candy.entity.Role;
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

public class ShowUserEditService implements Service {
    private final UserDao USER_DAO = UserDaoImpl.getInstance();
    private final RoleDao ROLE_DAO = RoleDaoImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        User currentUser = (User) request.getSession().getAttribute(SESSION_USER);
        if(currentUser == null){
            response.sendRedirect(UrlConstant.SHOW_LOGIN);
        } else if(currentUser.getRole().getName().equals(ServiceConstant.ROLE_USER)) {
            response.sendRedirect(UrlConstant.ERROR_403);
        } else {
            Long id = Long.parseLong(request.getParameter(ServiceConstant.ID));
            User user = USER_DAO.findById(id);
            ArrayList<Role> roles = (ArrayList<Role>) ROLE_DAO.findAll();

            request.setAttribute(ServiceConstant.ROLES, roles);
            request.setAttribute(ServiceConstant.USER, user);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/edit_user.jsp");
            dispatcher.forward(request, response);
        }
    }
}
