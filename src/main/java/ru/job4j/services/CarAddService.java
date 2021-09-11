package ru.job4j.services;

import lombok.extern.slf4j.Slf4j;
import ru.job4j.services.dao.DataAccessObject;
import ru.job4j.services.entities.Car;

import java.time.Instant;
import java.util.Date;

@Slf4j
public class CarAddService {

    private CarAddService() {
    }

    public static void newCar(String description, int price, int bodyId, int markId, String email) {
        new CarAddService().add(description, price, bodyId, markId, email);
    }

    private void add(String description, int price, int bodyId, int markId, String email) {
        try {
            var body = DataAccessObject.instOf().getBodyOnId(bodyId).orElseThrow();
            var mark = DataAccessObject.instOf().getMarkOnId(markId).orElseThrow();
            var user = DataAccessObject.instOf().getUserByEmail(email).orElseThrow();

            Car car = new Car();
            car.setStatus(true);
            car.setDescription(description);
            car.setCreate(Date.from(Instant.now()));
            car.setPrice(price);
            car.setBody(body);
            car.setMark(mark);
            car.setUser(user);

            DataAccessObject.instOf().addCar(car);
        } catch (Exception e) {
            log.error("Error add car.", e);
        }
    }
}
