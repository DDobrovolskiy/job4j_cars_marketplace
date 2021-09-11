package ru.job4j.controllers.users;

import lombok.extern.log4j.Log4j2;
import ru.job4j.services.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@Log4j2
@WebServlet("/login")
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/login.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        log.debug("Email: {}", email);
        log.debug("Password: {}", password);

        var userOptional = LoginService.getUser(email, password);

        log.debug("User: {}", userOptional);

        if (userOptional.isPresent()) {
                HttpSession sc = req.getSession();
                sc.setAttribute("user", userOptional.get());
                log.debug("Add user in session: {}", userOptional.get());
                resp.addCookie(new Cookie("email", email));
                resp.addCookie(new Cookie("password", password));
                log.debug("Add cookie");
                resp.sendRedirect(req.getContextPath() + "/");
        } else {
            doGet(req, resp);
        }
    }

}
