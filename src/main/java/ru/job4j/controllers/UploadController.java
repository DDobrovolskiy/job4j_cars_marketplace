package ru.job4j.controllers;

import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.services.UploadService;
import ru.job4j.services.token.FactoryToken;
import ru.job4j.services.token.Token;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@WebServlet("/upload")
@MultipartConfig
@Log4j2
public class UploadController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        log.debug("Start upload photo");
        log.debug("doGet upload image for id : {}", req.getParameter("id"));
        req.getSession().setAttribute("uploadPhotoCarId", req.getParameter("id"));
        req.getRequestDispatcher("/upload.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.parseInt((String) req.getSession().getAttribute("uploadPhotoCarId"));
        log.debug("doGet upload image for id : {}", id);

        for (Part part : req.getParts()) {
            Token token = FactoryToken.produce(part);
            if (token.fileContentType() != null) {
                log.debug("File load = {}", token.filename());
                log.debug("Save as = {}{}", id, token.fileContentType());
                part.write(UploadService.getSaveDirectory() + File.separator
                        + id + token.fileContentType());
                UploadService.upload(id, id + token.fileContentType());
            }
        }
        resp.sendRedirect(req.getContextPath() + "/profile");
    }
}
