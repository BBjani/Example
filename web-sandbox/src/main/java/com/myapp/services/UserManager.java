package com.myapp.services;

import com.myapp.model.Person;
import java.util.List;

/**
 *
 *
 */
public interface UserManager {

    /**
     *
     * @param person
     * @throws ValidationException
     * @throws InfrastructureException
     * @throws DuplicateException
     */
    void createPerson(final Person person) throws ValidationException, InfrastructureException, DuplicateException;

    /**
     *
     * @return
     * @throws InfrastructureException
     */
    List<Person> getPeople() throws InfrastructureException;

    /**
     *
     * @param id
     * @return
     * @throws InfrastructureException
     */
    Person getPersonById(final Integer id) throws InfrastructureException;

    /**
     *
     * @param person
     * @throws ValidationException
     * @throws InfrastructureException
     * @throws DuplicateException
     */
    void updatePerson(final Person person) throws ValidationException, InfrastructureException, DuplicateException;

    /**
     *
     * @param id
     * @throws InfrastructureException
     */
    void deletePerson(final Integer id) throws InfrastructureException;
}
