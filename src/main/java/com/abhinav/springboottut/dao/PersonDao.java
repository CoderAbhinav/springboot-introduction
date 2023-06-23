package com.abhinav.springboottut.dao;

import com.abhinav.springboottut.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDao {
    int insertPerson(UUID id, Person person);

    default int insertPerson(Person person) {
        UUID id = UUID.randomUUID();
        return insertPerson(id, person);
    }

    List<Person> getPersons();

    Optional<Person> selectPersonByUID(UUID id);

    int deletePerson(UUID id);
    int updatePerson(UUID ip, Person person);
}
