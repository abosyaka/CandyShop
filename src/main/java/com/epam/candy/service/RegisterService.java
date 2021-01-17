package com.epam.candy.service;

import com.epam.candy.dao.UserDao;
import com.epam.candy.dao.impl.UserDaoImpl;
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

public class RegisterService implements Service {
    private final UserDao userDao = UserDaoImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        String email = request.getParameter(ServiceConstant.EMAIL);
        String password = request.getParameter(ServiceConstant.PASSWORD);
        String rePassword = request.getParameter(ServiceConstant.NEW_PASSWORD_CONFIRM);
        String name = request.getParameter(ServiceConstant.NAME);
        String hashedPassword;

        User user = null;

        if (password.equals(rePassword)) {
            hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            user = new User(email, hashedPassword, name);
        } else {
            logger.error("passwords do not match");
        }


        if (userDao.create(user)) {
            response.sendRedirect(UrlConstant.HOME);
        }
    }
}
