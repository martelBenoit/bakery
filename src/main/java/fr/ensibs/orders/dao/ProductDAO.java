package fr.ensibs.orders.dao;


import fr.ensibs.orders.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * ProductDAO class.
 *
 * @author Yoann Le Dréan
 * @version 1.0
 */
public class ProductDAO {

    /**
     * the connection instance.
     */
    private Connection connection;

    /**
     * Constructor.
     */
    public ProductDAO() {

        this.connection = ConnectionSQLite.getConnection();
    }

    /**
     * This method allows you to add a product in the database.
     *
     * @param product the product to add.
     * @throws Exception throw an exception if the product already exists
     */
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

    /**
     * This method checks if a product exists in the database
     *
     * @param id The id of the product to check
     * @return true if the product exists
     * @throws SQLException throw an SQLException if an exception was occurred
     */
    public boolean checkProductExists(int id) throws SQLException {
        String query = "SELECT id, name, price FROM Product WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet res = preparedStatement.executeQuery();
        return res.next();
    }
}
