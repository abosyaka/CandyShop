package com.epam.candy.filter;

import com.epam.candy.service.constant.ServiceConstant;
import com.epam.candy.service.constant.UrlConstant;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CookieLocaleFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Cookie cookie = null;
        String locale = request.getParameter(ServiceConstant.LANGUAGE);
        Cookie[] cookies = request.getCookies();

        if (locale != null) {
            for(Cookie element : cookies){
                if(element.getValue().equals(ServiceConstant.LANGUAGE)){
                    cookie = element;
                    cookie.setValue(locale);
                    break;
                }
            }
            if(cookie == null) {
                cookie = new Cookie(ServiceConstant.LANGUAGE, locale);
                cookie.setMaxAge(60 * 60 * 24 * 30);
            }
            response.addCookie(cookie);
        }

        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
