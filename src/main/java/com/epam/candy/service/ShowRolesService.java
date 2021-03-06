package com.epam.candy.service;

import com.epam.candy.dao.RoleDao;
import com.epam.candy.dao.impl.RoleDaoImpl;
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

public class ShowRolesService implements Service {
    private final RoleDao ROLE_DAO = RoleDaoImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        User user = (User) request.getSession().getAttribute(ServiceConstant.USER);
        if (user != null) {
            if (user.getRole().getName().equals(ServiceConstant.ROLE_ADMIN)) {
                ArrayList<Role> roles = (ArrayList<Role>) ROLE_DAO.findAll();
                request.setAttribute(ServiceConstant.ROLES, roles);

                RequestDispatcher dispatcher = request.getRequestDispatcher("/admin_roles_list.jsp");
                dispatcher.forward(request, response);
            } else {
                response.sendRedirect(UrlConstant.ERROR_403);
            }
        } else {
            response.sendRedirect(UrlConstant.SHOW_LOGIN);
        }
    }
}
