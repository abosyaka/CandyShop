package com.epam.candy.service;

import com.epam.candy.dao.UserDao;
import com.epam.candy.dao.impl.UserDaoImpl;
import com.epam.candy.entity.User;
import com.epam.candy.service.factory.UrlConstant;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class RegisterService implements Service {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        UserDao userDao = UserDaoImpl.getInstance();

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String rePassword = request.getParameter("repassword");
        String name = request.getParameter("name");
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
