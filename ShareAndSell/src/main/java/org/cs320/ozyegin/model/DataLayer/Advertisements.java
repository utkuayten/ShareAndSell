package org.cs320.ozyegin.model.DataLayer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Advertisements extends DbIntegration{
    public Advertisements() throws ClassNotFoundException, SQLException {
        super("Advertisements");
        Statement statement = connection.createStatement();
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS Advertisements (" +
                                "    id INTEGER PRIMARY KEY AUTOINCREMENT  ," +
                                "    owner_id INTEGER," +
                                "    advert_name VARCHAR[64]," +
                                "    advert_date date," +
                                "    quantity INTEGER" +
                                ");");
        this.connection.close();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Advertisements dbAdvertisements = new Advertisements();
    }
}
