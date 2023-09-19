package org.io.people.repos;

import org.io.people.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PeopleRepo extends JpaRepository<Person, Integer> {
    List<Person> findByFullNameContainingIgnoreCase(String toSearchFor);
}
