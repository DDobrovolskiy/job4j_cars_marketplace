package ru.job4j.controllers;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.services.UpdateStatusCarService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet("/cars/update-status")
public class UpdateCarStatusController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        boolean status = Boolean.parseBoolean(req.getParameter("status"));
        log.info("id - {}", id);
        log.info("Status - {}", status);
        UpdateStatusCarService.update(id, status);
    }
}
