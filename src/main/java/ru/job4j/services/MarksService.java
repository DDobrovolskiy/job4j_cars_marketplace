package ru.job4j.services;

import ru.job4j.services.dao.DataAccessObject;
import ru.job4j.services.entities.Mark;

import java.util.List;

public class MarksService {
    private MarksService() {
    }

    public static List<Mark> get() {
        return new MarksService().getAllMark();
    }

    private List<Mark> getAllMark() {
        return DataAccessObject.instOf().getMarks();
    }
}
