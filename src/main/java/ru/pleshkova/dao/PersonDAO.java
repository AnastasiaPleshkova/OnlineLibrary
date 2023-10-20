package ru.pleshkova.dao;

import org.springframework.stereotype.Component;
import ru.pleshkova.models.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

    public List<Person> index(){
        return new ArrayList<>();
    }
}
