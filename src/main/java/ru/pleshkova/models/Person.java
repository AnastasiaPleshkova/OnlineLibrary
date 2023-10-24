package ru.pleshkova.models;

import jakarta.validation.constraints.*;

public class Person {
    private int id_person;
    @Pattern(regexp = "^[А-ЯЁ][а-яё]* [А-ЯЁ][а-яё]* [А-ЯЁ][а-яё]*$", message = "Введите ФИО в формате \"Иванов Иван Иванович\"")
    private String fio;
    @DecimalMin(value = "13", message = "Возраст не может быть меньше 13")
    @DecimalMax(value = "120", message = "Возраст не может быть больше 120")
    private int year;

    public int getId_person() {
        return id_person;
    }

    public void setId_person(int id_person) {
        this.id_person = id_person;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
