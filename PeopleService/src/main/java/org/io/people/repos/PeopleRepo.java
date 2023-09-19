package org.io.people.repos;

import org.io.people.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeopleRepo extends JpaRepository<Person, Integer> {
}
