package com.epam.candy.service;

import com.epam.candy.service.constant.ServiceConstant;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class ChangeLocaleService implements Service {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        String locale = request.getParameter(ServiceConstant.LANGUAGE);

        Cookie cookie = new Cookie(ServiceConstant.LANGUAGE, locale);
        response.addCookie(cookie);
    }
}
