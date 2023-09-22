package org.io.people.services;

import org.io.people.entities.Person;
import org.io.people.repos.PeopleRepo;
import org.io.people.validators.Validator;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class PeopleService {
    @Autowired
    private PeopleRepo peopleRepo;
    @Autowired
    private Validator<Person> personValidator;

    public List<Person> getPeople(){
        return peopleRepo.findAll();
    }
    public Person getPersonById(int id){
        Optional<Person> person = peopleRepo.findById(id);
        if(person.isPresent()){
            return person.get();
        }
        return null;//bad
    }

    public void deletePersonById(int id){
        peopleRepo.deleteById(id);
    }

    public Person addPerson(Person person){
        if(personValidator.validate(person)) {
            return peopleRepo.save(person);
        }
        return null;
    }

    public Person updatePerson(Person person){
        if(personValidator.validate(person)) {
            return peopleRepo.save(person);
        }
        return null;
    }
    public List<Person> getPersonByFullNameIgnoreCase(String fullName){
        List<Person> people = peopleRepo.findByFullNameContainingIgnoreCase(fullName);
        return people;
    }
}
