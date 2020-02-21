package fr.ensibs.orders.service;

import fr.ensibs.orders.model.Order;
import fr.ensibs.orders.model.Product;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

/**
 * Interface OrdersManagementService.
 *
 * @author Yoann Le Dréan
 * @version 1.0
 */
@WebService(name = "OrdersManagementService", targetNamespace = "http://fr.ensibs")
public interface OrdersManagementService {

    /**
     * Add an order in the database
     *
     * @param token    The user token
     * @param id       The product id
     * @param quantity The quantity of the product
     * @return true if the order is added
     */
    boolean addOrder(@WebParam(name = "token") String token, @WebParam(name = "id") int id, @WebParam(name = "quantity") int quantity);

    /**
     * Gets the orders from a specified user
     *
     * @param token The token of the user
     * @return The list of the user's orders
     */
    List<Order> getOrders(@WebParam(name = "token") String token);

    /**
     * Gets the orders from all users
     *
     * @param token The token of the admin
     * @return The list of all the orders
     */
    List<Order> getAllOrders(@WebParam(name = "token") String token);

    /**
     * Get the list of products
     *
     * @param token The user token
     * @return The list of products
     */
    List<Product> getMenu(@WebParam(name = "token") String token);

    /**
     * Set the state of the order to paid
     *
     * @param token The user token
     * @param id    The order id
     */
    void setPaid(@WebParam(name = "token") String token, @WebParam(name = "id") int id);

    /**
     * Add a product in the database
     *
     * @param token The user token
     * @param name  The product name
     * @param price The product price
     * @return true if the product is added
     */
    boolean addProduct(@WebParam(name = "token") String token, @WebParam(name = "name") String name, @WebParam(name = "price") float price);
}