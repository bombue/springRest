package com.example.springREST.services;

import com.example.springREST.models.Person;
import com.example.springREST.repositories.PeopleRepository;
import com.example.springREST.util.PersonNotFoundException;
import org.hibernate.Hibernate;
import org.hibernate.PersistentObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }


    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findOne(int id) {
        return peopleRepository.findById(id).orElseThrow(PersonNotFoundException::new);
    }

    @Transactional
    public void create(Person person) {
        enrichPerson(person);
        peopleRepository.save(person);
    }

//    @Transactional
//    public void update(int id, Person person) {
//        person.setId(id);
//        peopleRepository.save(person);
//    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }

    public void enrichPerson(Person person) {
        person.setCreatedAt(LocalDateTime.now());
        person.setUpdatedAt(LocalDateTime.now());
        person.setCreatedWho("akiselev");
    }

}
