package com.epam.candy.controller;

import com.epam.candy.service.constant.ServiceConstant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "ImageUploadController")
public class ImageUploadController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Part filePart = request.getPart(ServiceConstant.FILE);
        String fileName = filePart.getSubmittedFileName();

        for(Part part : request.getParts()){
            part.write("src\\main\\webapp\\resources\\img" + fileName);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
