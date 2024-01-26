package com.example.mongo.service;

import com.example.mongo.collection.Person;
import com.example.mongo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class PersonServiceImpl implements PersonService {


    private final PersonRepository personRepository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository, MongoTemplate mongoTemplate)
    {
        this.personRepository = personRepository;
        this.mongoTemplate = mongoTemplate;
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

    @Override
    public Page<Person> search(String name, Integer minAge, Integer maxAge, String city, Pageable pageable)
    {
        Query query=new Query().with(pageable);
        List<Criteria> criteriaList=new ArrayList<>(4);

        if(null != name && !name.isEmpty()){
            criteriaList.add(Criteria.where("firstName").regex(name,"i"));
        }

        if(null != minAge ){
            criteriaList.add(Criteria.where("age").gte(minAge));
        }
        if(null != maxAge ){
            criteriaList.add(Criteria.where("age").lte(maxAge));
        }
        if(null != city && !city.isEmpty()){
            criteriaList.add(Criteria.where("addresses.city").is(city));
        }

        if(!criteriaList.isEmpty()){
            query.addCriteria(new Criteria().andOperator(criteriaList.toArray(new Criteria[0])));
        }

        Page<Person> people = PageableExecutionUtils.getPage(
                mongoTemplate.find(query, Person.class
                ), pageable, () -> mongoTemplate.count(query.skip(0).limit(0),Person.class));

        return people;
    }
}
