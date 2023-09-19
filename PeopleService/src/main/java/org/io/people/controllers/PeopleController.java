package org.io.people.controllers;

import org.io.people.entities.Person;
import org.io.people.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PeopleController {
    @Autowired
    private PeopleService peopleService;

    @GetMapping("people/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable("id") int id){
        return ResponseEntity.status(HttpStatus.OK).body(peopleService.getPersonById(id));
    }
}
