package org.io.people.controllers;

import org.io.people.entities.Person;
import org.io.people.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PeopleController {
    @Autowired
    private PeopleService peopleService;

    @CrossOrigin
    @GetMapping("people/all")
    public ResponseEntity<List<Person>> getPeople(){
        return ResponseEntity.status(HttpStatus.OK).body(peopleService.getPeople());
    }
    @CrossOrigin
    @GetMapping("people/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable("id") int id){
        return ResponseEntity.status(HttpStatus.OK).body(peopleService.getPersonById(id));
    }

    @CrossOrigin
    @DeleteMapping("people/delete/{id}")
    public ResponseEntity<Void> deletePersonById(@PathVariable("id") int id){
        peopleService.deletePersonById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @CrossOrigin
    @PostMapping("people/add")
    public ResponseEntity<Void> addPerson(@RequestBody Person person){
        Person added = peopleService.addPerson(person);
        if(added != null){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @CrossOrigin
    @PutMapping("people/update")
    public ResponseEntity<Void> updatePerson(@RequestBody Person person){
        Person updated = peopleService.updatePerson(person);
        if(updated != null){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @CrossOrigin
    @GetMapping("people/search/{name}")
    public ResponseEntity<List<Person>> getPeopleByName(@PathVariable("name") String name){
        return ResponseEntity.status(HttpStatus.OK).body(peopleService.getPersonByFullNameIgnoreCase(name));
    }

    @CrossOrigin
    @DeleteMapping("people/delete/all")
    public ResponseEntity<Void> deleteAll(){
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
