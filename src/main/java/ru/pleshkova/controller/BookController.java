package ru.pleshkova.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.pleshkova.dao.BookDAO;
import ru.pleshkova.dao.PersonDAO;
import ru.pleshkova.models.Book;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookDAO bookDAO;
    private final PersonDAO personDAO;
    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {  //PersonDAO personDAO
         this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookDAO.index());
        return "book/index";
    }

    @PostMapping()
    public String addNewBook(@ModelAttribute("book") Book book) {
        bookDAO.add(book);
        return "redirect:/books";
    }

    @GetMapping("/new")
    public String createNewBookForm(@ModelAttribute("book") Book book) {
        return "book/new";
    }

    @GetMapping("/{id}/edit")
    public String editBookForm(@PathVariable("id") int id, Model model) {
        model.addAttribute("book",bookDAO.show(id));
        return "book/edit";
    }

    @GetMapping("/{id}")
    public String getOneBook(Model model, @PathVariable("id") int id){
        model.addAttribute("book",bookDAO.show(id));
        model.addAttribute("person", personDAO.get(bookDAO.show(id).getId_person()));
        model.addAttribute("people", personDAO.index());
        return "book/show";
    }

    @PatchMapping("/{id}")
    public String editBook(@ModelAttribute("book") Book book, @PathVariable("id") int id){
        bookDAO.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id){
        bookDAO.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/release")
    public String releaseBookTaker(@PathVariable("id") int id){
        bookDAO.release(id);
        return "redirect:/books";
    }
}
