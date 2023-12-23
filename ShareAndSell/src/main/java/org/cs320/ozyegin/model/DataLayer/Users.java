package org.cs320.ozyegin.model.DataLayer;


import java.sql.SQLException;
import java.sql.Statement;

public class Users extends DbIntegration{
    public Users() throws ClassNotFoundException, SQLException {
        super("Users");
        Statement statement = connection.createStatement();
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS users (" +
                                "    id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                "    name VARCHAR[64]," +
                                "    mail VARCHAR[64]," +
                                "    password VARCHAR[64],"+
                                "    role text"+
                    ");");
        this.connection.close();
    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Users dbUsers = new Users();
    }
}
