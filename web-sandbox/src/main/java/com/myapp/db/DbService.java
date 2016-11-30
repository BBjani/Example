package com.myapp.db;

import com.myapp.services.DuplicateException;
import com.myapp.services.InfrastructureException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 *
 */
public interface DbService {

    /**
     *
     * @return
     * @throws InfrastructureException
     */
    Connection getConnection() throws InfrastructureException;

    /**
     *
     * @param stmt
     * @return
     * @throws InfrastructureException
     */
    ResultSet query(final PreparedStatement stmt) throws InfrastructureException;

    /**
     *
     * @param stmt
     * @throws InfrastructureException
     * @throws DuplicateException
     */
    void insertRow(final PreparedStatement stmt) throws InfrastructureException, DuplicateException;

    /**
     *
     * @param stmt
     * @throws InfrastructureException
     */
    void deleteRow(final PreparedStatement stmt) throws InfrastructureException;
}
