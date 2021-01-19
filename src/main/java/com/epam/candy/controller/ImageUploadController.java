package com.epam.candy.controller;

import com.epam.candy.dao.GoodDao;
import com.epam.candy.dao.impl.GoodDaoImpl;
import com.epam.candy.entity.Good;
import com.epam.candy.service.constant.ServiceConstant;
import com.epam.candy.service.constant.UrlConstant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ImageUploadController extends HttpServlet {
    private final GoodDao goodDao = GoodDaoImpl.getInstance();
    private final Logger logger = LogManager.getLogger();
    private final String FilePathParameter = "upload.location";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter(ServiceConstant.ID));
        Good good = goodDao.findById(id);
        String path = getServletContext().getInitParameter(FilePathParameter);

        File fileUpload = new File(path);

        Part filePart = request.getPart(ServiceConstant.FILE);
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

        File file = new File(fileUpload, fileName);

        try (InputStream fileContent = filePart.getInputStream()) {
            Files.copy(fileContent, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            good.setPictureUrl(fileName);
            goodDao.update(good);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        response.sendRedirect(UrlConstant.ADMIN_SHOW_GOOD_EDIT + "?id=" + id);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileName = request.getRequestURI().substring(14);


        String path = getServletContext().getInitParameter(FilePathParameter);
        File file = new File(path, fileName);
        response.setHeader("Content-Type", getServletContext().getMimeType(fileName));
        response.setHeader("Content-Length", String.valueOf(file.length()));
        response.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");
        Files.copy(file.toPath(), response.getOutputStream());
    }
}
