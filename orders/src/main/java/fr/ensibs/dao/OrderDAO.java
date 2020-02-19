package fr.ensibs.dao;

import fr.ensibs.models.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    private static Connection connection;

    public OrderDAO(){

        this.connection = ConnectionSQLite.getConnection();
    }


    public boolean addOrder(Order order) throws SQLException {
        String query = "INSERT INTO Order(userName,price,paid) VALUES (?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,order.getUserName());
        preparedStatement.setFloat(2,order.getPrice());
        preparedStatement.setBoolean(3,order.isPaid());
        int res = preparedStatement.executeUpdate();
        return res == 1;
    }

    public List<Order> getOrders(String userName) throws SQLException {
        String query = "SELECT id, userName, price, paid FROM Order WHERE userName = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,userName);
        ResultSet res = preparedStatement.executeQuery(query);

        List<Order> orders = new ArrayList<Order>();
        while(res.next()){
            orders.add(new Order(
                    res.getInt("id"),
                    res.getString("userName"),
                    res.getFloat("price"),
                    res.getBoolean("paid")
                    )
            );
        }

        return orders;

    }

    public void setPaid(int id) {
        try {
            String query = "UPDATE Order set paid = true WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, String.valueOf(id));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
