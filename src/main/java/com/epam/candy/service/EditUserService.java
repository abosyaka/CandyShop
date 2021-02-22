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

public class EditUserService implements Service {
    private final UserDao USER_DAO = UserDaoImpl.getInstance();
    private final RoleDao ROLE_DAO = RoleDaoImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {

        String email = request.getParameter(ServiceConstant.EMAIL);
        String name = request.getParameter(ServiceConstant.NAME);
        String newPass = request.getParameter(ServiceConstant.NEW_PASSWORD);
        String reNewPass = request.getParameter(ServiceConstant.NEW_PASSWORD_CONFIRM);
        Long roleId = Long.parseLong(request.getParameter(ServiceConstant.ROLE));

        User user = USER_DAO.findByEmail(email);
        Role role = ROLE_DAO.findById(roleId);

        user.setName(name);
        user.setRole(role);

        if (!newPass.isEmpty() && !reNewPass.isEmpty()) {
            if (newPass.equals(reNewPass)) {
                String hashedPassword = BCrypt.hashpw(newPass, BCrypt.gensalt());
                user.setPassword(hashedPassword);
            }
        }

        String editStatus = ServiceConstant.FAIL;
        if (USER_DAO.update(user) != null) {
            editStatus = ServiceConstant.SUCCESS;
        }

        String params = "?" + ServiceConstant.ID +
                "=" + user.getId() + "&" + ServiceConstant.EDIT + "=" + editStatus;

        response.sendRedirect(UrlConstant.ADMIN_SHOW_USER_EDIT + params);
    }
}
