package ru.job4j.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.services.MarksService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cars/marks")
public class MarksController extends HttpServlet {
    private static final Gson GSON = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String marks = GSON.toJson(MarksService.get());
        resp.setCharacterEncoding("UTF-8");
        try (var out = resp.getWriter()) {
            out.println(marks);
            out.flush();
        }
    }
}
