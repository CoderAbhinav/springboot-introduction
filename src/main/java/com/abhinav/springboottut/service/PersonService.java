package com.abhinav.springboottut.service;

import com.abhinav.springboottut.dao.PersonDao;
import com.abhinav.springboottut.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {

    static private PersonDao personDao;

    @Autowired
    public PersonService(@Qualifier("postgres") PersonDao personDao) {
        if (this.personDao == null) {
            this.personDao = personDao;
        }
    }

    public int addPerson(Person person) {
        return personDao.insertPerson(person);
    }

    public int updatePerson(UUID id, Person person) {
        return personDao.updatePerson(id, person);
    }

    public int deletePerson(UUID id) {
        return personDao.deletePerson(id);
    }

    public List<Person> getPerson() {
        return personDao.getPersons();
    }

    public Optional<Person> getPersonByID(UUID id) {
        return personDao.selectPersonByUID(id);
    }


}
