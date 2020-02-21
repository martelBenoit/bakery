package fr.ensibs.orders.dao;


import fr.ensibs.orders.model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    private Connection connection;

    public OrderDAO() {

        this.connection = ConnectionSQLite.getConnection();
    }


    public boolean addOrder(Order order) throws SQLException {
        String query = "INSERT INTO OOrder(login_user,price,isPaid) VALUES (?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, order.getUserName());
        preparedStatement.setFloat(2, order.getPrice());
        preparedStatement.setBoolean(3, order.isPaid());
        int res = preparedStatement.executeUpdate();
        return res == 1;
    }

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
