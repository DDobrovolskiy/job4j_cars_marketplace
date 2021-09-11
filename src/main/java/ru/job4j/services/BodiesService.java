package ru.job4j.services;

import ru.job4j.services.dao.DataAccessObject;
import ru.job4j.services.entities.Body;

import java.util.List;

public class BodiesService {
    private BodiesService() {
    }

    public static List<Body> get() {
        return new BodiesService().getAllBodies();
    }

    private List<Body> getAllBodies() {
        return DataAccessObject.instOf().getBodies();
    }
}
