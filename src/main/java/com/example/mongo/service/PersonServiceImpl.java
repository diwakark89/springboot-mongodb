package com.example.mongo.service;

import com.example.mongo.collection.Person;
import com.example.mongo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;
    @Override
    public String save(Person person) {
        return personRepository.save(person).getPersonId();
    }
}
