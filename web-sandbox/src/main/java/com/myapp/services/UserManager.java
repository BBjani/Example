package com.myapp.services;

import com.myapp.model.Person;
import java.util.List;

public interface UserManager {

    void createPerson(Person person) throws ValidationException, InfrastructureException, DuplicateException;

    List<Person> getPeople() throws InfrastructureException;

    Person getPersonById(Integer id) throws InfrastructureException;

    void updatePerson(Person person) throws ValidationException, InfrastructureException, DuplicateException;

    void deletePerson(Integer id) throws InfrastructureException;
}
