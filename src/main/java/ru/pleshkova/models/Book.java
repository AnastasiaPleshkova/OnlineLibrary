package ru.pleshkova.models;
public class Book {
    private int id_book;
    private String name;
    private String author;
    private int year;
    @Override
    public String toString() {
        return "Book{" +
                "id_book=" + id_book +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                '}';
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public int getId_book() {
        return id_book;
    }
    public void setId_book(int id_book) {
        this.id_book = id_book;
    }
}
