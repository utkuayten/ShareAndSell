package org.cs320.ozyegin.model.DataLayer;

import java.sql.SQLException;
import java.sql.Statement;

public class Transactions extends DbIntegration {
    public Transactions() throws ClassNotFoundException, SQLException {
        super("Transactions");
        Statement statement = connection.createStatement();
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS Transactions (" +
                "    id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "    seller_id INTEGER," +
                "    buyer_id INTEGER," +
                "    price FLOAT," +
                "    quantity INTEGER," +
                "    advert_id INTEGER" +
                ");");
        this.connection.close();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Transactions dbTransactions = new Transactions();
    }
}