package fr.ensibs.orders.dao;


import fr.ensibs.orders.model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * OrderDAO class.
 *
 * @author Yoann Le Dréan
 * @version 1.0
 */
public class OrderDAO {

    /**
     * the connection instance.
     */
    private Connection connection;

    /**
     * Constructor.
     */
    public OrderDAO() {

        this.connection = ConnectionSQLite.getConnection();
    }


    /**
     * This method allows you to add an order in the database.
     *
     * @param order the order to add.
     * @return true if the order is correctly added
     * @throws Exception throw an exception if the order already exists
     */
    public boolean addOrder(Order order) throws SQLException {
        String query = "INSERT INTO OOrder(login_user,price,isPaid) VALUES (?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, order.getUserName());
        preparedStatement.setFloat(2, order.getPrice());
        preparedStatement.setBoolean(3, order.isPaid());
        int res = preparedStatement.executeUpdate();
        return res == 1;
    }

    /**
     * This method allows you to get a list of orders from an username
     *
     * @param userName    the login of the user
     * @return The list of orders of the given user
     * @throws SQLException throw an SQLException if an exception was occurred
     */
    public List<Order> getOrders(String userName) throws SQLException {
        String query = "SELECT id, login_user, price, isPaid FROM OOrder WHERE login_user = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, userName);
        ResultSet res = preparedStatement.executeQuery();

        List<Order> orders = new ArrayList<>();
        while (res.next()) {
            orders.add(new Order(
                            res.getInt("id"),
                            res.getString("login_user"),
                            res.getFloat("price"),
                            res.getBoolean("isPaid")
                    )
            );
        }

        return orders;

    }

    /**
     * This method allows you to get all orders in the database.
     *
     * @return the list of orders
     * @throws SQLException throw an SQLException if an exception was occurred
     */
    public List<Order> getAllOrders() throws SQLException {
        String query = "SELECT id, login_user, price, isPaid FROM OOrder";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet res = preparedStatement.executeQuery();

        List<Order> orders = new ArrayList<>();
        while (res.next()) {
            orders.add(new Order(
                            res.getInt("id"),
                            res.getString("login_user"),
                            res.getFloat("price"),
                            res.getBoolean("isPaid")
                    )
            );
        }

        return orders;

    }

    /**
     * This method is used to set an order to the paid state
     *
     * @param id the id of the order to set paid
     */
    public void setPaid(int id) {
        try {
            String query = "UPDATE OOrder set isPaid = true WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, String.valueOf(id));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
