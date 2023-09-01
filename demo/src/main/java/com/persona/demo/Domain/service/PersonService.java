package com.persona.demo.Domain.service;
import com.persona.demo.Domain.Person;
import com.persona.demo.persistence.entities.PersonEntity;
import com.persona.demo.persistence.repository.PersonRepository;
import com.persona.demo.persistence.repositoryimpl.PersonRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import org.springframework.context.MessageSource;


@Service
public class PersonService implements PersonRepository {

    private MessageSource messageSource;

    public PersonService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    private final static String[] genderList = {"F","M"};

    PersonRepositoryImpl personRepository;
    @Autowired
    public PersonService(PersonRepositoryImpl personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> getAll(){
        return personRepository.getAll();
    }

    public List<Person> getByGender(String gender){
        if (!Arrays.stream(genderList).anyMatch(gend ->gend.equals(gender))) {
            throw new RuntimeException("El género no es válido");
        }
        return personRepository.getByGender(gender);
    }

    public List<Person> getByEdad(Integer years){
        if (years < 1) {
            throw new IllegalArgumentException("La edad debe ser mayor a 1");
        }
        List<Person> personAge = personRepository.getByEdad(years);
        if (personAge.isEmpty()){
            throw  new IllegalArgumentException("No hay personas de esa edad");
        }
        return personRepository.getByEdad(years);
    }

    @Override
    public List<Person> getByMayores(Integer years) {
        return personRepository.getByMayores(years);
    }



    @Override
    public List<Person> getByName(String name){
        if(name == null || name.isEmpty()){
            throw new IllegalArgumentException(messageSource.getMessage("validation.empty.name",null, Locale.getDefault()));
        }
        if(!name.matches("[A-Ca-c].*")){
            throw new IllegalArgumentException("La primera letra debe ser A,B o C (mayúsculas o minúsculas");
        }

        List<Person> namesABC = personRepository.getByName(name.toLowerCase());

        /*if(namesABC.isEmpty()){
            throw new IllegalArgumentException("No hay personas que comiencen por la letra " +name+ ".");
        }*/
        return namesABC;

    }

    @Override
    public Optional<PersonEntity> findById(Integer cc) {
        return Optional.empty();
    }

    @Override
    public Integer update(Person person, Integer cc){
        Optional<PersonEntity> personExist = personRepository.findById(cc);
        if (personExist.isEmpty()){
            throw new IllegalArgumentException("No se puede actualizar, cedula no existe");
        }
        return  personRepository.update(person,cc);
    }

    @Override
    public Integer save(Person person, Integer cc, String name, LocalDate bornDate){
        if (person.getCc() == null){
            throw new IllegalArgumentException("la cédula no puede estar vacia");
        }

        Optional<PersonEntity> personExist = personRepository.findById(cc);
        if (personExist.isPresent()){
            throw new IllegalArgumentException("La cédula ya existe");
        }

        String nameSpaces = name.trim();

        if (!nameSpaces.isEmpty()){
            Integer age = calcularEdad(person.getBornDate());
            person.setYears(age);

            return personRepository.save(person,cc,name.trim(),bornDate);
        } else {
            throw new IllegalArgumentException("El nombre no puede estar vacio");
        }
   }
    private int calcularEdad(LocalDate bornDate) {
        LocalDate fechaActual = LocalDate.now();
        int edad = fechaActual.getYear() - bornDate.getYear();

        if (bornDate.getMonthValue() > fechaActual.getMonthValue() ||
                (bornDate.getMonthValue() == fechaActual.getMonthValue() &&
                        bornDate.getDayOfMonth() > fechaActual.getDayOfMonth())) {
            edad--;
        }
        return edad;
    }
}

