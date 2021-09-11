package ru.job4j.services;

import ru.job4j.services.dao.DataAccessObject;
import ru.job4j.services.entities.Car;

import java.util.List;

public class CarsOwnerService {

    private CarsOwnerService() {
    }

    public static List<Car> getCars(long userId) {
        return new CarsOwnerService().get(userId);
    }

    private List<Car> get(long userId) {
        return DataAccessObject.instOf().getCarsByIdUser(userId);
    }
}
