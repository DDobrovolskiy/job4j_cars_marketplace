package ru.job4j.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.services.DownloadService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet("/download")
public class DownloadController extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(DownloadController.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String photo = req.getParameter("photo");
        LOG.debug("Load id image: {}", photo);
        var downloadFile = DownloadService.download(photo);
        LOG.debug(downloadFile.getName());
        String mime = getServletContext().getMimeType(downloadFile.getName());
        LOG.debug(mime);

        if (mime == null) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        resp.setContentType(mime);
        resp.setHeader("Content-Disposition", "attachment; filename=\""
                + downloadFile.getName() + "\"");

        try (var stream = new FileInputStream(downloadFile);
        var out = resp.getOutputStream()) {
            out.write(stream.readAllBytes());
        }
    }
}
