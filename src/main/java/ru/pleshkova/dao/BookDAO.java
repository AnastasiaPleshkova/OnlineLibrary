package ru.pleshkova.dao;

import org.springframework.stereotype.Component;
import ru.pleshkova.models.Book;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookDAO {

    public List<Book> index(){
        // выгрузить все книги из БД
        return new ArrayList<>();
    }

    public void delete(int id){
        //удалить книгу с указанным id
    }

    public Book show(int id){
        // вернуть книгу с id из БД
        return new Book();
    }

    public void add(Book book){
        //добавить книгу в БД
    }
    public void update(int id, Book book){
        //изменить книгу в БД
    }

}
