package com.abhinav.springboottut.dao;

import com.abhinav.springboottut.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class PersonServiceAccess implements PersonDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonServiceAccess(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertPerson(UUID id, Person person) {
        return 0;
    }

    @Override
    public List<Person> getPersons() {
        return jdbcTemplate.query("SELECT * FROM person", (ResultSet, i) -> {
            return new Person(
                    UUID.fromString(ResultSet.getString("id")),
                    ResultSet.getString("name")
            );
        });
    }

    @Override
    public Optional<Person> selectPersonByUID(UUID id) {
        Person person = jdbcTemplate.queryForObject(
                "SELECT * FROM person WHERE id = ?",
                new Object[] {id},
                (ResultSet, i) -> {
                    return new Person(
                            UUID.fromString(ResultSet.getString("id")),
                            ResultSet.getString("name")
                    );
                }
        );
        return Optional.ofNullable(person);
    }

    @Override
    public int deletePerson(UUID id) {
        return 0;
    }

    @Override
    public int updatePerson(UUID ip, Person person) {
        return 0;
    }
}
