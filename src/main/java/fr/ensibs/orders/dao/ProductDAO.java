package fr.ensibs.orders.dao;


import fr.ensibs.orders.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    private Connection connection;

    public ProductDAO() {

        this.connection = ConnectionSQLite.getConnection();
    }


    public void addProduct(Product product) throws SQLException {
        String query = "INSERT INTO Product(name,price) VALUES (?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, product.getName());
        preparedStatement.setFloat(2, product.getPrice());
        preparedStatement.executeUpdate();
    }

    public List<Product> getProducts() throws SQLException {
        String query = "SELECT id, name, price FROM Product";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet res = preparedStatement.executeQuery();

        List<Product> products = new ArrayList<>();
        while (res.next()) {
            products.add(new Product(
                            res.getInt("id"),
                            res.getString("name"),
                            res.getFloat("price")
                    )
            );
               }


        return products;

    }

    public boolean checkProductExists(int id) throws SQLException {
        String query = "SELECT id, name, price FROM Product WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet res = preparedStatement.executeQuery();
        return res.next();
    }
}
