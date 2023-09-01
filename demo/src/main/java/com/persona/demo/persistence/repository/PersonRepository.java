package com.persona.demo.persistence.repository;
import com.persona.demo.Domain.Person;
import com.persona.demo.persistence.entities.PersonEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PersonRepository {

    public List<Person> getAll();

    public List<Person> getByGender(String gender);

    public List<Person> getByEdad(Integer years);

    public List<Person> getByMayores(Integer years);

    public List<Person> getByName(String name);

    Optional<PersonEntity> findById(Integer cc);

    Integer update(Person person,Integer cc);

    Integer save(Person person, Integer cc, String name, LocalDate bornDate);

}
