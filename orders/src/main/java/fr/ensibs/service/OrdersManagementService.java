package fr.ensibs.service;

import fr.ensibs.entity.Order;

import javax.jws.WebService;
import java.util.List;

@WebService(name = "OrdersManagementService", targetNamespace = "http://fr.ensibs")
public interface OrdersManagementService {

   Order add(String nom /* etc.. */);

   List<Order> getOrders(int id);


}