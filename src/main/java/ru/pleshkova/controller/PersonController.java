package ru.pleshkova.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.pleshkova.dao.PersonDAO;
import ru.pleshkova.models.Person;
import ru.pleshkova.util.PersonValidator;

@Controller
@RequestMapping("/people")
public class PersonController {
    private final PersonDAO personDAO;
    private final PersonValidator personValidator;

    @Autowired
    public PersonController(PersonDAO personDAO, PersonValidator personValidator) {
        this.personDAO = personDAO;
        this.personValidator = personValidator;
    }
    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", personDAO.index());
        return "person/index";
    }

    @PostMapping()
    public String newPerson(@ModelAttribute("person") @Valid Person person, BindingResult result) {
        personValidator.validate(person, result);
        if (result.hasErrors())
            return "person/new";
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
    public String edit(@PathVariable("id") int id, @ModelAttribute("person") @Valid Person person, BindingResult result) {
        personValidator.validate(person, result);
        if (result.hasErrors())
            return "person/edit";
        personDAO.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id, @ModelAttribute("person") Person person, BindingResult result) {
        if (result.hasErrors())
            return "person/show";
        personDAO.delete(id);
        return "redirect:/people";
    }

}
