package com.epam.candy.service;

import com.epam.candy.dao.RoleDao;
import com.epam.candy.dao.impl.RoleDaoImpl;
import com.epam.candy.entity.Role;
import com.epam.candy.service.constant.ServiceConstant;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class ShowRolesService implements Service {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        RoleDao roleDao = RoleDaoImpl.getInstance();

        ArrayList<Role> roles = (ArrayList<Role>) roleDao.findAll();
        request.setAttribute(ServiceConstant.ROLES, roles);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin_roles_list.jsp");
        dispatcher.forward(request,response);
    }
}
