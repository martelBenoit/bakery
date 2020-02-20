package fr.ensibs.service;

import fr.ensibs.model.Order;
import fr.ensibs.model.Product;


import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@WebService(name = "OrdersManagementService", targetNamespace = "http://fr.ensibs")
public interface OrdersManagementService {

   boolean addOrder(String token, List<String> products);

   List<Order> getOrders(String token);

   ArrayList<Product> getMenu();

   void setPaid(int id);


}