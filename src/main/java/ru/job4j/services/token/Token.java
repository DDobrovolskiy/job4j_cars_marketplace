package ru.job4j.services.token;

public interface Token {
    /**
     * Переданное имя
     * */
    String name();

    /**
     * Полное наименование файла
     * */
    String filename();

    /**
     * Разрешение файла например ".jpeg"
     * */
    String fileContentType();
}
