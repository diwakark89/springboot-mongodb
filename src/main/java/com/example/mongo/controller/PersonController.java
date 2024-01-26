package com.example.mongo.controller;

import com.example.mongo.collection.Person;
import com.example.mongo.service.PersonService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping
    public String save(@RequestBody Person person){
        return personService.save(person);
    }

    @GetMapping
    public List<Person> getPersonWithName(@RequestParam String name){
        return personService.getPersonWithName(name);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id){
        personService.deleteById(id);
    }

    @GetMapping("/all")
    public List<Person> allPerson(){
        return  personService.findAll();
    }

    @GetMapping("/age")
    public List<Person> getByPersonAge(@RequestParam Integer minAge,@RequestParam Integer maxAge){
        return  personService.getByPersonAge(minAge,maxAge);
    }

    @GetMapping("/search")
    public Page<Person> searchPerson(@RequestParam(required = false) Integer minAge,
                                     @RequestParam(required = false) Integer maxAge,
                                     @RequestParam(required = false) String name,
                                     @RequestParam(required = false) String city,
                                     @RequestParam(defaultValue = "0")Integer page,
                                     @RequestParam(defaultValue = "5") Integer size){
        Pageable pageable= PageRequest.of(page,size);
        return  personService.search(name,minAge,maxAge,city,pageable);
    }
}
