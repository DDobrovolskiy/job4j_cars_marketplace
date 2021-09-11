package ru.job4j.services;

import lombok.extern.log4j.Log4j2;
import ru.job4j.services.dao.DataAccessObject;
import ru.job4j.services.entities.User;

import java.util.Optional;

@Log4j2
public class RegistrationService {

    private RegistrationService() {
    }

    public static Optional<User> reg(String name, String email, String password, String phone) {
        return new RegistrationService().of(name, email, password, phone);
    }

    private Optional<User> of(String name, String email, String password, String phone) {
        Optional<User> result = Optional.empty();
        var userOptional = DataAccessObject.instOf().getUserByEmail(email);
            if (userOptional.isEmpty()) {
                User user = new User(name, email, password, phone);
                DataAccessObject.instOf().addUser(user);
                result = Optional.of(user);
                log.debug("Add user: {}", user);
            }
        return result;
    }
}
