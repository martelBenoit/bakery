package fr.ensibs.service;

import fr.ensibs.database.entity.Order;
import fr.ensibs.database.entity.Product;


import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebService(name = "OrdersManagementService", targetNamespace = "http://fr.ensibs")
public interface OrdersManagementService {

   void addOrder(String token, List<String> products);

   List<Order> getOrders(String token);

   ArrayList<Product> getMenu();

   void setPaid(int id);


}