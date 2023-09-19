package org.io.people.services;

import org.io.people.entities.Person;
import org.io.people.repos.PeopleRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class PeopleService {
    @Autowired
    private PeopleRepo peopleRepo;

    public Person getPersonById(int id){
        Optional<Person> person = peopleRepo.findById(id);
        if(person.isPresent()){
            return person.get();
        }
        return null;//bad
    }
}
