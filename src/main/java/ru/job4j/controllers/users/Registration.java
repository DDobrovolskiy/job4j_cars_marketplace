package ru.job4j.controllers.users;

import ru.job4j.services.RegistrationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/reg")
public class Registration extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/reg.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String phone = req.getParameter("phone");
        var userOptional = RegistrationService.reg(name, email, password, phone);
            if (userOptional.isPresent()) {
                req.getSession().setAttribute("user", userOptional.get());
                resp.addCookie(new Cookie("email", email));
                resp.addCookie(new Cookie("password", password));
                resp.sendRedirect(req.getContextPath() + "/");
            } else {
                doGet(req, resp);
            }
    }
}
