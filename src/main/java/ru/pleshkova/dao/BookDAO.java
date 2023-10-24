package ru.pleshkova.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.pleshkova.models.Book;
import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Book> index(){
        // выгрузить все книги из БД
        return jdbcTemplate.query("SELECT * FROM book", new BookMapper());
    }

    public void delete(int id){
        //удалить книгу с указанным id
        jdbcTemplate.update("DELETE FROM book WHERE id_book=?", id);
    }

    public Book show(int id){
        // вернуть книгу с id из БД
        return jdbcTemplate.query("SELECT * FROM book WHERE id_book=?", new Object[]{id},
                new BookMapper()).stream().findAny().orElse(null);
    }

    public void add(Book book) {
        //добавить книгу в БД
        jdbcTemplate.update("INSERT book (name, author, year) VALUES (?,?,?)", book.getName(), book.getAuthor(),
                book.getYear());
    }
    public void update(int id, Book book) {
        jdbcTemplate.update("UPDATE book SET name=?, author=?, year=? WHERE id_book=?",
                book.getName(), book.getAuthor(), book.getYear(), id);
        //изменить книгу в БД
    }

    public List<Book> getListById(int id_person) {
        // выгрузить все книги из БД
        return jdbcTemplate.query("SELECT * FROM book WHERE id_person=?", new Object[]{id_person},new BookMapper());
    }

    public void release(int id) {
        jdbcTemplate.update("UPDATE book SET id_person=null WHERE id_book=?", id);
    }

    public void take(int id_book, int id_person) {
        jdbcTemplate.update("UPDATE book SET id_person=? WHERE id_book=?", id_person, id_book);
    }



}
