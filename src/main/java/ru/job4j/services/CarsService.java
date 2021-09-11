package ru.job4j.services;

import ru.job4j.services.dao.DataAccessObject;
import ru.job4j.services.entities.Car;

import java.util.List;

public class CarsService {
    private CarsService() {
    }

    public static List<Car> getCars() {
        return new CarsService().get();
    }

    private List<Car> get() {
        return DataAccessObject.instOf().getAllActiveCars();
    }
}
