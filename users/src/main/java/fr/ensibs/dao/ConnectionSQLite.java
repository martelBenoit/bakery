package fr.ensibs.dao;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionSQLite {

    private static Connection connection = null;
    private Statement statement = null;

    private String url = "bdd.db";

    private ConnectionSQLite() {

        URL url = ConnectionSQLite.class.getResource("/bdd.db");
        this.url = url.getPath()+"\\"+url.getFile();

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + url);
            statement = connection.createStatement();
            System.out.println("Connection at " + url + " successful");
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
