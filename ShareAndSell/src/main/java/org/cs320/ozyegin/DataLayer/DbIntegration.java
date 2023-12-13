package org.cs320.ozyegin.DataLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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