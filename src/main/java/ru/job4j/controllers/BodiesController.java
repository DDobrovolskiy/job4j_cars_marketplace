package ru.job4j.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.services.BodiesService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet("/cars/bodies")
public class BodiesController extends HttpServlet {
    private static final Gson GSON = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String bodies = GSON.toJson(BodiesService.get());
        try (var out = resp.getOutputStream()) {
            out.write(bodies.getBytes(StandardCharsets.UTF_8));
            out.flush();
        }
    }
}
