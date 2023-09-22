package org.io.people.controllers;

import org.io.people.entities.Address;
import org.io.people.entities.Mail;
import org.io.people.entities.Person;
import org.io.people.messages.Message;
import org.io.people.services.PeopleService;
import org.io.people.tdo.AddressTDO;
import org.io.people.tdo.MailTDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Message> deletePersonById(@PathVariable("id") int id){
        Person person = peopleService.getPersonById(id);
        peopleService.deletePersonById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Message("Person "+person.getFullName()+" was deleted successfully"));
    }

    @CrossOrigin
    @PostMapping("people/add")
    public ResponseEntity<Message> addPerson(@RequestBody Person person){
        Person added = peopleService.addPerson(person);
        if(added != null){
            return ResponseEntity.status(HttpStatus.OK).body(new Message("Person "+added.getFullName()+" was inserted successfully!"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @CrossOrigin
    @PutMapping("people/update")
    public ResponseEntity<Message> updatePerson(@RequestBody Person person){
        Person updated = peopleService.updatePerson(person);
        if(updated != null){
            return ResponseEntity.status(HttpStatus.OK).body(new Message("Person "+updated.getFullName()+" was updated successfully"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @CrossOrigin
    @PostMapping("addresses/add")
    public ResponseEntity<Message> addAddress(@RequestBody AddressTDO addressTDO){
        Person person = peopleService.getPersonById(addressTDO.getPersonId());
        Address address = new Address();
        address.setInfo(addressTDO.getInfo());
        address.setType(addressTDO.getType());
        address.setPerson(person);
        person.getAddress().add(address);
        Person updated = peopleService.updatePerson(person);
        if(updated != null){
            return ResponseEntity.status(HttpStatus.OK).body(new Message("Address was added successfully"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @CrossOrigin
    @PostMapping("emails/add")
    public ResponseEntity<Message> addEmail(@RequestBody MailTDO mailTDO){
        Person person = peopleService.getPersonById(mailTDO.getPersonId());
        Mail mail = new Mail();
        mail.setEmail(mailTDO.getEmail());
        mail.setType(mailTDO.getType());
        mail.setPerson(person);
        person.getEmail().add(mail);
        Person updated = peopleService.updatePerson(person);
        if(updated != null){
            return ResponseEntity.status(HttpStatus.OK).body(new Message("Email was added successfully"));
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
    public ResponseEntity<Message> deleteAll(){
        return ResponseEntity.status(HttpStatus.OK).body(new Message("All people were deleted successfully"));
    }
}
