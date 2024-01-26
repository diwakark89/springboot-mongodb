package com.example.mongo.service;

import com.example.mongo.collection.Person;
import com.example.mongo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class PersonServiceImpl implements PersonService {


    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository)
    {
        this.personRepository = personRepository;
    }

    @Override
    public String save(Person person) {
        return personRepository.save(person).getPersonId();
    }

    @Override
    public List<Person> getPersonWithName(String name)
    {
        return personRepository.findByFirstNameStartsWith(name);
    }

    @Override
    public void deleteById(String id)
    {
        personRepository.deleteById(id);
    }

    @Override
    public List<Person> findAll()
    {
        return personRepository.findAll();
    }

    @Override
    public List<Person> getByPersonAge(Integer minAge, Integer maxAge)
    {
        return personRepository.findByAgeBetween(minAge,maxAge);
    }
}
