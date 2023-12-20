package org.cs320.ozyegin.model.DataLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.lang.String;

public class DbIntegration
{
    protected Connection connection;
    public DbIntegration(String dbName) throws ClassNotFoundException{
        Class.forName("org.sqlite.JDBC");
        connection = null;
        try
        {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:%s.db".formatted(dbName));
        }
        catch(SQLException e)
        {
            //If database is not found.
            System.err.println(e.getMessage());
        }
    }
}