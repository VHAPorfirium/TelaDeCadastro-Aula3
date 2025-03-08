package br.com.aula3.aula3.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private ConnectionFactory() {};
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    "jdbc:postgresql://localhost/poo_bd",
                    "postgres",
                    "**********"
            );
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}