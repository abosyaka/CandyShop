package com.epam.candy.service;

import com.epam.candy.dao.RoleDao;
import com.epam.candy.dao.UserDao;
import com.epam.candy.dao.impl.RoleDaoImpl;
import com.epam.candy.dao.impl.UserDaoImpl;
import com.epam.candy.entity.Role;
import com.epam.candy.entity.User;
import com.epam.candy.service.constant.ServiceConstant;
import com.epam.candy.service.constant.UrlConstant;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class AddUserService implements Service {
    private final RoleDao ROLE_DAO = RoleDaoImpl.getInstance();
    private final UserDao USER_DAO = UserDaoImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        String email = request.getParameter(ServiceConstant.EMAIL);
        String name = request.getParameter(ServiceConstant.NAME);
        String password = request.getParameter(ServiceConstant.PASSWORD);
        String rePass = request.getParameter(ServiceConstant.NEW_PASSWORD_CONFIRM);
        Long roleId = Long.parseLong(request.getParameter(ServiceConstant.ROLE));

        Role role = ROLE_DAO.findById(roleId);

        if(password.equals(rePass)) {
            User user = new User();
            String hashedPass = BCrypt.hashpw(password, BCrypt.gensalt());

            user.setEmail(email);
            user.setPassword(hashedPass);
            user.setName(name);
            user.setRole(role);

            String addStatus = ServiceConstant.FAIL;
            if (USER_DAO.create(user)) {
                addStatus = ServiceConstant.SUCCESS;
            }

            String params = "?" + ServiceConstant.ADD + "=" + addStatus;
            response.sendRedirect(UrlConstant.ADMIN_SHOW_USERS + params);
        }

    }
}
