package org.cs320.ozyegin.DataLayer;

import java.sql.SQLException;
import java.sql.Statement;

public class Wallets extends DbIntegration{
    public Wallets() throws ClassNotFoundException, SQLException {
        super("Wallets");
        Statement statement = connection.createStatement();
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS Wallets (" +
                                "    id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                "    owner VARCHAR[64]," +
                                "    balance VARCHAR[64]" +
                                ");");
        this.connection.close();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Wallets dbWallets = new Wallets();
    }
}
