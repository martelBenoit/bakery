package fr.ensibs.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * ConnectionSQlite class.
 * This class allows you to connect at the database embedded
 *
 * @author Yoann Le Dréan
 * @author Benoît Martel
 * @author Loïc Travaillé
 * @version 1.0
 */
public class ConnectionSQLite {

    /**
     * the connection instance
     */
    private static Connection connection = null;

    /**
     * the url for the database
     */
    private static String url = ConnectionSQLite.class.getResource("/bdd_orders.db").getPath();

    /**
     * Private constructor.
     */
    private ConnectionSQLite() {

    }

    /**
     * This method allows you to connect at the database.
     */
    private static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + url);
            System.out.println("Connection at " + url + " successful");
        } catch (ClassNotFoundException | SQLException notFoundException) {
            notFoundException.printStackTrace();
        }
    }

    /**
     * This method allows you to close the connection to the database.
     */
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method allows you to get the connection to the database.
     *
     * @return the connection
     */
    public static Connection getConnection() {
        if (connection == null) {
            connect();
        }
        return connection;
    }

}
