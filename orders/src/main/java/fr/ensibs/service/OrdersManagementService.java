package fr.ensibs.service;

import fr.ensibs.models.Order;

import javax.jws.WebService;
import java.util.List;

@WebService(name = "OrdersManagementService", targetNamespace = "http://fr.ensibs")
public interface OrdersManagementService {

   void add(String userName, float price);

   List<Order> getOrders(String userName, String userToken);

   void printMenu();


}