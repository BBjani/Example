package com.myapp.db;

import com.myapp.services.DuplicateException;
import com.myapp.services.InfrastructureException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MysqlService implements DbService {

    private static MysqlService INSTANCE;
    private static final int DUPLICATE_ERROR_CODE = 1062;
    private DataSource datasource;

    private MysqlService() {
        try {
            final Context initContext = new InitialContext();
            final Context envContext = (Context) initContext.lookup("java:/comp/env");
            datasource = (DataSource) envContext.lookup("jdbc/personal");
        } catch (NamingException se) {
            throw new IllegalStateException(se);
        }
    }

    public static synchronized MysqlService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MysqlService();
        }
        return INSTANCE;
    }

    @Override
    public Connection getConnection() throws InfrastructureException {
        try {
            return datasource.getConnection();
        } catch (SQLException se) {
            throw new InfrastructureException("unable to get DB connection", se);
        }
    }

    @Override
    public ResultSet query(final PreparedStatement stmt) throws InfrastructureException {
        try {
            return stmt.executeQuery();
        } catch (SQLException se) {
            throw new InfrastructureException("unable to execute query", se);
        }
    }

    @Override
    public void insertRow(final PreparedStatement stmt) throws InfrastructureException, DuplicateException {
        try {
            stmt.executeUpdate();
        } catch (SQLException se) {
            if (se.getErrorCode() == DUPLICATE_ERROR_CODE) {
                throw new DuplicateException("email already exists", se);
            } else {
                throw new InfrastructureException("unable to insert", se);
            }
        }
    }

    @Override
    public void deleteRow(PreparedStatement stmt) throws InfrastructureException {
        try {
            stmt.executeUpdate();
        } catch (SQLException se) {
            throw new InfrastructureException("unable to delete", se);
        }
    }
}
