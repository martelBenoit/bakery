package fr.ensibs.service;

import fr.ensibs.models.Order;

import javax.jws.WebService;
import java.util.List;
import java.util.Map;

@WebService(name = "OrdersManagementService", targetNamespace = "http://fr.ensibs")
public interface OrdersManagementService {

   void addOrder(String userName, List<String> products);

   List<Order> getOrders(String login);

   Map<String, Float> getMenu();

   void setPaid(int id);


}