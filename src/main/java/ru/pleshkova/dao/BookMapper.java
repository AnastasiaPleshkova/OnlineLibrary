package ru.pleshkova.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.pleshkova.models.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setId_book(rs.getInt("id_book"));
        book.setName(rs.getString("name"));
        book.setAuthor(rs.getString("author"));
        book.setYear(rs.getInt("year"));
        book.setId_person(rs.getInt("id_person"));
        return book;
    }
}
