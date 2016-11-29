package com.myapp.services;

import com.myapp.db.MysqlService;
import com.myapp.model.Person;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserManagerService implements UserManager {

    private static UserManagerService INSTANCE;
    private final MysqlService mysqlService;

    private UserManagerService() {
        this.mysqlService = MysqlService.getInstance();
    }

    public static synchronized UserManagerService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserManagerService();
        }
        return INSTANCE;
    }

    private void isValidUser(Person person) throws ValidationException {
        if (null == person.getName() || null == person.getEmail() || null == person.getAddress().getCountry()
                || null == person.getAddress().getPostalcode() || null == person.getAddress().getCity() || null == person.getAddress().getStreet()) {
            throw new ValidationException("Name, email and address are required", new ValidationExceptionBean(person.toString()));
        } else if (1 > person.getName().length()) {
            throw new ValidationException("Name is too short", new ValidationExceptionBean(person.getName()));
        } else if (25 < person.getName().length()) {
            throw new ValidationException("Name is too long", new ValidationExceptionBean(person.getName()));
        } else if (1 > person.getEmail().length()) {
            throw new ValidationException("Email is too short", new ValidationExceptionBean(person.getEmail()));
        } else if (25 < person.getEmail().length()) {
            throw new ValidationException("Email is too long", new ValidationExceptionBean(person.getEmail()));
        } else if (1 > person.getAddress().getCountry().length()) {
            throw new ValidationException("Name of country is too short", new ValidationExceptionBean(person.getAddress().getCountry()));
        } else if (25 < person.getAddress().getCountry().length()) {
            throw new ValidationException("Name of country is too long", new ValidationExceptionBean(person.getAddress().getCountry()));
        } else if (1000 > person.getAddress().getPostalcode() || 9999 < person.getAddress().getPostalcode()) {
            throw new ValidationException("Invalid Postalcode", new ValidationExceptionBean(person.getAddress().getPostalcode()));
        } else if (1 > person.getAddress().getCity().length()) {
            throw new ValidationException("City is too short", new ValidationExceptionBean(person.getAddress().getCity()));
        } else if (25 < person.getAddress().getCity().length()) {
            throw new ValidationException("City is too long", new ValidationExceptionBean(person.getAddress().getCity()));
        } else if (1 > person.getAddress().getStreet().length()) {
            throw new ValidationException("Street is too short", new ValidationExceptionBean(person.getAddress().getStreet()));
        } else if (25 < person.getAddress().getStreet().length()) {
            throw new ValidationException("Street is too long", new ValidationExceptionBean(person.getAddress().getStreet()));
        }
    }

    @Override
    public void createPerson(Person person) throws ValidationException, InfrastructureException {
        try {
            isValidUser(person);
            PreparedStatement stmt;
            String insert = "insert into address (Country, Postalcode, City, Street) values (?,?,?,?);";
            stmt = mysqlService.getConnection().prepareStatement(insert);
            stmt.setString(1, person.getAddress().getCountry());
            stmt.setInt(2, person.getAddress().getPostalcode());
            stmt.setString(3, person.getAddress().getCity());
            stmt.setString(4, person.getAddress().getStreet());
            mysqlService.insertRow(stmt);
            final String select = "select A_Id from address where Country = ? and "
                    + "Postalcode = ? and City = ? and Street = ?;";
            stmt = mysqlService.getConnection().prepareStatement(select);
            stmt.setString(1, person.getAddress().getCountry());
            stmt.setInt(2, person.getAddress().getPostalcode());
            stmt.setString(3, person.getAddress().getCity());
            stmt.setString(4, person.getAddress().getStreet());
            Integer id = null;
            final ResultSet rs = mysqlService.query(stmt);
            while (rs.next()) {
                id = rs.getInt("A_Id");
            }
            insert = "insert into users (A_Id, Name, Email) values (?,?,?);";
            stmt = mysqlService.getConnection().prepareStatement(insert);
            stmt.setInt(1, id);
            stmt.setString(2, person.getName());
            stmt.setString(3, person.getEmail());
            mysqlService.insertRow(stmt);
            if (log.isDebugEnabled()) {
                log.debug(person.getName() + " (" + person.getId() + ") " + "created");
            }
        } catch (DuplicateException ex) {
            throw new ValidationException("Email already exists", new ValidationExceptionBean(person.getEmail()), ex);
        } catch (SQLException se) {
            throw new InfrastructureException(se.getMessage(), se);
        }
    }

    private Person newPerson() {
        return new Person();
    }

    @Override
    public List<Person> getPeople() throws InfrastructureException {
        List<Person> people;
        try {
            PreparedStatement stmt;
            final String select = "select u.U_Id, u.Name, u.Email, "
                    + "a.Country, a.Postalcode, a.City, a.Street from users u, "
                    + "address a where u.A_Id = a.A_ID;";
            stmt = mysqlService.getConnection().prepareStatement(select);
            final ResultSet rs = mysqlService.query(stmt);
            people = new ArrayList();
            while (rs.next()) {
                final Person person = newPerson();
                person.setId(rs.getInt("U_Id"));
                person.setName(rs.getString("Name"));
                person.setEmail(rs.getString("Email"));
                person.getAddress().setCountry(rs.getString("Country"));
                person.getAddress().setPostalcode(rs.getInt("Postalcode"));
                person.getAddress().setCity(rs.getString("City"));
                person.getAddress().setStreet(rs.getString("Street"));
                people.add(person);
            }
            return people;
        } catch (SQLException se) {
            throw new InfrastructureException(se.getMessage(), se);
        }
    }

    @Override
    public Person getPersonById(Integer id) throws InfrastructureException {
        Person person;
        try {
            PreparedStatement stmt;
            final String select = "select u.U_Id, u.Name, u.Email, a.Country, a.Postalcode, a.City, a.Street "
                    + "from users u, address a where u.A_Id = a.A_ID and u.U_Id = ?;";
            stmt = mysqlService.getConnection().prepareStatement(select);
            stmt.setInt(1, id);
            final ResultSet rs = mysqlService.query(stmt);
            person = new Person();
            while (rs.next()) {
                person.setId(rs.getInt("U_Id"));
                person.setName(rs.getString("Name"));
                person.setEmail(rs.getString("Email"));
                person.getAddress().setCountry(rs.getString("Country"));
                person.getAddress().setPostalcode(rs.getInt("Postalcode"));
                person.getAddress().setCity(rs.getString("City"));
                person.getAddress().setStreet(rs.getString("Street"));
            }
            return person;
        } catch (SQLException se) {
            throw new InfrastructureException(se.getMessage(), se);
        }
    }

    @Override
    public void updatePerson(Person person) throws ValidationException, InfrastructureException {
        isValidUser(person);
        try {
            PreparedStatement stmt;
            final String update = "update users, address set users.Name = ?, users.Email = ?,"
                    + "address.Country = ?, address.Postalcode = ?, address.City = ?, address.Street = ?"
                    + "where users.U_Id = ? and users.A_Id = address.A_Id;";
            stmt = mysqlService.getConnection().prepareStatement(update);
            stmt.setString(1, person.getName());
            stmt.setString(2, person.getEmail());
            stmt.setString(3, person.getAddress().getCountry());
            stmt.setInt(4, person.getAddress().getPostalcode());
            stmt.setString(5, person.getAddress().getCity());
            stmt.setString(6, person.getAddress().getStreet());
            stmt.setInt(7, person.getId());
            mysqlService.insertRow(stmt);
            if (log.isDebugEnabled()) {
                log.info("(" + person.getId() + ") " + "updated");
            }
        } catch (DuplicateException ex) {
            throw new ValidationException("Email already exists", new ValidationExceptionBean(person.getEmail()), ex);
        } catch (SQLException se) {
            throw new InfrastructureException(se.getMessage(), se);
        }
    }

    @Override
    public void deletePerson(Integer id) throws InfrastructureException {
        try {
            PreparedStatement stmt;
            String delete = "delete from users where U_Id = ?;";
            stmt = mysqlService.getConnection().prepareStatement(delete);
            stmt.setInt(1, id);
            mysqlService.deleteRow(stmt);
            delete = "delete from address where A_Id = ?;";
            stmt = mysqlService.getConnection().prepareStatement(delete);
            stmt.setInt(1, id);
            mysqlService.deleteRow(stmt);
        } catch (SQLException se) {
            throw new InfrastructureException(se.getMessage(), se);
        }
    }
}
