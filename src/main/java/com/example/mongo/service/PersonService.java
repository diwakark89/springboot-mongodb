package com.example.mongo.service;

import com.example.mongo.collection.Person;

import java.util.List;

public interface PersonService {
    String save(Person person);

    List<Person> getPersonWithName(String name);

    void deleteById(String id);

    List<Person> findAll();

    List<Person> getByPersonAge(Integer minAge, Integer maxAge);
}
