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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class LoginService implements Service {
    private final UserDao USER_DAO = UserDaoImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        String email = request.getParameter(ServiceConstant.EMAIL);
        String password = request.getParameter(ServiceConstant.PASSWORD);
        HttpSession session = request.getSession();

        User user = USER_DAO.findByEmail(email);

        String redirectTo = UrlConstant.SHOW_LOGIN + "?login=error";

        if (user != null) {
            if (BCrypt.checkpw(password, user.getPassword())) {
                session.setAttribute(SESSION_USER, user);
                redirectTo = UrlConstant.HOME;
            }
        }

        response.sendRedirect(redirectTo);
    }
}
