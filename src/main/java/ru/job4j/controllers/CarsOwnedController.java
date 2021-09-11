package ru.job4j.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import ru.job4j.services.CarAddService;
import ru.job4j.services.CarsOwnerService;
import ru.job4j.services.entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/cars/owned")
@Slf4j
public class CarsOwnedController extends HttpServlet {

    private static final Gson GSON = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        var user = (User) req.getSession().getAttribute("user");
        log.debug("Get cars users: {}", user);
        String carAll = GSON.toJson(CarsOwnerService.getCars(user.getId()));
        try (var out = resp.getOutputStream()) {
            out.write(carAll.getBytes(StandardCharsets.UTF_8));
            out.flush();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String description = req.getParameter("description");
        int price = Integer.parseInt(req.getParameter("price"));
        int bodyId = Integer.parseInt(req.getParameter("bodyId"));
        int markId = Integer.parseInt(req.getParameter("markId"));
        var user = (User) req.getSession().getAttribute("user");
        log.debug("Description: {}", description);
        log.debug("Price: {}", price);
        log.debug("Body ID: {}", bodyId);
        log.debug("Mark ID: {}", markId);
        log.debug("User: {}", user);
        CarAddService.newCar(description, price, bodyId, markId, user.getEmail());
        resp.sendRedirect(req.getContextPath() + "/profile");
    }
}
