package ru.job4j.services;

import net.jcip.annotations.ThreadSafe;
import ru.job4j.services.dao.DataAccessObject;
import ru.job4j.services.entities.User;

import java.util.Optional;

@ThreadSafe
public class LoginService {

    private LoginService() {
    }

    public static Optional<User> getUser(String email, String password) {
        return new LoginService().checkUser(email, password);
    }

    private Optional<User> checkUser(String email, String password) {
        Optional<User> result = Optional.empty();
        var userOptional = DataAccessObject.instOf().getUserByEmail(email);
        if (userOptional.isPresent()) {
            if (userOptional.get().getPassword().equals(password)) {
                result = userOptional;
            }
        }
        return result;
    }
}
