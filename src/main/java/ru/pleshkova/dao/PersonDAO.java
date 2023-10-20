package ru.pleshkova.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.pleshkova.models.Book;
import ru.pleshkova.models.Person;
import java.util.List;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index(){
        //выгрузка всех людей из БД
        return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
    }

    public void add(Person newPerson){
        //добавление нового человека в БД
        jdbcTemplate.update("INSERT INTO person (fio, year) VALUES (?,?)", newPerson.getFio(), newPerson.getYear());
    }

    public Person get(int id) {
        //возвращение человека по id
        return jdbcTemplate.query("SELECT * FROM person WHERE id_person=?",
                new BeanPropertyRowMapper<>(Person.class), id).stream().findAny().orElse(null);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE id_person=?",id);
        // удаление читателя с указанным id
    }

    public void update(int id, Person person) {
        jdbcTemplate.update("UPDATE person SET fio=?, year=? WHERE id_person=?", person.getFio(), person.getYear(), id);
        // редактирование сущ читателя
    }

    public List<Book> getListOfBooksById(int id){
        return jdbcTemplate.query("SELECT book.id_book, book.name, book.author, book.year, book.id_person " +
                "FROM person INNER JOIN book ON person.id_person=book.id_person " +
                "WHERE person.id_person=?", new Object[]{id}, new BookMapper());
    }
}
