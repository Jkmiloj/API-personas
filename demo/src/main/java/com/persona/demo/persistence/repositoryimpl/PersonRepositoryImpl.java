package com.persona.demo.persistence.repositoryimpl;
import com.persona.demo.Domain.Person;
import com.persona.demo.persistence.entities.PersonEntity;
import com.persona.demo.persistence.repository.CrudRepository;
import com.persona.demo.persistence.repository.PersonRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PersonRepositoryImpl implements PersonRepository {

    CrudRepository crudRepository;

    public PersonRepositoryImpl(CrudRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    @Override
    public List<Person> getAll(){

        List<Person> personList = new ArrayList<>();

        List<PersonEntity> personEntityList = crudRepository.findAll();

        personEntityList.forEach(personEntity -> {
            Person person = new Person(
                personEntity.getCc(),
                personEntity.getName(),
                personEntity.getLastname(),
                personEntity.getGender(),
                personEntity.getBornDate(),
                personEntity.getYears(),
                personEntity.getCellphone()
            );
            personList.add(person);
        });
        return personList;
    }

    @Override
    public List<Person> getByGender(String gender){

        List<Person> personList = new ArrayList<>();

        List<PersonEntity> personEntityList = crudRepository.getByGender(String.valueOf(gender));

       personEntityList.forEach(personEntity -> {
           Person person = new Person(
                    personEntity.getCc(),
                    personEntity.getName(),
                    personEntity.getLastname(),
                    personEntity.getGender(),
                    personEntity.getBornDate(),
                    personEntity.getYears(),
                    personEntity.getCellphone()
            );
            personList.add(person);
        });
        return personList;
    }

    @Override
    public List<Person> getByEdad(Integer years){
        List<Person> personList = new ArrayList<>();
        List<PersonEntity> personEntityList = (List<PersonEntity>) crudRepository.getByEdad(years);

        personEntityList.forEach(personEntity -> {
            Person person = new Person(
                    personEntity.getCc(),
                    personEntity.getName(),
                    personEntity.getLastname(),
                    personEntity.getGender(),
                    personEntity.getBornDate(),
                    personEntity.getYears(),
                    personEntity.getCellphone()
            );
            personList.add(person);
        });
        return personList;
    }

    /*@Override
    public List<Person> getByMayores() {
        return null;
    }*/

    @Override
    public List<Person> getByMayores(Integer years){

            List<Person> personList = new ArrayList<>();
            List<PersonEntity> personEntityList = (List<PersonEntity>) crudRepository.getByMayores(years);

            personEntityList.forEach(personEntity -> {
                Person person = new Person(
                        personEntity.getCc(),
                        personEntity.getName(),
                        personEntity.getLastname(),
                        personEntity.getGender(),
                        personEntity.getBornDate(),
                        personEntity.getYears(),
                        personEntity.getCellphone()
                );
                personList.add(person);
            });
            return personList;
    }

    @Override
    public List<Person> getByName(String name){
        List<Person> personList = new ArrayList<>();

        List<PersonEntity> personEntityList = crudRepository.getByName(name);

        personEntityList.forEach(personEntity -> {
            Person person = new Person(
                    personEntity.getCc(),
                    personEntity.getName(),
                    personEntity.getLastname(),
                    personEntity.getGender(),
                    personEntity.getBornDate(),
                    personEntity.getYears(),
                    personEntity.getCellphone()
            );
            personList.add(person);
        });
        return personList;
    }

    @Override
    public Optional<PersonEntity> findById(Integer cc){
        return crudRepository.findById(cc);
    }

    @Override
    public Integer update(Person person,Integer cc){
        PersonEntity personEntity = new PersonEntity(
                person.getCc(),
                person.getName(),
                person.getLastname(),
                person.getGender(),
                person.getBornDate(),
                person.getYears(),
                person.getCellphone()
        );
        return crudRepository.save(personEntity).getCc();
    }

    @Override
    public Integer save(Person person, Integer cc, String name, LocalDate bornDate){
        PersonEntity personEntity = new PersonEntity(
                cc,
                name,
                person.getLastname(),
                person.getGender(),
                bornDate,
                person.getYears(),
                person.getCellphone()
        );
        return crudRepository.save(personEntity).getCc();
    }
}