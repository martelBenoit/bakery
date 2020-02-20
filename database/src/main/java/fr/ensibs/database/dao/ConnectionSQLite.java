package fr.ensibs.database.dao;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionSQLite {

    private static Connection connection = null;
    private Statement statement = null;

    private ConnectionSQLite() {

        URL url = ConnectionSQLite.class.getResource("/bdd.db");
        String urlString = url.getPath();

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + urlString);
            statement = connection.createStatement();
            System.out.println("Connection at " + urlString + " successful");
        } catch (ClassNotFoundException | SQLException notFoundException) {
            notFoundException.printStackTrace();
        }

    }

    public void close() {
        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        if (connection == null) {
            new ConnectionSQLite();
        }
        return connection;
    }

}
