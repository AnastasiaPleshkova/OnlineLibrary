package ru.pleshkova.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.pleshkova.dao.BookDAO;
import ru.pleshkova.dao.PersonDAO;
import ru.pleshkova.models.Person;

@Controller
@RequestMapping("/people")
public class PersonController {
    private final PersonDAO personDAO;
//    private final BookDAO bookDAO;
    @Autowired
    public PersonController(PersonDAO personDAO) {
        this.personDAO = personDAO;
//        this.bookDAO = bookDAO;
    }
    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", personDAO.index());
        return "person/index";
    }

    @PostMapping()
    public String newPerson(@ModelAttribute("person") Person person) {
        personDAO.add(person);
        return "redirect:/people";
    }

    @GetMapping("/new")
    public String formNewPerson(@ModelAttribute("person") Person person) {
        return "person/new";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.get(id));
        model.addAttribute("books", personDAO.getListOfBooksById(id));
        return "/person/show";
    }

    @GetMapping("/{id}/edit")
    public String formEdit(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.get(id));
        return "/person/edit";
    }

    @PatchMapping("/{id}")
    public String edit(@PathVariable("id") int id, @ModelAttribute("person") Person person) {
        personDAO.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/people";
    }

}
