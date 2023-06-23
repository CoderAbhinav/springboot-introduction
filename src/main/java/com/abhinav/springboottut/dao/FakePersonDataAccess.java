package com.abhinav.springboottut.dao;

import com.abhinav.springboottut.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fake-dao")
public class FakePersonDataAccess implements PersonDao{
    private List<Person> DB = new ArrayList<>();

    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id, person.getName()));
        return 1;
    }

    @Override
    public List<Person> getPersons() {
        return DB;
    }

    @Override
    public Optional<Person> selectPersonByUID(UUID id) {
        return DB.stream().filter(person -> {
            return person.getId().equals(id);
        }).findFirst();
    }

    @Override
    public int deletePerson(UUID id) {
        Optional<Person> person = selectPersonByUID(id);

        if (person.isEmpty()) {
             return 0;
        }

        DB.remove(person.get());

        return 1;
    }

    @Override
    public int updatePerson(UUID id, Person person) {
        return selectPersonByUID(id).map(p -> {
            int indexOfPInDB = DB.indexOf(p);
            if (indexOfPInDB >= 0) DB.set(indexOfPInDB, person);
            return 1;
        }).orElse(0);
    }
}
