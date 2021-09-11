package ru.job4j.services;

import ru.job4j.services.dao.DataAccessObject;

public class UpdateStatusCarService {
    private UpdateStatusCarService() {
    }

    public static void update(int idCar, boolean status) {
        new UpdateStatusCarService().insertStatus(idCar, status);
    }

    private void insertStatus(int idCar, boolean status) {
        DataAccessObject.instOf().changeStatusCar(idCar, status);
    }
}
