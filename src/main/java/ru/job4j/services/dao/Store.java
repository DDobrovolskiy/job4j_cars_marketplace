package ru.job4j.services.dao;

import ru.job4j.services.entities.Body;
import ru.job4j.services.entities.Car;
import ru.job4j.services.entities.Mark;
import ru.job4j.services.entities.User;

import java.util.List;
import java.util.Optional;

public interface Store {
    void addBody(Body body);

    List<Body> getBodies();

    void addMark(Mark mark);

    List<Mark> getMarks();

    Optional<Body> getBodyOnId(long id);

    Optional<Mark> getMarkOnId(long id);

    void addUser(User user);

    List<User> getAllUser();

    Optional<User> getUserByEmail(String email);

    void addCar(Car car);

    List<Car> getCarsToday();

    List<Car> getCarsWithPhoto();

    List<Car> getCarsOfMarkOnId(long id);

    List<Car> getAllActiveCars();

    void clearCarsTable();

    void clearBodyTable();

    void clearMarkTable();

    void clearUserTable();

    List<Car> getCarsByIdUser(long id);

    void insertPhotoInCar(long idCar, String namePhoto);

    void changeStatusCar(long idCar, boolean statusCar);
}
