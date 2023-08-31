package com.persona.demo.controller;
import com.persona.demo.Domain.Person;
import com.persona.demo.Domain.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.List;

@RestController
public class PersonController {

    PersonService personService;
    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(value = "/traer-todo")
    public List<Person> getAll(){
        return personService.getAll();
    }

    @GetMapping(value = "/genero/{gender}")
    public List<Person> getByGender(@PathVariable("gender") String gender){
        return personService.getByGender(gender);
    }

    @GetMapping(value = "/edad/{years}")
    public  List<Person> getByEdad(@PathVariable("years") Integer years){
        return personService.getByEdad(years);
    }

    @GetMapping(value = "/mayor-edad")
    public ResponseEntity<List<Person>> getByMayores() {
        List<Person> adultList = personService.getByMayores(18);
        return new ResponseEntity<>(adultList, HttpStatus.OK);
    }

    @GetMapping(value = "/traer-nombre/{name}")
    public List<Person> getByName(@PathVariable("name")String name) {
        return (List<Person>) personService.getByName(name);}

     @PutMapping(value = "/actualizar")
    public Integer update (@RequestBody Person person){
        return personService.update(person, person.getCc());
    }

    @PostMapping(value = "/guardar")
    public Integer save(@RequestBody Person person) {
        return personService.save(person, person.getCc(), person.getName(),
            person.getBornDate());}

}
