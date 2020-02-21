package fr.ensibs.service;

import fr.ensibs.model.Order;
import fr.ensibs.model.Product;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@WebService(name = "OrdersManagementService", targetNamespace = "http://fr.ensibs")
public interface OrdersManagementService {

    boolean addOrder(String token, int id, int quantity);

    List<Order> getOrders(String token);

    List<Order> getAllOrders(String token);

    List<Product> getMenu(String token);

    void setPaid(String token, int id);

   void addProduct(String token, String name, float price);
}