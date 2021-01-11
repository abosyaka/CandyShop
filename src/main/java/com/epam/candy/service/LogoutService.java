package com.epam.candy.service;

import com.epam.candy.service.factory.UrlConstant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class LogoutService implements Service {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        request.getSession().invalidate();
        response.sendRedirect(UrlConstant.HOME);
    }
}
