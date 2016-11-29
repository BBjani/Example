package com.myapp.db;

import com.myapp.services.DuplicateException;
import com.myapp.services.InfrastructureException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface DbService {

    Connection getConnection() throws InfrastructureException;

    ResultSet query(PreparedStatement stmt) throws InfrastructureException;

    void insertRow(PreparedStatement stmt) throws InfrastructureException, DuplicateException;

    void deleteRow(PreparedStatement stmt) throws InfrastructureException;
}
