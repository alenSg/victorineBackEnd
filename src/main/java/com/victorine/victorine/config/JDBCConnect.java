package com.victorine.victorine.config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnect {
    public Connection getConnection() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        Connection connection = null;
        String connectionURL = "jdbc:postgresql://localhost:5432/VictorineDB";
        Class.forName("org.postgresql.Driver").newInstance();
        connection = DriverManager.getConnection(connectionURL, "alen", "2783200");
        return connection;
    }
}
